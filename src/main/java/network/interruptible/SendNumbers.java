package network.interruptible;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SendNumbers implements Runnable {

    private Socket s;

    public SendNumbers(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try (OutputStream outStream = s.getOutputStream()) {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), false);

            for (int i = 0; i < 5; i++) {
                out.println(i);
                out.flush();
                Thread.sleep(100);
            }

            while (true) {
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
