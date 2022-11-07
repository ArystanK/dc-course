object Syntax {
    fun isInWord(ch: Char) =
        ch.code == 0x2019 || ch.code == 0x27 || ch == '-' || ch in '0'..'9' || ch.isLetter()

    fun isNewLine(ch: Char) = ch == '\n'
}