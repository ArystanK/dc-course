class Worker(private val id: Int) : Thread() {
    override fun run() {
        println("Hello, I am worker #$id")
    }
}