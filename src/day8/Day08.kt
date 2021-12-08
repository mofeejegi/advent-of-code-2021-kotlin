package day8

fun main() {
    part1()
    part2()
}

fun part1() {
    var uniqs = 0
    outputDigits.forEach { list ->
        list.forEach {
            when (it.length) {
                2, 3, 4, 7 -> uniqs++
            }
        }
    }
    println(uniqs)
}

fun part2() {
    var sum = 0
    var ans = ""

    outputDigits.forEachIndexed { i, list ->
        val map = createMappings(inputDigits[i])

        list.forEach {
            ans += map.indexOf(it.toCharArray().sorted().joinToString(""))
        }
        sum += ans.toInt()
        ans = ""
    }

    println(sum)
}

private fun createMappings(list: List<String>): List<String> {
    val mixMapping = mutableListOf<String>()
    repeat(10) { mixMapping.add("") }
    // 1478 235 069

    list.map { it.toCharArray().sorted().joinToString("") }.sortedBy { it.length }.forEach {
        when (it.length) {
            2 -> mixMapping[1] = it
            3 -> mixMapping[7] = it
            4 -> mixMapping[4] = it
            7 -> mixMapping[8] = it
            5 -> {
                if (it.toList().intersect(mixMapping[1].toList()).size == 2) {
                    mixMapping[3] = it // a 3
                } else if (it.toList().intersect(mixMapping[4].toList()).size == 3) {
                    mixMapping[5] = it // a 5
                } else {
                    mixMapping[2] = it // a 2
                }
            }
            6 -> {
                if (it.toList().intersect(mixMapping[1].toList()).size < 2) {
                    mixMapping[6] = it // a 6
                } else if (it.toList().intersect(mixMapping[4].toList()).size == 4) {
                    mixMapping[9] = it // a 9
                } else {
                    mixMapping[0] = it // a 0
                }
            }
        }
    }

    return mixMapping
}
