import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        var hostname = "localhost";
        var port = 9100;
        var RMI_HOSTNAME = "java.rmi.server.hostname";
        String SERVICE_PATH = "//" + hostname + ":" + port + "/Service";
        try {
            System.setProperty(RMI_HOSTNAME, hostname);
            var service = (Service) Naming.lookup(SERVICE_PATH);
            while (true) {
                var num = service.getNumber();
                if (num == null) {
                    System.out.println("Received none!");
                    break;
                } else {
                    System.out.println("Received: " + num);
                    service.addNumberOfPrimeNumbers(HelpersKt.numberOfPrimesLessThan(num));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
