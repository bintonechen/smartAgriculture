import com.project.IrrigationSystemProto;
import com.project.IrrigationSystemServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class IrrigationSystem {

    private static ManagedChannel channel;
    private IrrigationSystemServiceGrpc.IrrigationSystemServiceStub stub;

    // constructor
    public IrrigationSystem(String host, int port){
        this.channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        this.stub = IrrigationSystemServiceGrpc.newStub(channel);
    }

    // method to perform the bidirectional RPC
    public void InstructIrrigationSystem(ArrayList<IrrigationStatus> irrigationStatusData){

        try {
            StreamObserver<IrrigationSystemProto.IrrigationStatus> irrigationStatusStreamObserver = stub.instructIrrigationSystem(new StreamObserver<IrrigationSystemProto.ServerInstruction>() {
                @Override
                public void onNext(IrrigationSystemProto.ServerInstruction serverInstruction) {
                    // print the response from the server
                    System.out.println("Instruction received from the Server: "
                            + "\nset Irrigation System status to: " + serverInstruction.getInstruction()
                            + "\nset flow rate to: " + serverInstruction.getFlowRate());
                }

                @Override
                public void onError(Throwable t) {
                    // handle errors from the server
                    System.err.println("Error occurred while receiving responses from the server: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    System.out.println("Server instructions received completed.");
                }
            });

            // create IrrigationStatus messages to send to the server
            for(int i = 0; i < irrigationStatusData.size(); i++){
                IrrigationStatus irrigationStatus = irrigationStatusData.get(i);
                IrrigationSystemProto.IrrigationStatus irrigationStatusInfo = IrrigationSystemProto.IrrigationStatus.newBuilder()
                        .setCurrentStatus(irrigationStatus.currentStatus)
                        .setFlowRate(irrigationStatus.flowRate)
                        .build();

                irrigationStatusStreamObserver.onNext(irrigationStatusInfo);
                Thread.sleep(2000); // Pause for 2 second between sending each request
            }

            irrigationStatusStreamObserver.onCompleted();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // create an IrrigationStatus object to store data
    public static class IrrigationStatus{
        public String currentStatus;
        public int flowRate;

        // IrrigationStatus constructor
        public IrrigationStatus(String currentStatus, int flowRate){
            this.currentStatus = currentStatus;
            this.flowRate = flowRate;
        }
    }


    public static void main(String[] args){

        // create 5 pieces of IrrigationStatus to send to Server
        ArrayList<IrrigationStatus> irrigationStatusData = new ArrayList<>();
        irrigationStatusData.add(new IrrigationStatus("on",5));
        irrigationStatusData.add(new IrrigationStatus("on",4));
        irrigationStatusData.add(new IrrigationStatus("on",3));
        irrigationStatusData.add(new IrrigationStatus("on",2));
        irrigationStatusData.add(new IrrigationStatus("on",1));

        // create a MobilePhone instance
        IrrigationSystem irrigationSystem = new IrrigationSystem("localhost", 9093);

        //invoke the bidirectional RPC
        irrigationSystem.InstructIrrigationSystem(irrigationStatusData);


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
        } catch (InterruptedException e){
            System.err.println("Error while shutting down Mobile Phone: " + e.getMessage());
        }

    }

}
