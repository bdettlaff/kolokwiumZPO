import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws IOException {
        InetAddress address = InetAddress.getLocalHost();
        System.out.println("Host name: " + address.getHostName());
        System.out.println("Host ???: " + address.getHostAddress());
        System.out.println("Host address: " + address.getAddress());
        System.out.println("Obiekt: " + address);

        InetAddress ia = InetAddress.getByName("10.136.89.162");
        System.out.println(ia.getHostName());

        URL url = new URL("http","example.com",80,"/example/exampe.html");
        System.out.println(url.getProtocol());
        System.out.println(url.getHost());
        System.out.println(url.getPort());
        System.out.println(url.getFile());
        //System.out.println(url.openStream());
        URL url2 = new URL("ftp://anonymous:anonymous@wuarchive.wustl.edu/");
        String host = url2.getHost();
        System.out.println("Co zwróci klasa URL, metoda getHost dla ftp://anonymous:anonymous@wuarchive.wustl.edu/ "+ " : " + host);

        URL url3 = new URL("http://kis.p.lodz.pl/");
        System.out.println("Co zwróci klasa URL, metoda getPort dla http://kis.p.lodz.pl/ "+ " : " + url3.getPort());

    }
}
