package day2

fun main() {
    part1()
    part2()
}

fun part1() {
    var horizontal = 0
    var vertical = 0

    directions.forEach {
        val dir = it.split(" ")[0]
        val value = it.split(" ")[1].toInt()

        when (dir) {
            "forward" -> {
                horizontal += value
            }
            "up" -> {
                vertical -= value
            }
            "down" -> {
                vertical += value
            }
        }
    }

    println(horizontal * vertical)
}

fun part2() {
    var horizontal = 0
    var vertical = 0
    var aim = 0

    directions.forEach {
        val dir = it.split(" ")[0]
        val value = it.split(" ")[1].toInt()

        when (dir) {
            "forward" -> {
                horizontal += value
                vertical += aim * value
            }
            "up" -> {
                aim -= value
            }
            "down" -> {
                aim += value
            }
        }
    }

    println(horizontal * vertical)
}