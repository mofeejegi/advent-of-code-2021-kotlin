package day3

fun main() {
    part1()
    part2()
}

fun part1() {
    var gamma = ""
    var epsilon = ""

    for (i in 0 until bits[0].length) {
        var zeroCount = 0
        var oneCount = 0

        bits.forEach {
            if (it[i] == '0') {
                zeroCount++
            } else { // 1
                oneCount++
            }
        }

        if (oneCount > zeroCount) {
            gamma += "1"
            epsilon += "0"
        } else {
            gamma += "0"
            epsilon += "1"
        }
    }

    println(Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2))
}

fun part2() {
    val o2 = countBits(0, "o2", bits[0].length, bits)
    val co2 = countBits(0, "co2", bits[0].length, bits)

    println(Integer.parseInt(o2, 2) * Integer.parseInt(co2, 2))
}

private fun countBits(i: Int, type: String, max: Int, list: List<String>, previousChar: Char = ' '): String {
    var zeroCount = 0
    var oneCount = 0

    val filtered = if (previousChar != ' ') list.filter { it[i - 1] == previousChar } else list
    if (filtered.isEmpty()) return list[0]
    if (i == max || filtered.size == 1) return filtered[0]

    filtered.forEach {
        if (it[i] == '0') {
            zeroCount++
        } else { // 1
            oneCount++
        }
    }

    return if (type == "o2") {
        if (oneCount >= zeroCount) {
            countBits(i + 1, type, max, filtered, '1')
        } else {
            countBits(i + 1, type, max, filtered, '0')
        }
    } else {
        if (zeroCount <= oneCount) {
            countBits(i + 1, type, max, filtered, '0')
        } else {
            countBits(i + 1, type, max, filtered, '1')
        }
    }
}
