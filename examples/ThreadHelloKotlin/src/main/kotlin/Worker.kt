import java.net.Socket

class Worker(
    private val socket: Socket,
    private val request: HttpRequest,
) : Thread() {
    override fun run() {
        process(socket, request)
    }
}