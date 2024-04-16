import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import com.project.*;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import org.w3c.dom.ls.LSOutput;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Server {

    private io.grpc.Server server;

    // method to start the service with SoilSensorServiceImpl() service
    public void startSoilSensorServer(int port) throws IOException {
        server = ServerBuilder.forPort(port).addService(new SoilSensorServiceImpl()).build().start();
        System.out.println("Server started, listening to Soil Sensor on port " + port +"\n");

        // register to consul with service name, service port, and service ID
        registerToConsul(1); // register to Consul with the serviceName and servicePort specifically for soilSensor
    }

    // method to start the service with IrrigationSystemServiceImpl() service
    public void startIrrigationSystemServer(int port) throws IOException{
        server = ServerBuilder.forPort(port).addService(new IrrigationSystemServiceImpl()).build().start();
        System.out.println("Server started, listening to Irrigation System on port " + port);

        registerToConsul(2); // register to Consul with the serviceName and servicePort specifically for IrrigationSystem
    }

    // method to start the service with MobilePhoneServiceImpl() service
    public void startMobilePhoneServer(int port) throws IOException{
        server = ServerBuilder.forPort(port).addService(new MobilePhoneServiceImpl()).build().start();
        System.out.println("Server started, listening to Mobile Phone on port " + port);

        registerToConsul(3); // register to Consul with the serviceName and servicePort specifically for MobilePhone
    }

    public void blockUntilServerShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public void stopServer() throws InterruptedException {
        if(server !=null){
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    public void registerToConsul(int serviceNumber){

        System.out.println("Registering server to Consul...");

        // Load Consul configuration from consul.properties file
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/consul.properties")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Extract Consul configuration properties
        String consulHost = props.getProperty("consul.host");
        int consulPort = Integer.parseInt(props.getProperty("consul.port"));
        String healthCheckInterval = props.getProperty("consul.service.healthCheckInterval");

        // Determain the serviceName and servicePort based on the serviceNumber passed in
        String serviceName = null;
        int servicePort = 0;

        if (serviceNumber == 1){
            serviceName = props.getProperty("consul.service.name.soilSensor");
            servicePort = Integer.parseInt(props.getProperty("consul.service.port.soilSensor"));
        } else if (serviceNumber == 2){
            serviceName = props.getProperty("consul.service.name.irrigationSystem");
            servicePort = Integer.parseInt(props.getProperty("consul.service.port.irrigationSystem"));
        } else if (serviceNumber == 3){
            serviceName = props.getProperty("consul.service.name.mobilePhone");
            servicePort = Integer.parseInt(props.getProperty("consul.service.port.mobilePhone"));
        }

        // Get host address
        String hostAddress;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return;
        }

        // Create a Consul client
        ConsulClient consulClient = new ConsulClient(consulHost, consulPort);

        // Define service details
        NewService newService = new NewService();
        newService.setName(serviceName);
        newService.setPort(servicePort);
        newService.setAddress(hostAddress); // Set host address

        // Register service with Consul
        consulClient.agentServiceRegister(newService);

        // Print registration success message
        System.out.println(serviceName + " server registered to Consul successfully");
    }

    public class SoilSensorServiceImpl extends SoilSensorServiceGrpc.SoilSensorServiceImplBase{

        @Override
        public StreamObserver<SoilSensorProto.SoilInfo> sendSoilInfo(StreamObserver<SoilSensorProto.SoilInfoSummary> responseObserver) {
            return new StreamObserver<SoilSensorProto.SoilInfo>() {

                int requestCount = 0;
                double averageMoistureLevel = 0.0;
                double averagePHLevel = 0.0;
                double averageSoilTemperature = 0.0;

                @Override
                public void onNext(SoilSensorProto.SoilInfo soilInfo) {
                    requestCount++; // when receive a new SoilInfo, the count increase by 1
                    averageMoistureLevel += soilInfo.getMoistureLevel(); // Accumulate the moisture levels received to calculate the average.
                    // Division will be performed when the request stream is completed.
                    averagePHLevel += soilInfo.getPhLevel();
                    averageSoilTemperature+= soilInfo.getSoilTemperature();
                    System.out.println("Received SoilInfo " + requestCount + ": \nMoisture Level: " + soilInfo.getMoistureLevel()
                            + " PH Level: " + soilInfo.getPhLevel() + " Soil Temperature: " + soilInfo.getSoilTemperature() + "\n");
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("Error in Soil Sensor request streaming: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    // calculate the average MoistureLevel, SoilTemperature, and PHLevel for the summary message
                    averageMoistureLevel = averageMoistureLevel / requestCount;
                    averageSoilTemperature = averageSoilTemperature / requestCount;
                    averagePHLevel = averagePHLevel / requestCount;
                    responseObserver.onNext(SoilSensorProto.SoilInfoSummary.newBuilder().setRequestCount(requestCount)
                            .setAverageSoilTemperature(averageSoilTemperature).setAveragePHLevel(averagePHLevel)
                            .setAverageMoistureLevel(averageMoistureLevel).build());
                    responseObserver.onCompleted();

                    System.out.println("SoilInfo Summary: " + "\nNumber of SoilInfo received: " + requestCount
                            + " Average Moisture Level: "+ averageMoistureLevel
                            + " Average PH Level: " + averagePHLevel
                            + " Average Soil Temperature: " + averageSoilTemperature);
                }
            };
        }

        @Override
        public void healthCheck(SoilSensorProto.HealthCheckRequest request, StreamObserver<SoilSensorProto.HealthCheckResponse> responseObserver){
            SoilSensorProto.HealthCheckResponse response = SoilSensorProto.HealthCheckResponse.newBuilder().setStatus(200).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    public class IrrigationSystemServiceImpl extends IrrigationSystemServiceGrpc.IrrigationSystemServiceImplBase{

        @Override
        public StreamObserver<IrrigationSystemProto.IrrigationStatus> instructIrrigationSystem(StreamObserver<IrrigationSystemProto.ServerInstruction> responseObserver) {
            return new StreamObserver<IrrigationSystemProto.IrrigationStatus>() {
                @Override
                public void onNext(IrrigationSystemProto.IrrigationStatus irrigationStatus) {
                    String status = irrigationStatus.getCurrentStatus(); // get the current status of the irrigation system
                    int flowRate = irrigationStatus.getFlowRate(); // get the current flow rate of the irrigtation system
                    System.out.println("The current status of Irrigation System is: " + status + " and the current flow rate is: " + flowRate);

                    // create response message
                    int adjustedFlowedRate = flowRate - 1; // decrease the flow rate by 1 each time
                    String instruction;

                    if(adjustedFlowedRate>0){ // determine the status to set based on the flow rate to set.
                        instruction = "on";
                    } else {
                        instruction = "off";
                    }

                    // create the response object and send to the irrigation system
                    IrrigationSystemProto.ServerInstruction serverInstruction = IrrigationSystemProto.ServerInstruction.newBuilder()
                            .setInstruction(instruction)
                            .setFlowRate(adjustedFlowedRate)
                            .build();

                    responseObserver.onNext(serverInstruction);
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("Error in Irrigation System request streaming: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("The irrigation system request streaming is completed.");
                    responseObserver.onCompleted();

                }
            };
        }

        @Override
        public void healthCheck1(IrrigationSystemProto.HealthCheckRequest1 request, StreamObserver<IrrigationSystemProto.HealthCheckResponse1> responseStreamObserver){
            IrrigationSystemProto.HealthCheckResponse1 response = IrrigationSystemProto.HealthCheckResponse1.newBuilder().setStatus(200).build();
            responseStreamObserver.onNext(response);
            responseStreamObserver.onCompleted();
        }
    }

    public class MobilePhoneServiceImpl extends MobilePhoneServiceGrpc.MobilePhoneServiceImplBase{

        @Override
        public void setUserID(MobilePhoneProto.UserID request, StreamObserver<MobilePhoneProto.UserIDConfirmation> responseObserver) {
            // get the user ID from the request
            String userID = request.getUserID();
            // log the User ID
            System.out.println("Received User ID: " + userID + " from the Mobile Phone");
            // create a response message representing successful login
            String confirmation = "User ID: " + userID + " has been successfully logged in.";
            // build the response object
            MobilePhoneProto.UserIDConfirmation response = MobilePhoneProto.UserIDConfirmation.newBuilder().setConfirmation(confirmation).build();
            // send the response to the Mobile Phone
            responseObserver.onNext(response);
            // complete the response
            responseObserver.onCompleted();
        }

        @Override
        public void mobilePhoneRequest(MobilePhoneProto.InfoRequest request, StreamObserver<MobilePhoneProto.InfoResponse> responseObserver) {
            // get the request from the MobilePhone
            String requestFromMobilePhone = request.getInfoRequestMessage();
            // log the InfoRequest
            System.out.println("Received from the Mobile Phone: " + requestFromMobilePhone);
            System.out.println("Health check would run 3 times.");

            // generate three health check results
            // send each result back to the mobile phone separately, with a 2-second interval between each response
            for (int i = 0; i<3; i++){

                MobilePhoneProto.InfoResponse infoResponse = MobilePhoneProto.InfoResponse.newBuilder()
                        .setInfoResponseMessage("Health Check test " + i +" is completed. No issue found.").build();
                responseObserver.onNext(infoResponse);

                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            responseObserver.onCompleted();
            System.out.println("Health check is completed.");
        }

        @Override
        public void healthCheck2(MobilePhoneProto.HealthCheckRequest2 request, StreamObserver<MobilePhoneProto.HealthCheckResponse2> responseObserver){
            MobilePhoneProto.HealthCheckResponse2 response = MobilePhoneProto.HealthCheckResponse2.newBuilder().setStatus(200).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server();
        server.startSoilSensorServer(9191);
        server.startIrrigationSystemServer(9192);
        server.startMobilePhoneServer(9193);

        // add a shutdown hook for graceful shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // print a message indicting the start of the shutdown process
            System.err.println("*** shutting down gRPC server");
            try {
                server.stopServer();

                // print a message indicating successful shutdown
                System.err.println("*** all gRPC servers successfully shut down ***");

            } catch (InterruptedException e) {
                // if an InterruptedException occurs during shutdown, interrupt the current thread
                Thread.currentThread().interrupt();
                e.printStackTrace(System.err);
                // print an error message
                System.err.println("*** Error occurred during shutdown: " + e.getMessage() + " ***");
            }
        }));

        // Block until all servers shut down
        server.blockUntilServerShutdown();
    }
}
