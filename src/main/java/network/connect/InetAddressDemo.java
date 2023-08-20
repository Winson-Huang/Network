package network.connect;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {
    /**
     * CLI arguments receive a hostname, it will print all IP addresses of this
     * hostname using DNS.
     * @param args
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException {

        if (args.length > 0) {
            String hostname = args[0];
            InetAddress[] addrs = InetAddress.getAllByName(hostname);
            for (InetAddress item : addrs) {
                System.out.println(item);
            }
        } else {
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println(addr);
        }
    }
}
