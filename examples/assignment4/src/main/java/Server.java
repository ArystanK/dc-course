import java.io.FileReader;
import java.math.BigInteger;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        try {
            System.setProperty(RMI_HOSTNAME, hostName);

            // Create service for RMI
            Scanner scanner = new Scanner(new FileReader("test.txt"));
            Service service1 = new ServiceImpl();
            while (scanner.hasNext()) {
                service1.sendNumber(new BigInteger(scanner.next()));
            }
            String serviceName1 = "Service1";
            System.out.println("Initializing " + serviceName1);

            Registry registry = LocateRegistry.createRegistry(port);
            registry.rebind(serviceName1, service1);
            System.out.println("Start " + serviceName1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}