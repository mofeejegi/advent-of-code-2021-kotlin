package day11

import readInput

val octopi = readInput("Day11_Input", 11).map { it.toCharArray().map { c -> c.toString().toInt() } }
