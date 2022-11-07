import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.util.*

internal class FileWalker(rootDirPath: String, occ: Occurrences) {
    private val occ: Occurrences
    private val rootDir: File

    init {
        this.occ = occ
        rootDir = File(rootDirPath)
        if (!rootDir.isDirectory) {
            throw FileNotFoundException(
                rootDirPath + " does not exist, " +
                        "or is not a directory."
            )
        }
    }

    fun populateOccurrenceMap() {
        try {
            populateOccurrenceMap(rootDir)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    private fun populateOccurrenceMap(fileOrDir: File) {
        if (fileOrDir.isDirectory) {
            var children = fileOrDir.listFiles()
            if (children == null) children = arrayOfNulls(0)
            for (child in children) populateOccurrenceMap(child)
        } else {
            val scanner = Scanner(fileOrDir)
            var rowIndex = 1
            while (scanner.hasNext()) {
                var colIndex = 1
                val line = scanner.nextLine()
                var wordBuilder = StringBuilder()
                for (character: Char in line.toCharArray()) {
                    if (Syntax.isInWord(character)) wordBuilder.append(character) else if (wordBuilder.isNotEmpty()) {
                        if (Syntax.isNewLine(character)) rowIndex++
                        wordBuilder = addWord(fileOrDir, rowIndex, colIndex, wordBuilder)
                    }
                    colIndex++
                }
                if (wordBuilder.isNotEmpty()) addWord(fileOrDir, rowIndex, colIndex, wordBuilder)
                rowIndex++
            }
        }
    }

    private fun addWord(fileOrDir: File, rowIndex: Int, colIndex: Int, wordBuilder: StringBuilder): StringBuilder {
        val word = wordBuilder.toString()
        val position = FilePosition(rowIndex, colIndex - word.length)
        val path = fileOrDir.path
        occ.put(word, path, position)
        return StringBuilder()
    }
}
