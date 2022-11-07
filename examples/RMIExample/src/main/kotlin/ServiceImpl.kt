import java.rmi.server.UnicastRemoteObject
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

private var startTime: Long = 0
private var endTime: Long = 0

class ServiceImpl : UnicastRemoteObject(), Service {
    private val queue: BlockingQueue<Int> = LinkedBlockingQueue()
    private var numberOfPrimeNumbers = ArrayList<Int>()
    private var firstProcessStarted = false

    override val number: Int
        get() {
            if (!firstProcessStarted) {
                startTime = System.nanoTime()
            }
            firstProcessStarted = true
            return queue.poll()
        }

    override fun addMessage(num: Int) {
        queue.add(num)
    }

    override fun addNumberOfPrimeNumbers(num: Int) {
        println("Queue consists of: $queue")
        numberOfPrimeNumbers.add(num)
        if (queue.isEmpty()) {
            try {
                Thread.sleep(11.11.toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            var sum = 0
            for (numbers in numberOfPrimeNumbers) sum += numbers
            println("The final sum is equal to: $sum")
            endTime = System.nanoTime()
            println("Time taken:" + (endTime - startTime))
        }
    }
}
