package day9

fun main() {
    part1()
    part2()
}

fun part1() {
    var count = 0
    var lows = 0
    for (i in points.indices) {
        for (j in points[i].indices) {
            if (isLowAdjacent(j, i)) {
                count++
                lows += points[i][j]
            }
        }
    }

    println(count + lows)
}

fun part2() {
    val basins = mutableListOf<Int>()

    for (i in points.indices) {
        for (j in points[i].indices) {
            if (isLowAdjacent(j, i)) {
                basins.add(getNon9AdjacentValues(j, i))
            }
        }
    }
    println(basins.sortedDescending().take(3).reduce { x, y -> x * y })
}

private fun isLowAdjacent(j: Int, i: Int): Boolean {
    val point = points[i][j]
    val n = points.getOrNull(i - 1)?.getOrNull(j) ?: -1
    val s = points.getOrNull(i + 1)?.getOrNull(j) ?: -1
    val e = points.getOrNull(i)?.getOrNull(j + 1) ?: -1
    val w = points.getOrNull(i)?.getOrNull(j - 1) ?: -1

    if (n >= 0 && point >= n) return false
    if (s >= 0 && point >= s) return false
    if (e >= 0 && point >= e) return false
    if (w >= 0 && point >= w) return false

    return true
}

private fun getNon9AdjacentValues(j: Int, i: Int, map: MutableMap<Pair<Int, Int>, Boolean> = mutableMapOf()): Int {
    val n = points.getOrNull(i - 1)?.getOrNull(j) ?: -1
    val s = points.getOrNull(i + 1)?.getOrNull(j) ?: -1
    val e = points.getOrNull(i)?.getOrNull(j + 1) ?: -1
    val w = points.getOrNull(i)?.getOrNull(j - 1) ?: -1

    mapOf(Pair(j, i - 1) to n, Pair(j, i + 1) to s, Pair(j + 1, i) to e, Pair(j - 1, i) to w).forEach {
        if (it.value >= 0 && it.value < 9) {
            if (map[it.key] == null) {
                map[it.key] = true
                getNon9AdjacentValues(it.key.first, it.key.second, map)
            }
        }
    }

    return map.size
}
