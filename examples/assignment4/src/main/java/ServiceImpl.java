import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ServiceImpl extends UnicastRemoteObject implements Service {
    private final BlockingQueue<BigInteger> queue;
    ArrayList<BigInteger> numberOfPrimeNumbers = new ArrayList<>();
    static long startTime = 0, endTime = 0;
    boolean firstProcessStarted = false;

    public ServiceImpl() throws RemoteException {
        super();
        queue = new LinkedBlockingQueue<>();
    }

    @Override
    public BigInteger getNumber() throws RemoteException {
        //recording start time
        if (!firstProcessStarted) {
            startTime = System.nanoTime();
        }
        //sign that process has started (for time elapse measurement)
        firstProcessStarted = true;
        return queue.poll();
    }

    @Override
    public void sendNumber(BigInteger num) throws RemoteException {
        queue.add(num);
    }

    @Override
    public void sendNumberOfPrimes(BigInteger num) throws RemoteException {
        System.out.println("Queue consists of: " + queue);
        numberOfPrimeNumbers.add(num);
        if (queue.isEmpty()) {
            try {
                Thread.sleep((long) 11.11);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BigInteger sum = BigInteger.ZERO;
            for (BigInteger numbers : numberOfPrimeNumbers) sum = sum.add(numbers);
            System.out.println("The final sum is equal to: " + sum);
            endTime = System.nanoTime();
            System.out.println("Time taken:" + (endTime - startTime));
        }
    }
}