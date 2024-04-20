import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Objects;


public class SmartAgricultureApp extends Application {

    Button button;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/GUI.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Smart Agriculture Application");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
             event.consume();
            quit(primaryStage);
        });
    }

    public void quit(Stage primaryStage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit Smart Agriculture App");
        alert.setContentText("Do you want to exit?");

        if(alert.showAndWait().get() == ButtonType.OK){
            System.out.println("You successfully logged out.");
            primaryStage.close();
            System.exit(0);
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
