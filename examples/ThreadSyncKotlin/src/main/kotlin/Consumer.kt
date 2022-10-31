import java.net.Socket

class Consumer(
    private val queue: ThreadSafeQueue<Pair<Socket, HttpRequest>>,
) : Thread() {
    override fun run() {
        try {
            val (socket, request) = queue.pop()
            // Process element
            process(socket, request)
        } catch (ex: InterruptedException) {
            ex.printStackTrace()
        }
    }
}