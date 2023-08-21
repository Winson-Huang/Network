package network.server;

import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedServer {
    /**
     * After starting this app, use telnet command to connect 8189 port in localhost.
     * @param args
     */
    public static void main(String[] args) {
        ThreadedServer.startServer(EchoHandler.class);
    }


    public static void startServer(Class<? extends Runnable> runnableClass) {
        try (ServerSocket welcomeSocket = new ServerSocket(8189)) {
            int i = 1;
            while (true) {
                Socket socket = welcomeSocket.accept();
                System.out.println("Connection Number: " + i);
                i++;

                Runnable r = runnableClass.getConstructor(Socket.class).newInstance(socket);
                Thread t = new Thread(r);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
