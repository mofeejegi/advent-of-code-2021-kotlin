package day1

fun main() {
    part1()
    part2()
}

fun part1() {
    var count = 0
    numberList.forEachIndexed { i, it ->
        if (i > 0 && it > numberList[i - 1]) {
            count++
        }
    }
    println(count)
}

fun part2() {
    var count3 = 0
    for (i in 1..numberList.size - 3) {
        if (numberList[i] + numberList[i + 1] + numberList[i + 2] > numberList[i - 1] + numberList[i] + numberList[i + 1]) {
            count3++
        }
    }
    println(count3)
}
