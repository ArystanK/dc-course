fun main(args: Array<String>) {
    val numOfThreads = if (args.size > 1) args[1].toInt() else 4
    for (i in 0 until numOfThreads) {
        val worker = Worker(i)
        worker.start()
    }
}