package com.xavierclavel.answers

import com.xavierclavel.utils.getResourceAsText

object Day1_1 {
    const val START_INDEX = 50
    const val DIAL_SIZE = 100

    fun toDelta(s: String): Int {
        if (s.length < 2) {
            throw Exception("Invalid input: '$s'")
        }
        val operation = s[0]
        val amount = s.substring(1).toInt()

        return when (operation) {
            'R' -> amount
            'L' -> -amount
            else -> throw Exception("Invalid operation: '${s[0]}'")
        }
    }

}



fun main() {
    val data = getResourceAsText("/day1_input.txt")!!
    val inputs = data
        .split("\n")
        .map { it.trim() }
        .filter { it.isNotEmpty() }

    var zeroCounts = 0
    var currentPosition = Day1_1.START_INDEX

    inputs.forEach {
        currentPosition += Day1_1.toDelta(it)
        currentPosition = currentPosition.mod(Day1_1.DIAL_SIZE)
        println("$it -> $currentPosition")
        if (currentPosition == 0) {
            zeroCounts++
            println("Zero reached ! Current count: $zeroCounts")
        }
    }

    println("Zero count: $zeroCounts")
}

