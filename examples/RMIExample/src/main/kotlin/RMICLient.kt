import java.rmi.Naming


fun main() {
    val hostName = "localhost"
    val port = 8080
    val RMI_HOSTNAME = "java.rmi.server.hostname"
    val SERVICE_PATH = "//$hostName:$port/Service"

    try {
        System.setProperty(RMI_HOSTNAME, hostName)
        val service = Naming.lookup(SERVICE_PATH) as Service
        while (true) {
            val num = service.number
            if (num == null) {
                println("Received none!")
                break
            } else {
                println("Received: $num")
                service.addNumberOfPrimeNumbers(numberOfPrimesLessThan(num))
            }
        }
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}

fun numberOfPrimesLessThan(n: Int): Int {
    var numberOfPrimes = 0
    for (i in 1 until n) if (i.isPrime) numberOfPrimes++
    return numberOfPrimes
}

val Int.isPrime: Boolean
    get() {
        var i = 2
        while (i * i < this) {
            if (this % i == 0) return false
            i++
        }
        return true
    }