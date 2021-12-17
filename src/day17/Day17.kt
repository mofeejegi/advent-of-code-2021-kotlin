package day17

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

typealias Point = Pair<Int, Int>

fun main() {
    part1()
    part2()
}

fun part1() {
    shoot(0 to 0, (sqrt(xStart*2.0)).toInt() to abs(yStart)-1)
    println(peak)
}

fun part2() {
    count = 0
    for (x in (sqrt(xStart*2.0)).toInt()..xEnd) {
        for (y in yStart until abs(yStart)) {
            shoot(0 to 0, x to y)
        }
    }
    println(count)
}

private var peak = 0
private var count = 0

private fun shoot(start: Point, v: Point) {
    val dest = start.first + v.first to start.second + v.second
    val newV = max(v.first - 1, 0) to v.second - 1
    peak = max(dest.second, peak)

    // Overshot
    if (dest.first > xEnd || dest.second < yStart) {
        return
    }

    if (!isWithinTarget(dest)) {
        shoot(dest, newV)
    } else {
        ++count
    }
}

private fun isWithinTarget(p: Point) = p.first in xStart..xEnd && p.second in yStart .. yEnd
