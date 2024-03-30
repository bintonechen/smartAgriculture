import com.project.IrrigationSystemServiceGrpc;
import com.project.MobilePhoneServiceGrpc;
import com.project.SmartAgricultureProto;
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
        server = ServerBuilder.forPort(port).addService(new IrrigationsSystemServiceImpl()).build().start();
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
    }

    //SoilSensorService Implementation
    public class SoilSensorServiceImpl extends SoilSensorServiceGrpc.SoilSensorServiceImplBase{
    }

    //IrrigationSystemService Implementation
    public class IrrigationsSystemServiceImpl extends IrrigationSystemServiceGrpc.IrrigationSystemServiceImplBase{
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
