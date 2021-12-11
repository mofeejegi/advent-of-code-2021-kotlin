package day11

fun main() {
    part1()
    part2()
}

fun part1() {
    flash(100)
    println(flashes)
}

fun part2() {
    flash(0)
    println(times)
}

var pairMap = mutableMapOf<Pair<Int, Int>, Int>()
var flashedMap = mutableMapOf<Pair<Int, Int>, Boolean>()

var flashes = 0
var times = 0

private fun createMaps() {
    for (i in octopi.indices) {
        for (j in octopi[i].indices) {
            pairMap[Pair(j, i)] = octopi[i][j]
        }
    }
}

private fun iterateFlashes() {
    flashedMap = pairMap.keys.associateBy({ it }, { false }).toMutableMap()

    for (key in pairMap.keys) {
        pairMap[Pair(key.first, key.second)] = (pairMap[Pair(key.first, key.second)]!! + 1) % 10
    }
    for (key in pairMap.keys) {
        flashAdjacent(key.first, key.second)
    }
}

private fun flash(limit: Int) {
    createMaps()

    if (limit > 0) {
        repeat(limit) {
            iterateFlashes()
        }
    } else {
        while (flashedMap.values.filter { it }.size != flashedMap.size) {
            times++
            iterateFlashes()
        }
    }
}

private fun flashAdjacent(j: Int, i: Int, flash: Boolean = false) {
    if ((pairMap[Pair(j, i)] ?: -1) == -1) return // Return if out of bounds
    if (flashedMap[Pair(j, i)]!!) return // Return if position has already flashed others

    // If position is being flashed, then increment
    if (flash && (pairMap[Pair(j, i)]!!) != 0) pairMap[Pair(j, i)] = (pairMap[Pair(j, i)]!! + 1) % 10

    if ((pairMap[Pair(j, i)]!!) != 0) return // Return if position isn't eligible to flash others

    // Mark as flashed and then flash others
    flashedMap[Pair(j, i)] = true
    flashes++

    listOf(Pair(j - 1, i), Pair(j - 1, i - 1), Pair(j - 1, i + 1), Pair(j + 1, i),
        Pair(j + 1, i - 1), Pair(j + 1, i + 1), Pair(j, i + 1), Pair(j, i - 1)
    ).forEach {
        flashAdjacent(it.first, it.second, true)
    }
}
