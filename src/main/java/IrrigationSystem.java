import com.project.IrrigationSystemServiceGrpc;
import com.project.MobilePhoneServiceGrpc;
import com.project.SmartAgricultureProto;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class IrrigationSystem {
    // declare and create a channel
    private ManagedChannel channel;

    // constructor
    public IrrigationSystem(String host, int port){
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext().build();
    }

    // bidirectional streaming RPC
    public void irrigationSystem(){

        try {

            // create a gRPC server streaming stub for the bidirectional streaming RPC
            IrrigationSystemServiceGrpc.IrrigationSystemServiceStub stub = IrrigationSystemServiceGrpc.newStub(channel);

            // create a stream observer to handle responses from the server
            StreamObserver<SmartAgricultureProto.IrrigationSystemResponse> responseStreamObserver
                    = new StreamObserver<SmartAgricultureProto.IrrigationSystemResponse>() {
                @Override
                public void onNext(SmartAgricultureProto.IrrigationSystemResponse irrigationSystemResponse) {
                    // handle each response from the server
                    System.out.println("Received response from server: " + irrigationSystemResponse.getInstruction());
                }

                @Override
                public void onError(Throwable t) {
                    // handle errors from the server
                    System.err.println("Error from sever: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    // handle completion of sever streaming
                    System.out.println("Server streaming completed");
                }
            };

            // create a request observer for Irrigation System streaming
            StreamObserver<SmartAgricultureProto.IrrigationSystemRequest> requestStreamObserver = stub.irrigationSystem(responseStreamObserver);

            for (int i = 0; i < 3; i++) {

                String soilMoistureMessage = "The Moisture Level: ";
                String flowRateMessage = "The Flow Rate: ";

                if (i == 0) {
                    soilMoistureMessage += "Very Low ";
                    flowRateMessage += "High Flow ";
                } else if (i == 1) {
                    soilMoistureMessage += "Low ";
                    flowRateMessage += "Low Flow ";
                } else {
                    soilMoistureMessage += "Optimal ";
                    flowRateMessage += "Very Low Flow ";
                }


                SmartAgricultureProto.IrrigationSystemRequest.Builder builder = SmartAgricultureProto.IrrigationSystemRequest.newBuilder();
                builder.setFlowRate(flowRateMessage);
                builder.setSoilMoisture(soilMoistureMessage);
                SmartAgricultureProto.IrrigationSystemRequest irrigationSystemRequest = builder.build();

                // send each request to the server with 2 second apart
                requestStreamObserver.onNext(irrigationSystemRequest);
                Thread.sleep(2000);

            }

            requestStreamObserver.onCompleted();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {

        // create an Irrigation System instance
        IrrigationSystem irrigationSystem = new IrrigationSystem("localhost", 9001);

        // invoke the bidirectional streaming RPC
        irrigationSystem.irrigationSystem();

        // read input from the user to quit
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Press 'Q' to quit" );
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("Q")){
                irrigationSystem.shutdown();
                break;
            }
        }

    }

    public void shutdown (){
        try{
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("Irrigation System connection closed.");
        } catch (InterruptedException e){
            System.err.println("Error while shutting down Irrigation System: " + e.getMessage());
        }

    }

}
