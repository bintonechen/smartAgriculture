//import com.project.IrrigationSystemServiceGrpc;
//import com.project.MobilePhoneServiceGrpc;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import com.project.*;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Server {
    private io.grpc.Server server;

    // method to start the service with SoilSensorServiceImpl() service
    public void startSoilSensorServer(int port) throws IOException {
        server = ServerBuilder.forPort(port).addService(new SoilSensorServiceImpl()).build().start();
        System.out.println("Server started, listening to Soil Sensor on port " + port +"\n");

        // register to consul with service name, service port, and service ID
        registerToConsul("smart.agriculture.soil.sensor.service", port, "smartAgricultureSoilSensorServiceID");
    }

    // method to start the service with IrrigationSystemServiceImpl() service
    public void startIrrigationSystemServer(int port) throws IOException{
        server = ServerBuilder.forPort(port).addService(new IrrigationSystemServiceImpl()).build().start();
        System.out.println("Server started, listening to Irrigation System on port " + port);

        registerToConsul("smart.agriculture.irrigation.system.service", port, "smartAgricultureIrrigationSystemID");
    }

    // method to start the service with MobilePhoneServiceImpl() service
    public void startMobilePhoneServer(int port) throws IOException{
        server = ServerBuilder.forPort(port).addService(new MobilePhoneServiceImpl()).build().start();
        System.out.println("Server started, listening to Mobile Phone on port " + port);

        registerToConsul("smart.agriculture.mobile.phone.service", port, "smartAgricultureMobilePhoneID");
    }

    //  await termination on the main thread
    public void blockUntilServerShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    // stop the server gracefully
    public void stopServer() throws InterruptedException {
        if(server !=null){
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    public void registerToConsul(String serviceName, int servicePort, String serviceID){
        System.out.println("Registering server to Consul...");

        // Create a Consul client
        ConsulClient consulClient = new ConsulClient("localhost", 8500);

        // Define service details
        NewService newService = new NewService();
        newService.setName(serviceName);
        newService.setAddress("localhost");
        newService.setPort(servicePort);
        newService.setId(serviceID);

        // Register service with Consul
        consulClient.agentServiceRegister(newService);

        // Print registration success message
        System.out.println(serviceName + " server registered to Consul successfully");
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
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // create a server instance, which listens to three devices on three separate ports
        Server server = new Server();
        server.startSoilSensorServer(9091);
        server.startMobilePhoneServer(9092);
        server.startIrrigationSystemServer(9093);


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




