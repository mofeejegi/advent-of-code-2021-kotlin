package day17

import readInputLine

val coord = readInputLine("Day17_Input2", 17).drop(13).split(",").map { it.trim() }
val xStart = coord.first().split("..").first().drop(2).toInt()
val xEnd = coord.first().split("..").last().toInt()
val yStart = coord.last().split("..").first().drop(2).toInt()
val yEnd = coord.last().split("..").last().toInt()
