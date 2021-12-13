package day12

import readInput

val paths = readInput("Day12_Input2", 12).map { Pair(it.split("-").first(), it.split("-").last()) }
