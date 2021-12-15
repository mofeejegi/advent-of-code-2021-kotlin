package day15

typealias Point = Pair<Int, Int>

fun main() {
    part1()
    part2()
}

fun part1() {
    shortestPath(risks)
}

fun part2() {
    shortestPath(enlarge(risks))
}

private fun getNextPath(risks: List<List<Int>>, j: Int, i: Int): List<Point> = listOf(
    j - 1 to i,
    j to i - 1,
    j + 1 to i,
    j to i + 1
).filter { (x, y) -> x >= 0 && y >= 0 && x < risks.first().size && y < risks.size }

private fun shortestPath(risks: List<List<Int>>) {
    val smallestRisk = mutableMapOf<Point, Int>()
    val smallestRiskPreviousPoint = mutableMapOf<Point, Point>()

    val unvisited = mutableMapOf<Pair<Int, Int>, Boolean>()
    val visited = mutableMapOf<Pair<Int, Int>, Boolean>()

    var currentPoint: Point? = 0 to 0 //Pair(0, 0)
    smallestRisk[currentPoint!!] = 0

    while (currentPoint != null) {
        visited[currentPoint] = true
        unvisited.remove(currentPoint)

        getNextPath(risks, currentPoint.first, currentPoint.second).forEach {
            if (!visited.containsKey(it))
                unvisited[it] = true

            val currentRisk = smallestRisk[currentPoint]!! + risks[it.second][it.first]

            if (smallestRisk[it] == null || currentRisk < smallestRisk[it]!!) {
                smallestRisk[it] = currentRisk
                smallestRiskPreviousPoint[it] = currentPoint!!
            }
        }

        if (getNextPath(risks, currentPoint.first, currentPoint.second).isEmpty()) break

        currentPoint = unvisited.keys.minByOrNull {
            smallestRisk[it]!!
        }
    }

    println(smallestRisk[Pair(risks.first().size-1, risks.size-1)])
}

private fun enlarge(list: List<List<Int>>): List<List<Int>> {
    val horizontalEnlarge = mutableListOf<MutableList<Int>>()

    list.forEachIndexed { i, inner ->
        horizontalEnlarge.add(mutableListOf())
        (0..4).forEach { times ->
            inner.forEach {
                horizontalEnlarge[i].add(if (it + times >= 10) ((it + times) - 9) else (it + times))
            }
        }
    }

    val fullEnlarge = ArrayList(horizontalEnlarge)

    (1..4).forEach { times ->
        list.indices.forEach { i ->
            fullEnlarge.add(mutableListOf())
            for (it in fullEnlarge[i]) {
                fullEnlarge[fullEnlarge.size-1].add(if (it + times >= 10) ((it + times) - 9) else (it + times))
            }
        }
    }

    return fullEnlarge
}
