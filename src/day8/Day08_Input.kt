package day8

import readInput

val digits = readInput("Day08_Input", 8)
val inputDigits = digits.map { it.split(" | ")[0].split(" ") }
val outputDigits = digits.map { it.split(" | ")[1].split(" ") }