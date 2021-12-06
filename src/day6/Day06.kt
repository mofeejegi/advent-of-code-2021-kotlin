package day6

fun main() {
    part1()
    part2()
}

fun part1() {
    countFish(80)
}

fun part2() {
    countFish(256)
}

fun countFish(days: Int) {
    val fishMap = ages.groupingBy { it }.eachCount().mapValues { it.value.toLong() }.toMutableMap()
    (-1..8).forEach { fishMap[it.toLong()] = (fishMap[it.toLong()] ?: 0) }

    repeat(days) {
        fishMap.keys.sorted().drop(1).forEach {
            fishMap[it - 1] = fishMap[it] ?: 0
        }
        fishMap[6] = (fishMap[-1] ?: 0) + (fishMap[6] ?: 0)
        fishMap[8] = (fishMap[-1] ?: 0)
    }

    println(fishMap)
    println(fishMap.values.reduce { x, y -> x + y } - (fishMap[-1] ?: 0))
}
