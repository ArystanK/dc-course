import java.io.PrintWriter
import java.net.Socket

fun process(socket: Socket, request: HttpRequest) {
    println("Got request:")
    println(request.toString())
    System.out.flush()

    val requestLine = request.requestLine.split(" ")[1]

    val output = PrintWriter(socket.getOutputStream())
    when {
        requestLine.isEmpty() || requestLine == "/" -> printSimplePage(output, listOf("Hello, world!"))
        requestLine == "/create/itemid" -> printSimplePage(output, listOf("Create something with some id"))
        requestLine == "/delete/itemid" -> printSimplePage(output, listOf("Delete something with some id"))
        requestLine == "/exec/params" -> {
            Thread.sleep(5000)
            printSimplePage(output, listOf("Execute something with some parameters"))
        }
    }
    output.flush()
    socket.close()
}

private fun printSimplePage(output: PrintWriter, content: List<String>) {
    output.println("HTTP/1.1 200 OK")
    output.println("Content-Type: text/html; charset=utf-8")
    output.println()
    output.println("<html>")
    output.println("<head><title>Hello</title></head>")
    output.println("<body>")
    content.forEach {
        output.println(it)
    }
    output.println("</body>")
    output.println("</html>")
}
