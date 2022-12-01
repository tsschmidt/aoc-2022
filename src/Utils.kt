import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("data", "$name.txt")
    .readLines()

fun readAsText(name: String) = File("data", "$name.txt").readText()

/**
 * Reads comma separated integers from file.
 */
fun readInts(name: String) = readInput(name).map { it.split(",") }.flatten().map { it.toInt() }

/**
 * Extension function to List<T> to chunk a list by the passed predicate.
 *
 * @param T
 * @param include - true if element that matches predicate should be included in chunk
 * @param pred - predicate to determine beginning of new chunk
 * @return List<List<T>> - chunked list
 */
fun <T> List<T>.chunkBy(include: Boolean = false, pred: (T) -> Boolean): List<List<T>> {
    val outer = mutableListOf<MutableList<T>>()
    outer.add(mutableListOf())
    forEach {
        if (pred(it)) {
            outer.add(if (include) mutableListOf(it) else mutableListOf())
        } else {
            outer.last().add(it)
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
