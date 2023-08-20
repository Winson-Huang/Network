package network.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * EchoHandler implements Runnable
 */
public class EchoHandler implements Runnable {
    private Socket s;

    public EchoHandler(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try (InputStream inStream = s.getInputStream();
        OutputStream outStream = s.getOutputStream()) {
            Scanner in = new Scanner(inStream, "UTF-8");
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), false);

            out.println("[Server]: " + "Enter BYE to quit.");
            out.flush();

            boolean done = false;
            while (!done && in.hasNextLine()) {
                String rec = in.nextLine().toUpperCase();
                out.println("[Server]: " + rec);
                out.flush();
                if (rec.trim().equals("BYE")) {
                    done = true;
                }
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}