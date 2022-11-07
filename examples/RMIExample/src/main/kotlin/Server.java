import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) {
        var hostName = "localhost";
        var port = 9100;
        var RMI_HOSTNAME = "java.rmi.server.hostname";
        var numbersInFile = new ArrayList<Integer>();
        try {
            System.setProperty(RMI_HOSTNAME, hostName);
            // Create service for RMI
            var service = new ServiceImpl();
            var br = new BufferedReader(new FileReader("test.txt"));
            var line = br.readLine();
            while (line != null) {
                var tokens = line.split(" ");
                numbersInFile.add(Integer.getInteger(tokens[0]));
                line = br.readLine();
            }
            for (var num : numbersInFile) service.addMessage(num);
            var serviceName = "Service";
            System.out.println("Initializing $serviceName");
            var registry = LocateRegistry.createRegistry(port);
            // Make service available for RMI
            registry.rebind(serviceName, service);
            System.out.println("Start $serviceName");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
