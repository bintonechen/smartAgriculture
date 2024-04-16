import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.HealthService;
import com.project.SoilSensorProto;
import com.project.SoilSensorServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SoilSensor {

    private ConsulClient consulClient;
    private String consulServiceName;
    private static ManagedChannel channel;
    private SoilSensorServiceGrpc.SoilSensorServiceStub stub;

    public SoilSensor(String consulHost, int consulPort, int consulServicePort, String consulServiceName) {
        this.consulClient = new ConsulClient(consulHost, consulPort);
        this.consulServiceName = consulServiceName;
        this.channel = ManagedChannelBuilder.forAddress(consulHost, consulServicePort).usePlaintext().build();
        this.stub = SoilSensorServiceGrpc.newStub(channel);
    }

    // method to perform the client streaming RPC
    public void SoilSensorService(ArrayList<SoilSensorInfo> soilSensorInfos){

        // Lookup service details from Consul
        List<HealthService> healthServices = consulClient.getHealthServices(consulServiceName, true, null).getValue();
        if (healthServices.isEmpty()) {
            System.err.println("No healthy instances of " + consulServiceName + " found in Consul.");
            return;
        }

        // Pick the first healthy instance (you can implement a load balancing strategy here)
        HealthService healthService = healthServices.get(0);

        // Debug output for service details
        System.out.println("Service details from Consul:");
        System.out.println("Service ID: " + healthService.getService().getId());
        System.out.println("Service Name: " + healthService.getService().getService());
        System.out.println("Service Address: " + healthService.getService().getAddress());
        System.out.println("Service Port: " + healthService.getService().getPort());

        // Extract host and port from the service details
        String serverHost = healthService.getService().getAddress();
        int serverPort = healthService.getService().getPort();

        try {
            StreamObserver<SoilSensorProto.SoilInfo> soilInfoStreamObserver = stub.sendSoilInfo(new StreamObserver<SoilSensorProto.SoilInfoSummary>() {
                @Override
                public void onNext(SoilSensorProto.SoilInfoSummary soilInfoSummary) {
                    // print the response from the server
                    System.out.println("Soil Info Summary received from Server: \nnumber of Soil Info received: " + soilInfoSummary.getRequestCount()
                            + "\nAverage Moisture Level: " + soilInfoSummary.getAverageMoistureLevel()
                            + "\nAverage PH Level: " + soilInfoSummary.getAveragePHLevel()
                            + "\nAverage Soil Temperature: " + soilInfoSummary.getAverageSoilTemperature());
                }

                @Override
                public void onError(Throwable t) {
                    // handle errors from the server
                    System.err.println("Error occurred while receiving responses from the server: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("Soil Info Summary received completed.");
                }
            });

            // create SoilInfo messages to send to the server
            for (int i = 0; i < soilSensorInfos.size(); i++) {
                SoilSensorInfo soilSensorInfo = soilSensorInfos.get(i);
                SoilSensorProto.SoilInfo soilInfo = SoilSensorProto.SoilInfo.newBuilder()
                        .setMoistureLevel(soilSensorInfo.moistureLevel)
                        .setPhLevel(soilSensorInfo.phLevel)
                        .setSoilTemperature(soilSensorInfo.soilTemperature)
                        .build();

                soilInfoStreamObserver.onNext(soilInfo);

                System.out.println("I just sent message " + (i+1));

                Thread.sleep(2000); // paused for 2 second between sending each message
            }

            soilInfoStreamObserver.onCompleted();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    // create a SoilSensorInfo object to store SoilSensorInfo
    public static class SoilSensorInfo{
        public int moistureLevel;
        public int phLevel;
        public int soilTemperature;

        // SoilSensorInfo constructor
        public SoilSensorInfo(int moistureLevel, int phLevel, int soilTemperature){
            this.moistureLevel = moistureLevel;
            this.phLevel = phLevel;
            this.soilTemperature = soilTemperature;
        }
    }

    public static void main(String[] args) {

        // create 10 pieces of SoilSensorInfo to send to Server
        ArrayList<SoilSensorInfo> soilSensorInfos = new ArrayList<>();
        soilSensorInfos.add(new SoilSensorInfo(3, 5, 21));// 1
        soilSensorInfos.add(new SoilSensorInfo(2, 5, 21));// 2
        soilSensorInfos.add(new SoilSensorInfo(1, 5, 21));// 3
        soilSensorInfos.add(new SoilSensorInfo(0, 5, 21));// 4
        soilSensorInfos.add(new SoilSensorInfo(1, 5, 21));// 5
        soilSensorInfos.add(new SoilSensorInfo(2, 5, 20));// 6
        soilSensorInfos.add(new SoilSensorInfo(3, 5, 20));// 7
        soilSensorInfos.add(new SoilSensorInfo(4, 5, 20));// 8
        soilSensorInfos.add(new SoilSensorInfo(5, 5, 20));// 9
        soilSensorInfos.add(new SoilSensorInfo(5, 5, 20));// 10

        SoilSensor soilSensor = new SoilSensor("localhost", 8500,9191,"soil.sensor.service");

        // invoke the client streaming RPC
        soilSensor.SoilSensorService(soilSensorInfos);

        // read input from the user to quit
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Press 'Q' to quit" );
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("Q")){
                soilSensor.shutdown();
                break;
            }
        }
    }

    public void shutdown (){
        try{
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("Soil Sensor connection closed.");
        } catch (InterruptedException e){
            System.err.println("Error while shutting down Soil Sensor: " + e.getMessage());
        }

    }

}
