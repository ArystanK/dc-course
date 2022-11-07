import java.io.File

fun main() {
    val file = File("test.txt")
    file.writeText((1..1000000).joinToString(" "))
}