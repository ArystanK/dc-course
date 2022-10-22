import java.util.*

class ThreadSafeQueue<T> {
    private val queue: Queue<T> = LinkedList()
    private val lock = Object()
    val size: Int = synchronized(lock) { queue.size }

    fun add(elem: T): Unit = synchronized(lock) {
        queue.add(elem)
        lock.notify()
    }

    fun pop(): T = synchronized(lock) {
        while (queue.isEmpty()) lock.wait()
        queue.poll()
    }
}