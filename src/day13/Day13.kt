package day13

fun main() {
    part1()
    part2()
}

var newPoints = ArrayList(points)

fun part1() {
    newPoints = ArrayList(points)
    fold(1)
    println(newPoints.size)
}

fun part2() {
    newPoints = ArrayList(points)
    fold(folds.size)
    display()
}

fun fold(times: Int) {
    folds.take(times).forEach {

        if (it.first == "y") {
            val ypoints = mutableSetOf<Pair<Int, Int>>()
            newPoints.forEach { point ->
                if (point.second < it.second)
                    ypoints.add(point)
                else if (point.second > it.second) {
                    val y = (it.second * 2) - point.second
                    ypoints.add(Pair(point.first, y))
                }
            }
            newPoints = ArrayList(ypoints)

        } else { // x
            val xpoints = mutableSetOf<Pair<Int, Int>>()
            newPoints.forEach { point ->
                if (point.first < it.second)
                    xpoints.add(point)
                else if (point.first > it.second) {
                    val x = (it.second * 2) - point.first
                    xpoints.add(Pair(x, point.second))
                }
            }
            newPoints = ArrayList(xpoints)
        }
    }
}

fun display() {
    val innerList = mutableListOf<String>() //x
    repeat(folds.last { it.first == "x" }.second) {
        innerList.add(" ")
    }
    val outerList = mutableListOf<MutableList<String>>() //y
    repeat(folds.last { it.first == "y" }.second) {
        outerList.add(ArrayList(innerList))
    }

    for (pts in newPoints) { outerList[pts.second][pts.first] = "▆" }
    outerList.forEach{ println(it) }
}
