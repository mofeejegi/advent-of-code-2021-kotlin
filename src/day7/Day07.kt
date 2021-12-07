package day7

import kotlin.math.abs
import kotlin.math.min

fun main() {
    part1()
    part2()
}

fun part1() {
    val midIndex = (positions.size - 1) / 2
    println(calculateFuel(positions.sorted()[midIndex]))
}

fun part2() {
    val mean = positions.sumOf { it } / positions.size
    println(min(calculateFuel(mean, true), calculateFuel(mean + 1, true)))
}

fun calculateFuel(position: Int, crabMath: Boolean = false): Int {
    var fuel = 0
    positions.forEach {
        fuel += if (crabMath) numberSum(abs(position - it)) else abs(position - it)
    }
    return fuel
}

fun numberSum(number: Int): Int {
    if (number == 0) return number
    return number + numberSum(number - 1)
}
