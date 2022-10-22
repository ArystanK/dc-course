import java.io.BufferedReader
import java.io.IOException

data class HttpRequest(
    val requestLine: String,
    val headers: Map<String, String>,
    val messageBody: String
) {
    fun getHeader(key: String) = headers[key]

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append(requestLine).append("\n")
        for (key in headers.keys) builder.append(key).append(": ").append(headers[key]).append("\n")
        if (messageBody.isNotEmpty()) builder.append("\r\n").append(messageBody)
        return builder.toString()
    }

    companion object {
        fun parse(reader: BufferedReader): HttpRequest {
            val requestLine = reader.readLine()
            if (requestLine == null || requestLine.isEmpty()) throw IOException("Invalid Request-Line: $requestLine")
            val headers = HashMap<String, String>()
            while (reader.ready()) {
                val header = reader.readLine()
                if (header.isEmpty()) break
                val splitHeader = header.split(":")
                if (splitHeader.size <= 1) throw IOException("Invalid Header Parameter: $header")
                headers[splitHeader[0]] = splitHeader[1]
            }
            val messageBody = StringBuilder()
            while (reader.ready()) {
                val bodyLine = reader.readLine()
                messageBody.append(bodyLine).append("\r\n")
            }
            return HttpRequest(requestLine, headers, messageBody.toString())
        }
    }
}