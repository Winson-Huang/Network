package network.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ThreadedServer {
    /**
     * After starting this app, use telnet command to connect 8189 port in localhost.
     * @param args
     */
    public static void main(String[] args) {
        try (ServerSocket welcomeSocket = new ServerSocket(8189)) {
            int i = 1;
            while (true) {
                Socket s = welcomeSocket.accept();
                System.out.println("Connection Number: " + i);
                Runnable r = new EchoHandler(s);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * EchoHandler implements Runnable
 */
class EchoHandler implements Runnable {
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

