import java.io.File

fun main() {
    val file = File("test.txt")
    file.writeText((1..50).joinToString(" "))
}