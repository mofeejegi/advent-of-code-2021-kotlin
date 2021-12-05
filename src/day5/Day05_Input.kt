package day5

import readInput

val lines = readInput("Day05_Input", 5)

val startCoordinates = lines.map { it.split("->")[0].trim() }
val endCoordinates = lines.map { it.split("->")[1].trim() }
