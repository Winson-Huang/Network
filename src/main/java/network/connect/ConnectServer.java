package network.connect;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ConnectServer {
    /**
     * If no CLI argument, it will wait for a long time after reading the time,
     * if specify -t option, socket will set reading timeout, so it will exit
     * immediately after reading time.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        String hostname = "time-a-g.nist.gov";
        int port = 13;
        int timeout = 10_000;

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(hostname, port), timeout * 6);

        if (args.length > 0 && args[0].equals("-t")) {
            socket.setSoTimeout(timeout);
        }

        try (
            Scanner in = new Scanner(socket.getInputStream(), "UTF-8")
        ) {
            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
            }
        } catch (InterruptedIOException e) {
            System.out.println("timeout");
        } 

        socket.close();
    }
}
