import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.ServerSocket
import java.nio.charset.StandardCharsets

fun main(args: Array<String>) {
    // Port number for http request
    val port = if (args.size > 1) args[1].toInt() else 8080
    // The maximum queue length for incoming connection
    val queueLength = if (args.size > 2) args[2].toInt() else 50

    try {
        ServerSocket(port, queueLength).use { serverSocket ->
            println("Web Server is starting up, listening at port $port.")
            println("You can access http://localhost:$port now.")
            while (true) {
                // Make the server socket wait for the next client request
                val socket = serverSocket.accept()
                println("Got connection!")

                // To read input from the client
                val input = BufferedReader(InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))
                try {
                    // Get request
                    val request = HttpRequest.parse(input)

                    // Process request
                    process(socket, request)
                } catch (ex: IOException) {
                    ex.printStackTrace()
                }
            }
        }
    } catch (ex: IOException) {
        ex.printStackTrace()
    } finally {
        println("Server has been shutdown!")
    }
}