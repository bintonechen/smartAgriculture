//import com.project.IrrigationSystemServiceGrpc;
//import com.project.MobilePhoneServiceGrpc;
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
    }

    // method to start the service with IrrigationSystemServiceImpl() service
    public void startIrrigationSystemServer(int port) throws IOException{
        server = ServerBuilder.forPort(port).addService(new IrrigationSystemServiceImpl()).build().start();
        System.out.println("Server started, listening to Irrigation System on port " + port);
    }

    // method to start the service with MobilePhoneServiceImpl() service
    public void startMobilePhoneServer(int port) throws IOException{
        server = ServerBuilder.forPort(port).addService(new MobilePhoneServiceImpl()).build().start();
        System.out.println("Server started, listening to Mobile Phone on port " + port);
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

    public class MobilePhoneServiceImpl extends MobilePhoneServiceGrpc.MobilePhoneServiceImplBase{

        @Override
        public void setUserID(MobilePhoneProto.UserID request, StreamObserver<MobilePhoneProto.UserIDConfirmation> responseObserver) {
            // get the user ID from the request
            String userID = request.getUserID();
            // log the received User ID
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
            // get the InfoRequest
            String requestFromMobilePhone = request.getInfoRequestMessage();
            // log the InfoRequest
            System.out.println("Received from the Mobile Phone: " + requestFromMobilePhone);
            System.out.println("Health check would run 3 times.");

            for (int i = 0; i<3; i++){

                MobilePhoneProto.InfoResponse infoResponse = MobilePhoneProto.InfoResponse.newBuilder()
                        .setInfoResponseMessage("Health Check test " + i +" is completed. No issue found.").build();
                responseObserver.onNext(infoResponse);

                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
            responseObserver.onCompleted();
            System.out.println("Health check for the Soil Sensor and the Irrigation System completed.");
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
                    requestCount++;
                    averageMoistureLevel += soilInfo.getMoistureLevel();
                    averagePHLevel += soilInfo.getPhLevel();
                    averageSoilTemperature+= soilInfo.getSoilTemperature();
                    System.out.println("Received SoilInfo " + requestCount + ": \nMoisture Level: " + soilInfo.getMoistureLevel()
                            + " PH Level: " + soilInfo.getPhLevel() + " Soil Temperature: " + soilInfo.getSoilTemperature() + "\n");

//                    if(soilInfo.getMoistureLevel() == 0){
//                        IrrigationService();
//                        System.out.println("Moisture level is low. Triggering irrigation system.");
//                    }
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("Error in Soil Sensor request streaming: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
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
                    String status = irrigationStatus.getCurrentStatus();
                    int flowRate = irrigationStatus.getFlowRate();
                    System.out.println("The current status of Irrigation System is: " + status + " and the current flow rate is: " + flowRate);

                    // create response message
                    int adjustedFlowedRate = flowRate - 1;
                    String instruction;

                    if(adjustedFlowedRate>0){
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
                    System.out.println("The irrigation system request streaming completed.");
                    responseObserver.onCompleted();

                }
            };
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // instantiate a server for Soil Sensor
        Server server = new Server();
        server.startSoilSensorServer(9091);
        server.startMobilePhoneServer(9092);
        server.startIrrigationSystemServer(9093);


        // add a shutdown hook for graceful shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // print a message indicting the start of the shutdown process
            System.err.println("*** shutting down gRPC servers");
            try {
                server.stopServer();

//                soilSensorServer.stopServer();
//                irrigationSystemServer.stopServer();
//                mobilePhoneServer.stopServer();

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

//      soilSensorServer.blockUntilServerShutdown();
//      irrigationSystemServer.blockUntilServerShutdown();
//      mobilePhoneServer.blockUntilServerShutdown();

    }

}




