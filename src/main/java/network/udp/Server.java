package network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class Server {
    /**
     * Start multiple instances of this MainClass to bind multiple ports for
     * listening, waiting for clients' message. Need to provide a port in CLI
     * for this server to bind.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            startServer(Integer.valueOf(args[0]));
        } else {
            startServer(6666);
        }
    }

    public static void startServer(int port) throws IOException {
        try (DatagramSocket dSocket = new DatagramSocket(port)) {
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                dSocket.receive(packet);

                String s = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
                System.out.println("[Server]: " + s);

                byte[] resp = "ACK\n".getBytes(StandardCharsets.UTF_8);
                packet.setData(resp);
                dSocket.send(packet);
            }
        }
    }
}
