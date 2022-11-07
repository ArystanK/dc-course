import java.rmi.Remote


interface Service : Remote {
    val number: Int?
    fun addMessage(num: Int)
    fun addNumberOfPrimeNumbers(num: Int)
}