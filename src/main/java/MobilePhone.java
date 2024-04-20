import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.HealthService;
import com.project.MobilePhoneProto;
import com.project.MobilePhoneServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MobilePhone {

    private TextArea serverTextArea; // for message display in GUI

    private ConsulClient consulClient;
    private String consulServiceName;
    private static ManagedChannel channel;
    private MobilePhoneServiceGrpc.MobilePhoneServiceStub stub; // for the server-to-client streaming RPC
    private MobilePhoneServiceGrpc.MobilePhoneServiceBlockingStub blockingStub; // for the unary RPC

    // constructor
    public MobilePhone(String consulHost, int consulPort, int consulServicePort, String consulServiceName, TextArea serverTextArea) {
        this.consulClient = new ConsulClient(consulHost, consulPort);
        this.consulServiceName = consulServiceName;
        this.channel = ManagedChannelBuilder.forAddress(consulHost, consulServicePort).usePlaintext().build();
        this.stub = MobilePhoneServiceGrpc.newStub(channel);
        this.blockingStub = MobilePhoneServiceGrpc.newBlockingStub(channel);
        this.serverTextArea = serverTextArea;
    }

    // Unary RPC
    public String SetUserID(String userID){
        // Lookup service details from Consul
        List<HealthService> healthServices = consulClient.getHealthServices(consulServiceName, true, null).getValue();
        if (healthServices.isEmpty()) {
            System.err.println("No healthy instances of " + consulServiceName + " found in Consul.");
            return "No healthy instances of \" + consulServiceName + \" found in Consul.";
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

        try{
            // create a request message with a user ID
            MobilePhoneProto.UserID request = MobilePhoneProto.UserID.newBuilder().setUserID(userID).build();

            System.out.println(request);

            // send the request and receive a user ID confirmation response
            MobilePhoneProto.UserIDConfirmation response = blockingStub.setUserID(request);

            System.out.println(response);

            return response.toString();

        } catch (Exception e){
            System.err.println("An error occurred in SetUserID service: " + e.getMessage());
            serverTextArea.appendText("An error occurred in SetUserID service: " + e.getMessage() + "\n");
            throw new RuntimeException();
        }
    }

    // server-to-client streaming RPC
    public void MobilePhoneRequest(){

        // Lookup service details from Consul
        List<HealthService> healthServices = consulClient.getHealthServices(consulServiceName, true, null).getValue();
        if (healthServices.isEmpty()) {
            serverTextArea.appendText("No healthy instances of " + consulServiceName + " found in Consul.");
            System.err.println("****** Mobile Phone ******");
            System.err.println("No healthy instances of " + consulServiceName + " found in Consul.");
            return;
        }

        // Pick the first healthy instance (you can implement a load balancing strategy here)
        HealthService healthService = healthServices.get(0);

        // Debug output for service details
        System.out.println("****** Mobile Phone ******");
        System.out.println("Service details from Consul:");
        System.out.println("Service ID: " + healthService.getService().getId());
        System.out.println("Service Name: " + healthService.getService().getService());
        System.out.println("Service Address: " + healthService.getService().getAddress());
        System.out.println("Service Port: " + healthService.getService().getPort());

        // Extract host and port from the service details
        String serverHost = healthService.getService().getAddress();
        int serverPort = healthService.getService().getPort();

        System.out.println("****** Mobile Phone ******");
        String requestMessage = "Request for health check for the Smart Agriculture System.\n";
        serverTextArea.appendText("Request for health check for the Smart Agriculture System.\n\n");


        // create a request message
        MobilePhoneProto.InfoRequest request = MobilePhoneProto.InfoRequest
                .newBuilder().setInfoRequestMessage(requestMessage).build();

        // create a stream observer to handle response from the server
        StreamObserver<MobilePhoneProto.InfoResponse> infoResponseStreamObserver
                = new StreamObserver<MobilePhoneProto.InfoResponse>() {
            @Override
            public void onNext(MobilePhoneProto.InfoResponse infoResponse) {
                System.out.println(infoResponse);
                serverTextArea.appendText(infoResponse.toString()+"\n");
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error from server: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("****** Mobile Phone ******");
                System.out.println("Three Health check tests are completed.\n");
                serverTextArea.appendText("Three Health check tests are completed.\n");
            }
        };

        // send the request and handle response
        stub.mobilePhoneRequest(request, infoResponseStreamObserver);
    }

    /* ***** for testing purpose ***** */

//    public static void main(String[] args){
//        // create a MobilePhone instance
//        MobilePhone mobilePhone = new MobilePhone("localhost", 8500,9193,"mobile.phone.service", null);
//
//        mobilePhone.SetUserID("A0001");
//        mobilePhone.MobilePhoneRequest();
//
//        // read input from the user to quit
//        Scanner scanner = new Scanner(System.in);
//        while(true){
//            System.out.println("Press 'Q' to quit" );
//            String input = scanner.nextLine();
//            if(input.equalsIgnoreCase("Q")){
//                mobilePhone.shutdown();
//                break;
//            }
//        }
//    }

//    public void shutdown (){
//        try{
//            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
//        } catch (InterruptedException e){
//            System.err.println("Error while shutting down Mobile Phone: " + e.getMessage());
//        }
//    }

}