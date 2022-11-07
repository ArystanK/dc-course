import kotlin.collections.HashMap
import kotlin.collections.HashSet

class Occurrences(rootDirPath: String?) {
    private val occMap: HashMap<String, MutableMap<String, MutableSet<FilePosition>>> = HashMap()

    init {
        val walker = FileWalker(rootDirPath!!, this)
        walker.populateOccurrenceMap()
    }

    fun put(word: String, filePath: String, pos: FilePosition) {
        val caseInsensitiveWord = word.lowercase()
        if (occMap.containsKey(caseInsensitiveWord)) {
            val pathWithPosition = occMap[caseInsensitiveWord] ?: return
            if (pathWithPosition.containsKey(filePath)) pathWithPosition[filePath]?.add(pos)
            else pathWithPosition[filePath] = mutableSetOf(pos)
        } else {
            val emptySet = mutableSetOf(pos)
            occMap.putIfAbsent(caseInsensitiveWord, mutableMapOf(filePath to emptySet))
        }
    }

    fun distinctWords(): Int {
        return occMap.size
    }

    fun totalOccurrences(): Int {
        return occMap.values.sumOf {
            it.values.sumOf(MutableSet<FilePosition>::size)
        }
    }

    fun totalOccurrencesOfWord(word: String): Int {
        return occMap
            .getOrDefault(word, HashMap())
            .values
            .sumOf { it.size }
    }

    fun totalOccurrencesOfWordInFile(word: String, filepath: String): Int {
        return occMap
            .getOrDefault(word, HashMap())
            .getOrDefault(filepath, HashSet())
            .size
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        for ((word, occurrences) in occMap) {
            stringBuilder
                .append("\"")
                .append(word)
                .append("\" has ")
                .append(totalOccurrencesOfWord(word))
                .append(" occurrence(s):\n")
            for ((filepath, positions) in occurrences.entries) {
                for (pos in positions) stringBuilder
                    .append("   ( file: \"")
                    .append(filepath)
                    .append("\"; ")
                    .append(pos)
                    .append(" )\n")
            }
        }
        return stringBuilder.toString()
    }
}
