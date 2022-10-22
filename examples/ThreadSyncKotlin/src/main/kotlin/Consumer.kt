class Consumer<T>(
    private val id: Int,
    private val queue: ThreadSafeQueue<T>
) : Thread() {
    override fun run() {
        try {
            while (true) {
                val element = queue.pop() ?: return
                // Process element
                println("$id: get item: $element")
            }
        } catch (ex: InterruptedException) {
            ex.printStackTrace()
        }
    }
}