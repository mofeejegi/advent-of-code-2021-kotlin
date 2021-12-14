package day14

fun main() {
    part1()
    part2()
}

fun part1() {
    check(10)
}

fun part2() {
    check(40)
}

fun check(steps: Int) {
    var mapped = mutableMapOf<String, Long>()
    for (i in 1 until template.length) {
        val pair = "${template[i - 1]}${template[i]}"
        mapped[pair] = (mapped[pair] ?: 0) + 1
    }

    repeat(steps) {
        val newMap = mutableMapOf<String, Long>()
        mapped.forEach { (k, v) ->
            newMap[k.first() + rules[k]!!] = (newMap[k.first() + rules[k]!!] ?: 0) + v
            newMap[rules[k] + k.last()] = (newMap[rules[k] + k.last()] ?: 0) + v
        }
        mapped = newMap
    }

    val groups = mutableMapOf<Char, Long>()
    mapped.forEach { (t, u) -> groups[t.first()] = (groups[t.first()] ?: 0) + u }
    groups[template.last()] = groups[template.last()]!! + 1
    println(groups.values.maxOrNull()!! - groups.values.minOrNull()!!)
}
