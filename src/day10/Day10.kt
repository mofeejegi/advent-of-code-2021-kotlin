package day10

fun main() {
    part1()
    part2()
}

fun part1() {
    var score = 0
    val scoreMap = mapOf(")" to 3, "]" to 57, "}" to 1197, ">" to 25137)
    syntax.forEach {
        val invalidChar = checkInvalid(it)
        if (invalidChar != null) score += (scoreMap[invalidChar] ?: 0)
    }

    println(score)
}

fun part2() {
    var score = 0L
    val scoreMap = mapOf<String, Long>("(" to 1, "[" to 2, "{" to 3, "<" to 4)
    val listOfScores = mutableListOf<Long>()
    syntax.forEach {
        val rem = checkIncomplete(it)
        if (rem.isNotEmpty()) {
            rem.reversed().forEach { brace ->
                score = ((5 * score) + (scoreMap[brace] ?: 0))
            }
            listOfScores.add(score)
            score = 0
        }
    }
    println(listOfScores.sorted()[listOfScores.size/2])
}

fun checkInvalid(line: String): String? {
    val stack = Stack()
    line.forEach {
        if (isOpeningBrace(it.toString())) {
            stack.push(it.toString())
        } else if (isClosingBrace(it.toString())) {
            val poppedOpener = stack.pop()
            if (isNotMatch(poppedOpener, it.toString())) {
                return it.toString()
            }
        }
    }
    //if (stack.read() != "") return stack.read()
    return null
}

fun checkIncomplete(line: String): List<String> {
    val stack = Stack()
    line.forEach {
        if (isOpeningBrace(it.toString())) {
            stack.push(it.toString())
        } else if (isClosingBrace(it.toString())) {
            val poppedOpener = stack.pop()
            if (isNotMatch(poppedOpener, it.toString())) {
                return listOf()
            }
        }
    }

    if (stack.read() != "") return stack.list
    return listOf()
}

fun isOpeningBrace(string: String): Boolean {
    return listOf("{", "[", "<", "(").contains(string)
}

fun isClosingBrace(string: String): Boolean {
    return listOf("}", "]", ">", ")").contains(string)
}

fun isNotMatch(openingBrace: String, closingBrace: String): Boolean {
    return closingBrace != (mapOf("(" to ")", "[" to "]", "{" to "}", "<" to ">" )[openingBrace])
}

class Stack(val list: MutableList<String> = mutableListOf()) {
    fun read(): String {
        if (list.isEmpty()) return ""
        return list.last()
    }
    fun push(string: String) {
        list.add(string)
    }
    fun pop(): String {
        if (list.isEmpty()) return ""
        return list.removeLast()
    }
}
