package day5

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    part1()
    part2()
}

lateinit var lineMap: MutableMap<String, Int>
var multiCount = 0

fun part1() {
    lineMap = mutableMapOf()
    multiCount = 0

    for (i in lines.indices) {
        populateLine(startCoordinates[i], endCoordinates[i])
    }
    println(multiCount)
}

fun part2() {
    lineMap = mutableMapOf()
    multiCount = 0

    for (i in lines.indices) {
        populateLine(startCoordinates[i], endCoordinates[i], true)
    }
    println(multiCount)
}

private fun populateLine(start: String, end: String, includeDiagonals: Boolean = false) {
    val x1 = getX(start)
    val y1 = getY(start)
    val x2 = getX(end)
    val y2 = getY(end)

    if (x1 == x2) { // Vertical line
        for (i in min(y1, y2)..max(y1, y2)) {
            lineMap["$x1,$i"] = (lineMap["$x1,$i"] ?: 0) + 1
            if (lineMap["$x1,$i"] == 2) ++multiCount
        }
    } else if (y1 == y2) { // Horizontal line
        for (i in min(x1, x2)..max(x1, x2)) {
            lineMap["$i,$y1"] = (lineMap["$i,$y1"] ?: 0) + 1
            if (lineMap["$i,$y1"] == 2) ++multiCount
        }
    }

    if (includeDiagonals) {
        for (i in 0..abs(x1 - x2)) {
            if (x1 < x2 && y1 < y2) {
                lineMap["${x1 + i},${y1 + i}"] = (lineMap["${x1 + i},${y1 + i}"] ?: 0) + 1
                if (lineMap["${x1 + i},${y1 + i}"] == 2) ++multiCount
            }

            if (x1 > x2 && y1 < y2) {
                lineMap["${x1 - i},${y1 + i}"] = (lineMap["${x1 - i},${y1 + i}"] ?: 0) + 1
                if (lineMap["${x1 - i},${y1 + i}"] == 2) ++multiCount
            }

            if (x1 < x2 && y1 > y2) {
                lineMap["${x1 + i},${y1 - i}"] = (lineMap["${x1 + i},${y1 - i}"] ?: 0) + 1
                if (lineMap["${x1 + i},${y1 - i}"] == 2) ++multiCount
            }

            if (x1 > x2 && y1 > y2) {
                lineMap["${x1 - i},${y1 - i}"] = (lineMap["${x1 - i},${y1 - i}"] ?: 0) + 1
                if (lineMap["${x1 - i},${y1 - i}"] == 2) ++multiCount
            }
        }
    }
}

private fun getX(coord: String): Int {
    return coord.split(",")[0].toInt()
}

private fun getY(coord: String): Int {
    return coord.split(",")[1].toInt()
}
