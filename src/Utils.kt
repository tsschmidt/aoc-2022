import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("data", "$name.txt")
    .readLines()

/**
 * Reads comma separated integers from file.
 */
fun readInts(name: String) = readInput(name).map { it.split(",") }.flatten().map { it.toInt() }

fun groupByNewLine(input: List<String>): List<List<String>> {
    val outer = mutableListOf<List<String>>()
    var inner = mutableListOf<String>()
    input.forEach {
        if (it.isEmpty()) {
            outer.add(inner);
            inner = mutableListOf<String>()
        } else {
            inner.add(it)
        }
    }
    return outer
}

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')
