package day12

fun main() {
    part1()
    part2()
}

val vertices = mutableMapOf<String, Vertex>()

fun part1() {
    times = 0
    createGraph()
    dfs(vertices["start"]!!)
    println(times)
}

fun part2() {
    times = 0
    createGraph()
    dfsSmall(vertices["start"]!!)
    println(times)
}

var times = 0

fun createGraph() {
    paths.forEach {
        vertices[it.first] = vertices[it.first] ?: Vertex(it.first)
        vertices[it.second] = vertices[it.second] ?: Vertex(it.second)
        vertices[it.first]!!.addAdjacentVertex(vertices[it.second]!!)
    }
}

fun dfs(vertex: Vertex, visitedList: MutableList<String> = mutableListOf()) {
    if (vertex.value == "end") {
        println("$visitedList")
        times++
        return
    }

    vertex.adjVertices.filter { it != "start" }.forEach {
        if (!visitedList.contains(it)) {
            dfs(vertices[it]!!, ArrayList(visitedList).apply {
                if (vertex.value.first().isLowerCase())
                    add(vertex.value)
            })
        }
    }
}

fun dfsSmall(vertex: Vertex, visitedList: MutableList<String> = mutableListOf(), smallList: MutableList<String> = mutableListOf()) {
    if (visitedList.contains(vertex.value)) return // When you leave a visited vertex, don't return to it
    if (vertex.value == "end") {
        println("$smallList")
        times++
        return
    }

    vertex.adjVertices.filter { it != "start" }.forEach {
        if (!visitedList.contains(it)) {
            val newVisitedList = ArrayList(visitedList)
            val newSmallList = ArrayList(smallList)

            newVisitedList.apply {
                newSmallList.add(vertex.value)
                if (newSmallList.filter { x -> x.first().isLowerCase() }.groupingBy { v -> v }.eachCount().values.contains(2)) {
                    this.clear()
                    this.addAll(HashSet(newSmallList.filter { x -> x.first().isLowerCase()}))
                }
            }
            dfsSmall(vertices[it]!!, newVisitedList, newSmallList)
        }
    }
}

data class Vertex(var value: String, var adjVertices: MutableList<String> = mutableListOf()) {
    fun addAdjacentVertex(vertex: Vertex) {
        if (adjVertices.contains(vertex.value)) return
        adjVertices.add(vertex.value)
        vertex.addAdjacentVertex(this)
    }
}
