import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String, day: Int) = File("src/day$day", "$name.txt").readLines()

/**
 * Read just a single line from the given input txt file
 */
fun readInputLine(name: String, day: Int): String = File("src/day$day", "$name.txt").bufferedReader().use { it.readLine() }

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
