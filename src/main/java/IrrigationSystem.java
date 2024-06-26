import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.health.model.HealthService;
import com.project.IrrigationSystemProto;
import com.project.IrrigationSystemServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class IrrigationSystem {

    private TextArea irrigationTextArea; // for displaying messages in the GUI
    private ConsulClient consulClient;
    private String consulServiceName;
    private static ManagedChannel channel;
    private IrrigationSystemServiceGrpc.IrrigationSystemServiceStub stub;

    // constructor
    public IrrigationSystem(String consulHost, int consulPort, int consulServicePort, String consulServiceName, TextArea irrigationTextArea) {
        this.consulClient = new ConsulClient(consulHost, consulPort);
        this.consulServiceName = consulServiceName;
        this.channel = ManagedChannelBuilder.forAddress(consulHost, consulServicePort).usePlaintext().build();
        this.stub = IrrigationSystemServiceGrpc.newStub(channel);
        this.irrigationTextArea = irrigationTextArea;
    }

    // Bidirectional RPC
    public void InstructIrrigationSystem(ArrayList<IrrigationStatus> irrigationStatusData){
        // lookup service details from Consul
        List<HealthService> healthServices = consulClient.getHealthServices(consulServiceName, true, null).getValue();
        if (healthServices.isEmpty()) {
            System.err.println("No healthy instances of " + consulServiceName + " found in Consul.");
            irrigationTextArea.appendText("No healthy instances of " + consulServiceName + " found in Consul.");
            return;
        }

        // pick the first healthy instance
        HealthService healthService = healthServices.get(0);

        System.out.println("Service details from Consul:");
        System.out.println("Service ID: " + healthService.getService().getId());
        System.out.println("Service Name: " + healthService.getService().getService());
        System.out.println("Service Address: " + healthService.getService().getAddress());
        System.out.println("Service Port: " + healthService.getService().getPort());

        // Extract host and port from the service details
        String serverHost = healthService.getService().getAddress();
        int serverPort = healthService.getService().getPort();

        try {
            StreamObserver<IrrigationSystemProto.IrrigationStatus> irrigationStatusStreamObserver = stub.instructIrrigationSystem(new StreamObserver<IrrigationSystemProto.ServerInstruction>() {
                @Override
                public void onNext(IrrigationSystemProto.ServerInstruction serverInstruction) {
                    // print the response from the server
                    System.out.println("Instruction from the Server: "
                            + "\nset Irrigation System status to: " + serverInstruction.getInstruction()
                            + "\nset flow rate to: " + serverInstruction.getFlowRate() + "\n");
                    // append the response to GUI TextArea
                    irrigationTextArea.appendText("Instruction from the Server: "
                            + "\nset Irrigation System status to: " + serverInstruction.getInstruction()
                            + "\nset flow rate to: " + serverInstruction.getFlowRate() + "\n\n");
                }

                @Override
                public void onError(Throwable t) {
                    // handle errors from the server
                    System.err.println("Error occurred while receiving responses from the server: " + t.getMessage());
                    irrigationTextArea.appendText("Error occurred while receiving responses from the server: " + t.getMessage());

                }

                @Override
                public void onCompleted() {
                    System.out.println("Server streaming instructions completed.\n");
                    irrigationTextArea.appendText("Server streaming instructions completed.\n");
                }
            });

            // read the data from the ArrayList and send messages to the server
            for(int i = 0; i < irrigationStatusData.size(); i++){
                IrrigationStatus irrigationStatus = irrigationStatusData.get(i);
                IrrigationSystemProto.IrrigationStatus irrigationStatusInfo = IrrigationSystemProto.IrrigationStatus.newBuilder()
                        .setCurrentStatus(irrigationStatus.currentStatus)
                        .setFlowRate(irrigationStatus.flowRate)
                        .build();

                irrigationStatusStreamObserver.onNext(irrigationStatusInfo);
                Thread.sleep(5000); //pause for a few seconds between sending each request
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

    /* ***** for testing purpose ***** */

      // for testing purpose
//    public static void main(String[] args){
//
//        // create 5 pieces of IrrigationStatus to send to Server
//        ArrayList<IrrigationStatus> irrigationStatusData = new ArrayList<>();
//        irrigationStatusData.add(new IrrigationStatus("on",5));
//        irrigationStatusData.add(new IrrigationStatus("on",4));
//        irrigationStatusData.add(new IrrigationStatus("on",3));
//        irrigationStatusData.add(new IrrigationStatus("on",2));
//        irrigationStatusData.add(new IrrigationStatus("on",1));
//
//        // create a MobilePhone instance
//        IrrigationSystem irrigationSystem = new IrrigationSystem("localhost", 8500,9192,"irrigation.system.service", null);
//
//        //invoke the bidirectional RPC
//        irrigationSystem.InstructIrrigationSystem(irrigationStatusData);
//
//
//        // read input from the user to quit
//        Scanner scanner = new Scanner(System.in);
//        while(true){
//            System.out.println("Press 'Q' to quit" );
//            String input = scanner.nextLine();
//            if(input.equalsIgnoreCase("Q")){
//                irrigationSystem.shutdown();
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
//
//    }

}