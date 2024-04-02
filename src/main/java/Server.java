import com.project.IrrigationSystemServiceGrpc;
import com.project.MobilePhoneServiceGrpc;
import com.project.SoilSensorProto;
import com.project.MobilePhoneProto;
import com.project.IrrigationSystemProto;
import com.project.SoilSensorServiceGrpc;

import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Server {
    private io.grpc.Server server;

    // method to start the service with SoilSensorServiceImpl() service
    public void startSoilSensorServer(int port) throws IOException {
        server = ServerBuilder.forPort(port).addService(new SoilSensorServiceImpl()).build().start();
        System.out.println("Server started, listening to Soil Sensor on port " + port);
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

    // block until the server shuts down
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

    // MobilePhoneService Implementation
    public class MobilePhoneServiceImpl extends MobilePhoneServiceGrpc.MobilePhoneServiceImplBase{
        // Server streaming RPC
        // Mobile Phone send one request for info update, then receives a response from the server every 5 seconds, until press 'Q' to quit.
        @Override
        public void mobilePhoneService1(MobilePhoneProto.MobilePhoneRequest request, StreamObserver<MobilePhoneProto.MobilePhoneResponse> responseObserver) {
            // receive one request message from Mobile Phone
            String requestMessage = request.getRequestMessage();
            System.out.println("Received request from the Mobile Phone: " + requestMessage);

            // define a streaming task
            Runnable streamingTask = () -> {
                try {
                    // continuously send messages until the thread is interrupted
                    while (!Thread.currentThread().isInterrupted()) {
                        // create a response message for the mobile phone
                        String responseMessage = "The Soil Sensor is on, and the Irrigation System is on, at current time: " + LocalDateTime.now();
                        // build the response object
                        MobilePhoneProto.MobilePhoneResponse response = MobilePhoneProto.MobilePhoneResponse.newBuilder()
                                .setResponseMessage(responseMessage).build();

                        // send the response to the Mobile Phone
                        responseObserver.onNext(response);

                        // send the response to the Mobile Phone every 5 seconds
                        Thread.sleep(5000);
                    }
                } catch (InterruptedException e) {
                    // handle interruption by interrupting the thread and completing the response
                    Thread.currentThread().interrupt();
                    // log the interruption
                    System.err.println("Server info update response interrupted: " + e.getMessage());
                } finally {
                    // complete the response
                    responseObserver.onCompleted();
                }
            };

            // start the streaming task in a new thread
            Thread streamingThread = new Thread(streamingTask);
            streamingThread.start();
        }

        // Unary RPC
        // Mobile Phone sends the user ID to the server, then receives one login confirmation response from the server.
        @Override
        public void mobilePhoneService2(MobilePhoneProto.MobilePhoneRequest request, StreamObserver<MobilePhoneProto.MobilePhoneResponse> responseObserver) {
            // get the user ID from the request
            String userID = request.getRequestMessage();
            // log the received User ID
            System.out.println("Received User ID: " + userID + " from the Mobile Phone");
            // create a response message representing successful login
            String message = "User ID: " + userID + " has been successfully logged in.";
            // build the response object
            MobilePhoneProto.MobilePhoneResponse response = MobilePhoneProto.MobilePhoneResponse.newBuilder()
                    .setResponseMessage(message).build();

            // send the response to the Mobile Phone
            responseObserver.onNext(response);
            // complete the response
            responseObserver.onCompleted();
        }
    }

    //SoilSensorService Implementation
    public class SoilSensorServiceImpl extends SoilSensorServiceGrpc.SoilSensorServiceImplBase{
        // Client streaming RPC
        @Override
        public StreamObserver<SoilSensorProto.SoilSensorRequest> soilSensor(StreamObserver<SoilSensorProto.SoilSensorResponse> responseObserver) {
            // return a new StreamObserver to handle incoming requests from Soil Sensor
            return new StreamObserver<SoilSensorProto.SoilSensorRequest>() {

                // print each incoming request from the Soil Sensor
                @Override
                public void onNext(SoilSensorProto.SoilSensorRequest soilSensorRequest) {
                    System.out.println("Received Soil Sensor information: ");
                    System.out.println(soilSensorRequest.getSoilInfo() + " at " + LocalDateTime.now());
                }

                // handle errors occur during Soil Sensor Streaming
                @Override
                public void onError(Throwable t) {
                    System.err.println("Error in Soil Sensor request streaming: " + t.getMessage());
                }

                // handle the completion of Soil Sensor streaming
                @Override
                public void onCompleted() {
                    // print a message indicating the completion of Soil Sensor streaming
                    System.out.println("Soil Sensor streaming completed.");
                    try {
                        // create a response message and build a response object
                        SoilSensorProto.SoilSensorResponse response = SoilSensorProto.SoilSensorResponse.newBuilder()
                                .setServerMessage("Soil Sensor information received successfully").build();
                        // Send the response back to the Soil Sensor
                        responseObserver.onNext(response);
                        responseObserver.onCompleted();
                    } catch (Exception e){
                        System.err.println("Error sending final response to the Soil Sensor: " + e.getMessage());
                    }
                }
            };
        }
    }

    //IrrigationSystemService Implementation
    public class IrrigationSystemServiceImpl extends IrrigationSystemServiceGrpc.IrrigationSystemServiceImplBase{
        // Bidirectional streaming RPC
        @Override
        public StreamObserver<IrrigationSystemProto.IrrigationSystemRequest> irrigationSystem(StreamObserver<IrrigationSystemProto.IrrigationSystemResponse> responseObserver) {
            return new StreamObserver<IrrigationSystemProto.IrrigationSystemRequest>() {

                // print each incoming request from the Irrigation System
                @Override
                public void onNext(IrrigationSystemProto.IrrigationSystemRequest irrigationSystemRequest) {
                    System.out.println("Received Irrigation System request: ");
                    System.out.println(irrigationSystemRequest.getFlowRate());
                    System.out.println(irrigationSystemRequest.getSoilMoisture() + " at " + LocalDateTime.now());
                }

                // handle errors occur during Irrigation Streaming
                @Override
                public void onError(Throwable t) {
                    System.err.println("Error in Irrigation System request streaming: " + t.getMessage());
                }

                // handle the completion of Irrigation System streaming
                @Override
                public void onCompleted() {
                    // print a message indicating the completion of Soil Sensor streaming
                    System.out.println("Irrigation System request streaming completed.");

                    try {
                        // create a response message and build a response object
                        IrrigationSystemProto.IrrigationSystemResponse response = IrrigationSystemProto.IrrigationSystemResponse.newBuilder()
                                .setInstruction("Irrigation System streaming requests received successfully.").build();

                        // Send the response back to the Irrigation System
                        responseObserver.onNext(response);
                        responseObserver.onCompleted();
                    } catch (Exception e){
                        System.err.println("Error sending final response to the Irrigation System: " + e.getMessage());
                    }
                }
            };
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // instantiate a server for Soil Sensor
        Server soilSensorServer = new Server();
        soilSensorServer.startSoilSensorServer(9000);

        // instantiate a server for Irrigation System
        Server irrigationSystemServer = new Server();
        irrigationSystemServer.startIrrigationSystemServer(9001);

        // instantiate a server for Mobile Phone
        Server mobilePhoneServer = new Server();
        mobilePhoneServer.startMobilePhoneServer(9002);

        // add a shutdown hook for graceful shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // print a message indicting the start of the shutdown process
            System.out.println("Shutting down gRPC servers");
            try {
                soilSensorServer.stopServer();
                irrigationSystemServer.stopServer();;
                mobilePhoneServer.stopServer();
            } catch (InterruptedException e) {
                // if an InterruptedException occurs during shutdown, interrupt the current thread
                Thread.currentThread().interrupt();
                e.printStackTrace(System.err);

            }
        }));

        // Block until all servers shut down
        soilSensorServer.blockUntilServerShutdown();
        irrigationSystemServer.blockUntilServerShutdown();
        mobilePhoneServer.blockUntilServerShutdown();
    }


}
