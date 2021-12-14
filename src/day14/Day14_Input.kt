package day14

import readInput
import readInputLine

val template = readInputLine("Day14_Input", 14)
val rules = readInput("Day14_Input", 14).drop(2).associateBy({it.split(" -> ").first()}, {it.split(" -> ").last()})
