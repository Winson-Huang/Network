package network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Client {
    /**
     * give multiple ports in CLI arguments, client will send a message to these
     * ports in localhost, default destiny port is 6666
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        int[] ports;
        if (args.length == 0) {
            ports = new int[] {6666};
        } else {
            ports = new int[args.length];
            for (int i = 0; i < args.length; i++) {
                ports[i] = Integer.valueOf(args[i]);
            }
        }
        sendUDP(ports);
    }
    
    public static void sendUDP(int[] ports) throws IOException {
        try (DatagramSocket dSocket = new DatagramSocket(6665)) {
            byte[] message = "Hello\n".getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(message, message.length);
            packet.setAddress(InetAddress.getLocalHost());

            for (int port : ports) {
                packet.setPort(port);
                dSocket.send(packet);

                byte[] buffer = new byte[1024];
                DatagramPacket receivePack = new DatagramPacket(buffer, buffer.length);
                dSocket.receive(receivePack);
                String resp = new String(buffer, StandardCharsets.UTF_8);
                System.out.println(resp);
            }
        }

    }
}
