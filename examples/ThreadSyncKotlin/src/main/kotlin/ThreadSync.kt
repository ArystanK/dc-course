fun main(args: Array<String>) {
    val numOfThreads = if (args.size > 1) args[1].toInt() else 4
    val numOfItems = if (args.size > 2) args[2].toInt() else 100

    val queue = ThreadSafeQueue<String?>()

    // Starting consumer threads.
    for (i in 0 until numOfThreads) {
        val cons = Consumer(i, queue)
        cons.start()
    }

    // Adding items in the queue for consumers.
    for (i in 0 until numOfItems) queue.add("item $i")

    // Stopping consumers by sending them null values.
    for (i in 0 until numOfThreads) queue.add(null)
}