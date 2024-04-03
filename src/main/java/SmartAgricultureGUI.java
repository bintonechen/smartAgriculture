import com.project.IrrigationSystemServiceGrpc;
import com.project.MobilePhoneServiceGrpc;
import com.project.SoilSensorServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class SmartAgricultureGUI {

    private static ManagedChannel channel;

    private static IrrigationSystemServiceGrpc.IrrigationSystemServiceStub IrrigationSystemStub;
    private static MobilePhoneServiceGrpc.MobilePhoneServiceStub MobilePhoneStub;
    private static SoilSensorServiceGrpc.SoilSensorServiceStub SoilSensorStub;

    public static void main(String[] args) {
        channel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();
        IrrigationSystemStub =IrrigationSystemServiceGrpc.newStub(channel);
        MobilePhoneStub = MobilePhoneServiceGrpc.newStub(channel);
        SoilSensorStub = SoilSensorServiceGrpc.newStub(channel);

        Scanner sc = new Scanner(System.in);






    }

}
