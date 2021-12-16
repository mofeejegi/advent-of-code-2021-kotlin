package day16

fun main() {
    part1()
    part2()
}

fun part1() {
    parseBinary(hexToBin())
    println(versionTotal)
}

fun part2() {
    val answer = parseBinaryCalculate(hexToBin())
    println("Answer = $answer")
}

private fun hexToBin(): String {
    val bin = hex.toBigInteger(16).toString(2)
    var times = 4 - (bin.length % 4)
    if (times == 4) times = 0
    val leadingZeroes = "0".repeat(times) + "0".repeat(hex.takeWhile { it == '0' }.length * 4)

    val binary = leadingZeroes + bin
    println(binary)
    return binary
}

var versionTotal = 0

private fun parseBinary(binary: String) {
    val version = binary.substring(0..2).toInt(2)
    val type = binary.substring(3..5).toInt(2)

    versionTotal += version

    if (type == 4) { // Literal
        println(binary.substring(binary.length-5 until binary.length).toInt(2))

    } else { // Operator
        val id = binary.substring(6..6)
        if (id == "0") { // 15-bit for total bits in sub-packets
            val sp = getSubPackets(binary.substring(22))
            sp.forEach {
                parseBinary(it)
            }
        } else { // 11-bit for number of sub-packets
            val sp = getSubPackets(binary.substring(18))
            sp.forEach {
                parseBinary(it)
            }
        }
    }
}

private fun parseBinaryCalculate(binary: String): Long {
    val type = binary.substring(3..5).toInt(2)

    if (type == 4) { // Literal
        var literalValue = ""
        for(it in 0 .. binary.drop(6).length step 5) {
            literalValue += binary.drop(6).substring((it+1) .. (it+4))
            if (binary.drop(6)[it] == '0') break
        }
        return (literalValue.toLong(2))

    } else { // Operator
        val id = binary.substring(6..6)
        if (id == "0") { // 15-bit for total bits in sub-packets
            val sp = getSubPackets(binary.substring(22))
            return calculate(sp, type)

        } else { // 11-bit for number of sub-packets
            val sp = getSubPackets(binary.substring(18))
            return calculate(sp, type)
        }
    }
}

private fun getSubPackets(binary: String, sp: MutableList<String> = mutableListOf()): List<String> {
    if (!binary.contains('1')) return sp

    val type = binary.substring(3..5).toInt(2)

    if (type == 4) { // Literal
        for(it in 0 .. binary.drop(6).length step 5) {
            if (binary.drop(6)[it] == '0') {
                sp.add(binary.substring(0..(6 + (it+4))))
                getSubPackets(binary.substring(6 + (it+5)), sp)
                break
            }
        }
    } else {
        val id = binary.substring(6..6)
        if (id == "0") { // 15-bit for total bits in sub-packets
            val bISP = binary.substring(7..21).toInt(2)
            sp.add(binary.substring(0..(21+bISP)))
            getSubPackets(binary.substring(22+bISP), sp)
        } else { // 11-bit for number of sub-packets
            val nOSP = binary.substring(7..17).toInt(2)
            val endIndex = getSubPackets(binary.substring(18)).take(nOSP).map { it.length }.reduce { a, b -> a + b }
            sp.add(binary.substring(0 .. (17+endIndex)))
            getSubPackets(binary.substring(18+endIndex), sp)
        }

    }

    return sp
}

private fun calculate(subPackets: List<String>, type: Int): Long {
    val values = subPackets.map { parseBinaryCalculate(it) }
    return when (type) {
        0 -> values.sum()
        1 -> values.reduce { a, b -> a * b }
        2 -> values.minOrNull() ?: 0L
        3 -> values.maxOrNull() ?: 0L
        5 -> if (values[0] > values[1]) 1L else 0L
        6 -> if (values[0] < values[1]) 1L else 0L
        7 -> if (values[0] == values[1]) 1L else 0L
        else -> 0L
    }
}
