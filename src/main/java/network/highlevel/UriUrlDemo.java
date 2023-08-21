package network.highlevel;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class UriUrlDemo {
    /**
     * specify -e option in CLI to invoke an Exception of unknown protocol
     * @param args
     * @throws URISyntaxException
     * @throws MalformedURLException
     */
    public static void main(String[] args) throws URISyntaxException, MalformedURLException {
        
        String host = "www.baidu.com";
        final String schemeHttp = "http";
        final String schemeXyz = "xyz";

        URI httpUri = new URI(schemeHttp, host, null, null);
        URI xyzUri = new URI(schemeXyz, host, null, null);

        URL url;
        if (args.length > 0 && "-e".equals(args[0])) {
            url = xyzUri.toURL(); 
        } else {
            url = httpUri.toURL();
        }

        System.out.println(url);
    }
}
