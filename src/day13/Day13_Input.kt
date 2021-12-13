package day13

import readInput

val points = readInput("Day13_Input", 13).map { Pair(it.split(",").first().toInt(), it.split(",").last().toInt()) }
val folds = readInput("Day13_Input_a", 13).map {
    Pair(it.substring(11 until it.length).split("=").first(), it.substring(11 until it.length).split("=").last().toInt())
}
