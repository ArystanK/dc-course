import java.io.File
import kotlin.random.Random
import kotlin.random.nextInt

fun main() {
    val file = File("input.txt")
//    file.readText().first { it == ' ' }
    file.writeText((1..1000000).map {
        if (Random.nextInt(1000) % 11 == 0) ' ' else (Random.nextInt(0x0020, 0x007F + 1).toChar())
    }.joinToString(""))
}