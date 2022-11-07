import java.math.BigInteger;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    BigInteger getNumber() throws RemoteException;

    void sendNumber(BigInteger num) throws RemoteException;

    void sendNumberOfPrimes(BigInteger num) throws RemoteException;
}
