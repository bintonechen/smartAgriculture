import java.io.IOException;

public class Launcher {
    public static void main(String[] args) {
        Thread serverThread = new Thread(() -> {
            try {
                Server.main(args);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread smartAgricultureAppThread = new Thread(() -> SmartAgricultureApp.main(args));

        serverThread.start();
        smartAgricultureAppThread.start();
    }
}
