package network.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    /**
     * After starting this app, use telnet command to connect 8189 port in localhost.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ServerSocket helloSocket = new ServerSocket(8189);
        Socket s = helloSocket.accept();

        InputStream inStream = s.getInputStream();
        OutputStream outStream = s.getOutputStream();

        Scanner in = new Scanner(inStream, "UTF-8");
        PrintWriter out = new PrintWriter(new OutputStreamWriter(outStream, "UTF-8"), true);

        out.println("Hello, Enter bye to quit.");

        boolean done = false;
        while (!done && in.hasNextLine()) {
            String recLine = in.nextLine().toUpperCase();
            out.println("[Server]: " + recLine);
            if (recLine.trim().equals("BYE")) {
                done = true;
            }
        }

        in.close();
        out.close();

        s.close();
        helloSocket.close();
    }
}
