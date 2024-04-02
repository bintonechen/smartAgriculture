import com.project.MobilePhoneServiceGrpc;
import com.project.SmartAgricultureProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MobilePhone {

    // declare and create a channel
    private ManagedChannel channel;

    // constructor
    public MobilePhone(String host, int port){
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext().build();
    }

    // Server streaming RPC
    public void mobilePhoneService1(String requestMessage){

        // create a request message for Soil Sensor and Irrigation System update
        SmartAgricultureProto.MobilePhoneRequest request = SmartAgricultureProto.MobilePhoneRequest
                .newBuilder().setRequestMessage(requestMessage).build();

        // create a gRPC stub for the server streaming RPC
        MobilePhoneServiceGrpc.MobilePhoneServiceStub stub = MobilePhoneServiceGrpc.newStub(channel);

        // create a stream observer to handle responses from the server
        StreamObserver<SmartAgricultureProto.MobilePhoneResponse> responseStreamObserver
                = new StreamObserver<SmartAgricultureProto.MobilePhoneResponse>() {
            @Override
            public void onNext(SmartAgricultureProto.MobilePhoneResponse mobilePhoneResponse) {
                // print each response from the server
                System.out.println("Received response from server: " + mobilePhoneResponse.getResponseMessage());
            }

            @Override
            public void onError(Throwable t) {
                // handle errors from the server
                System.err.println("Error from server: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                // print completion message when the response is completed
                System.out.println("Server streaming responses completed.");
            }
        };

        // send the request and handle responses;
        stub.mobilePhoneService1(request, responseStreamObserver);

    }

    // Unary RPC
    public void mobilePhoneService2(String userID){
        try {
            // create a request message with a user ID
            SmartAgricultureProto.MobilePhoneRequest request = SmartAgricultureProto.MobilePhoneRequest
                    .newBuilder().setRequestMessage(userID).build();

            // create a gRPC blocking stub for the unary RPC
            MobilePhoneServiceGrpc.MobilePhoneServiceBlockingStub blockingStub = MobilePhoneServiceGrpc.newBlockingStub(channel);

            // send the request and receive one response
            SmartAgricultureProto.MobilePhoneResponse responseMessage = blockingStub.mobilePhoneService2(request);
            System.out.println("Mobile Phone Service 2 response from server: " + responseMessage);
            System.out.println("Mobile Phone Service 2 response completed."); // once received one response message, the response from the server is completed.
        } catch (Exception e){
            // log the exception
            System.err.println("An error occurred in Mobile Phone Service 2: " + e.getMessage());
            throw new RuntimeException();
        }

    }

    public static void main(String[] args){
        // create a MobilePhone instance
        MobilePhone mobilePhone = new MobilePhone("localhost",9002);

        // invoke the unary RPC and the server streaming RPC
        mobilePhone.mobilePhoneService2("A001");
        mobilePhone.mobilePhoneService1("Request for Soil Sensor and Irrigation System update.");

        // read input from the user to quit
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Press 'Q' to quit" );
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("Q")){
                mobilePhone.shutdown();
                break;
            }
        }
    }

    public void shutdown (){
        try{
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e){
            System.err.println("Error while shutting down Mobile Phone: " + e.getMessage());
        }

    }
}
