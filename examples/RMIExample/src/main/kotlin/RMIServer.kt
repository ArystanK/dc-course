import java.io.BufferedReader
import java.io.FileReader
import java.rmi.registry.LocateRegistry


fun main() {
    val hostName = "localhost"
    val port = 8080
    val RMI_HOSTNAME = "java.rmi.server.hostname"
    val numbersInFile = ArrayList<Int>()
    try {
        System.setProperty(RMI_HOSTNAME, hostName)
        // Create service for RMI
        val service = ServiceImpl()
        val br = BufferedReader(FileReader("test.txt"))
        var line = br.readLine()
        while (line != null) {
            val tokens = line.split(" ")
            numbersInFile.add(tokens[0].toInt())
            line = br.readLine()
        }
        for (num in numbersInFile) service.addMessage(num)
        val serviceName = "Service"
        println("Initializing $serviceName")
        val registry = LocateRegistry.createRegistry(port)
        // Make service available for RMI
        registry.rebind(serviceName, service)
        println("Start $serviceName")
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}