import com.project.MobilePhoneServiceGrpc;
import com.project.SmartAgricultureProto;
import com.project.SoilSensorServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SoilSensor {
    // declare and create a channel  
    private ManagedChannel channel;

    // constructor  
    public SoilSensor(String host, int port){
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext().build();
    }

    // client streaming RPC  
    public void soilSensorService(){

        try {
            // create a gRPC client streaming stub  
            SoilSensorServiceGrpc.SoilSensorServiceStub stub = SoilSensorServiceGrpc.newStub(channel);

            // create the StreamObserver to handle responses from the server
            StreamObserver<SmartAgricultureProto.SoilSensorRequest> requestStreamObserver = stub.soilSensor(new StreamObserver<SmartAgricultureProto.SoilSensorResponse>() {
                @Override
                public void onNext(SmartAgricultureProto.SoilSensorResponse soilSensorResponse) {
                    // print the response from the server
                    System.out.println("Server response: " + soilSensorResponse.getServerMessage());
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("Error occurred while receiving responses from the server: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    // print completion message when the response is completed
                    System.out.println("Response from the server completed.");
                }
            });


            // send requests to the server
            String request;
            for (int i = 0; i < 4; i++) {
                if (i == 0) {
                    request = "TEMPERATURE: 20 degree ";
                } else if (i == 1) {
                    request = "NUTRITION: Nitrogen: Optimal ; Phosphorus: Optimal ; potassium: Low ";
                } else if (i == 2){
                    request = "PH LEVEL: optimal ";
                } else {
                    request = "Soil Sensor information update completed ";
                }

                SmartAgricultureProto.SoilSensorRequest.Builder builder = SmartAgricultureProto.SoilSensorRequest.newBuilder();
                builder.setSoilInfo(request + "at " + LocalDateTime.now());
                SmartAgricultureProto.SoilSensorRequest soilSensorRequest = builder.build();

                // send each request to the server with 2 second apart  
                requestStreamObserver.onNext(soilSensorRequest);
                Thread.sleep(2000);
            }

            requestStreamObserver.onCompleted();



        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args){
        // create a SoilSensor instance
        SoilSensor soilSensor = new SoilSensor("localhost",9000);

        // invoke the client streaming RPC
        soilSensor.soilSensorService();

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