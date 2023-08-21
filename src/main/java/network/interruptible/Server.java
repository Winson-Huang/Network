package network.interruptible;

import network.server.ThreadedServer;

public class Server {
    // SendNumbers will send number 0-4 to clients, then start to sleep,
    // it is for demo of SocketChannel, but I failed to build a client.
    public static void main(String[] args) {
        ThreadedServer.startServer(SendNumbers.class);
    }
}
