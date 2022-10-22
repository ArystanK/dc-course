import java.io.PrintWriter
import java.net.Socket

fun process(socket: Socket, request: HttpRequest) {
    // Print request that we received.
    println("Got request:")
    println(request.toString())
    System.out.flush()

    // To send response back to the client.
    val output = PrintWriter(socket.getOutputStream())

    // We are returning a simple web page now.
    output.println("HTTP/1.1 200 OK")
    output.println("Content-Type: text/html; charset=utf-8")
    output.println()
    output.println("<html>")
    output.println("<head><title>Hello</title></head>")
    output.println("<body><p>Hello, world!</p></body>")
    output.println("</html>")
    output.flush()
    socket.close()
}
