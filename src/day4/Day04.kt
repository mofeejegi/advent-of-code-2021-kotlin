package day4

fun main() {
    part1()
    part2()
}

fun part1() {
    for (i in 5..numbers.size) {
        for (j in boards.indices step 5) {
            val bingo = checkBingo(j, numbers.subList(0, i))
            if (bingo) {
                println(calculateScore(j, i))
                return
            }
        }
    }
}

fun part2() {
    val winningBoards = mutableMapOf<Int, Boolean>()

    for (i in 5..numbers.size) {
        for (j in boards.indices step 5) {
            val bingo = checkBingo(j, numbers.subList(0, i))
            if (bingo) {
                winningBoards[j] = true
                if (winningBoards.keys.size == boards.size/5) {
                    println(calculateScore(j, i))
                    return
                }
            }
        }
    }
}

private fun checkBingo(index: Int, list: List<Int>): Boolean {
    val row1 = boards[index]
    val row2 = boards[index + 1]
    val row3 = boards[index + 2]
    val row4 = boards[index + 3]
    val row5 = boards[index + 4]

    if (row1.intersect(list).size == 5) return true
    if (row2.intersect(list).size == 5) return true
    if (row3.intersect(list).size == 5) return true
    if (row4.intersect(list).size == 5) return true
    if (row5.intersect(list).size == 5) return true

    for (i in 0 until 5) {
        if (listOf(row1[i], row2[i], row3[i], row4[i], row5[i]).intersect(list).size == 5)
            return true
    }

    return false
}

private fun calculateScore(index: Int, numbersListLastIndex: Int): Int {
    val row1 = boards[index]
    val row2 = boards[index + 1]
    val row3 = boards[index + 2]
    val row4 = boards[index + 3]
    val row5 = boards[index + 4]

    val list = numbers.subList(0, numbersListLastIndex)
    val last = list.last()

//    println("Index: $index")
//    println("List: $list")

    var totalUnmarked = 0
    for (i in 0 until 5) {
        if (!list.contains(row1[i])) totalUnmarked += row1[i]
        if (!list.contains(row2[i])) totalUnmarked += row2[i]
        if (!list.contains(row3[i])) totalUnmarked += row3[i]
        if (!list.contains(row4[i])) totalUnmarked += row4[i]
        if (!list.contains(row5[i])) totalUnmarked += row5[i]
    }

    return last * totalUnmarked
}
