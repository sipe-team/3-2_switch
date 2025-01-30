package pairmatching.utils

import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets.UTF_8

/**
 * - object 는 정적 유틸리티 클래스를 만들 때 가장 적합한 방법(Java 에서는 @UtilityClass 로 선언)
 * - use 함수를 사용하면 자동으로 BufferedReader 가 닫혀서 close 를 사용할 필요가 없음.
 */
object FileReaderUtils {

    private const val MARKDOWN_EXTENSION = ".md"

    fun readMarkdownFile(filePath: String): MutableList<String> {
        val resourcePath = "$filePath$MARKDOWN_EXTENSION"

        val inputStream = javaClass.classLoader.getResourceAsStream(resourcePath)
            ?: throw IllegalStateException("Failed to read file : $resourcePath")

        return BufferedReader(InputStreamReader(inputStream, UTF_8)).use { reader ->
            reader.readLines().toMutableList()
        }
    }
}
