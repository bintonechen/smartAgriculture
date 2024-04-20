import java.io.IOException;

public class Launcher {
    public static void main(String[] args) {
        Thread serverThread = new Thread(() -> {
            try {
                Server.main(args); // start the server
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread smartAgricultureAppThread = new Thread(() -> SmartAgricultureApp.main(args)); // start the SmartAgricultureApp

        serverThread.start(); // start the server thread
        smartAgricultureAppThread.start(); // start the SmartAgricultureApp thread
    }
}
