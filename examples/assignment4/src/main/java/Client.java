import java.math.BigInteger;
import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        String hostName = "localhost";
        int port = 8080;
        String RMI_HOSTNAME = "java.rmi.server.hostname";
        String SERVICE_PATH = "//" + hostName + ":" + port + "/Service";

        try {
            System.setProperty(RMI_HOSTNAME, hostName);
            Service service1 = (Service) Naming.lookup(SERVICE_PATH + "1");
            while (true) {
                BigInteger num1 = service1.getNumber();
                if (num1 == null) {
                    System.out.println("Received none!");
                    break;
                } else {
                    System.out.println("Received: " + num1);
                    service1.sendNumberOfPrimes(numberOfPrimesLessThan(num1));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static BigInteger numberOfPrimesLessThan(BigInteger num) {
        BigInteger counterOfPrimes = BigInteger.ZERO;
        for (var i = BigInteger.ONE; i.compareTo(num) < 0; i = i.add(BigInteger.ONE))
            if (isPrime(i)) counterOfPrimes = counterOfPrimes.add(BigInteger.ONE);
        return counterOfPrimes;
    }

    public static boolean isPrime(BigInteger num) {
        for (BigInteger i = BigInteger.valueOf(2L); i.multiply(i).compareTo(num) <= 0; i = i.add(BigInteger.ONE))
            if (num.remainder(i).compareTo(BigInteger.ZERO) == 0) return false;
        return true;
    }
}
