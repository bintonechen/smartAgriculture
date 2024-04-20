import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {

    @FXML
    private Button irrigationSystemButton;

    @FXML
    private TextArea irrigationSystemTextArea;

    @FXML
    private Button logInButton;

    @FXML
    private Button quitButton;

    @FXML
    private TextArea serverTextArea;

    @FXML
    private Button soilSensorStartButton;

    @FXML
    private TextArea soilSensorTextArea;

    @FXML
    private Button systemHealthCheckButton;

    @FXML
    private TextArea userIDTextField;

    @FXML
    private ImageView logoImageView;

    public void login(ActionEvent e) throws IOException {
        String userID = userIDTextField.getText();
        MobilePhone mobilePhone = new MobilePhone("localhost", 8500,9193,"mobile.phone.service", null);
        String confirmationMessage = mobilePhone.SetUserID(userID);
        displayLogInConfirmation(confirmationMessage);

    }

    public void displayLogInConfirmation(String confirmationMessage){
        serverTextArea.setWrapText(true);
        serverTextArea.setText(confirmationMessage+"\n");
    }

    public void startSoilSensor(ActionEvent e) throws IOException{
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {


        // Create 10 pieces of SoilSensorInfo to send to Server
        ArrayList<SoilSensor.SoilSensorInfo> soilSensorInfos = new ArrayList<>();
        soilSensorInfos.add(new SoilSensor.SoilSensorInfo(3, 5, 21));// 1
        soilSensorInfos.add(new SoilSensor.SoilSensorInfo(2, 5, 21));// 2
        soilSensorInfos.add(new SoilSensor.SoilSensorInfo(1, 5, 21));// 3
        soilSensorInfos.add(new SoilSensor.SoilSensorInfo(0, 5, 21));// 4
        soilSensorInfos.add(new SoilSensor.SoilSensorInfo(1, 5, 21));// 5
        soilSensorInfos.add(new SoilSensor.SoilSensorInfo(2, 5, 20));// 6
        soilSensorInfos.add(new SoilSensor.SoilSensorInfo(3, 5, 20));// 7
        soilSensorInfos.add(new SoilSensor.SoilSensorInfo(4, 5, 20));// 8
        soilSensorInfos.add(new SoilSensor.SoilSensorInfo(5, 5, 20));// 9
        soilSensorInfos.add(new SoilSensor.SoilSensorInfo(5, 5, 20));// 10

        SoilSensor soilSensor = new SoilSensor("localhost", 8500, 9191, "soil.sensor.service",
                soilSensorTextArea);

        // Invoke the client streaming RPC
        soilSensor.SoilSensorService(soilSensorInfos);

    });
    }

    @FXML
    public void requestSystemHealthCheck(ActionEvent e) throws IOException {
        serverTextArea.setWrapText(true);
        MobilePhone mobilePhone = new MobilePhone("localhost", 8500,9193,"mobile.phone.service", serverTextArea);
        mobilePhone.MobilePhoneRequest();
    }

    @FXML
    void startIrrigationSystem(ActionEvent event) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {

        irrigationSystemTextArea.setWrapText(true);
        // create 5 pieces of IrrigationStatus to send to Server
        ArrayList<IrrigationSystem.IrrigationStatus> irrigationStatusData = new ArrayList<>();

        irrigationStatusData.add(new IrrigationSystem.IrrigationStatus("on",10));
        irrigationStatusData.add(new IrrigationSystem.IrrigationStatus("on",9));
        irrigationStatusData.add(new IrrigationSystem.IrrigationStatus("on",8));
        irrigationStatusData.add(new IrrigationSystem.IrrigationStatus("on",7));
        irrigationStatusData.add(new IrrigationSystem.IrrigationStatus("on",6));
        irrigationStatusData.add(new IrrigationSystem.IrrigationStatus("on",5));
        irrigationStatusData.add(new IrrigationSystem.IrrigationStatus("on",4));
        irrigationStatusData.add(new IrrigationSystem.IrrigationStatus("on",3));
        irrigationStatusData.add(new IrrigationSystem.IrrigationStatus("on",2));
        irrigationStatusData.add(new IrrigationSystem.IrrigationStatus("on",1));

        // create a MobilePhone instance
        IrrigationSystem irrigationSystem = new IrrigationSystem("localhost", 8500,9192,"irrigation.system.service", irrigationSystemTextArea);

        //invoke the bidirectional RPC
        irrigationSystem.InstructIrrigationSystem(irrigationStatusData);

        });
    }
}
