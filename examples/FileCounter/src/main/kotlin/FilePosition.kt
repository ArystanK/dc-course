data class FilePosition(
    val line: Int,
    val column: Int,
) : Comparable<FilePosition> {
    override fun compareTo(other: FilePosition) = when {
        line < other.line -> -1
        line > other.line -> 1
        else -> column.compareTo(other.column)
    }
}
