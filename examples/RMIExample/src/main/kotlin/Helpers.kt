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