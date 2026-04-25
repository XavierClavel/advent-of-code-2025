package com.xavierclavel.answers

import com.xavierclavel.utils.getResourceAsText
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.sign


object Day1_2 {
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
    var currentPosition = Day1_2.START_INDEX

    inputs.forEach {
        val previousPosition = currentPosition
        val newOutOfBoundPosition = currentPosition + Day1_2.toDelta(it)
        currentPosition = newOutOfBoundPosition.mod(Day1_2.DIAL_SIZE)

        // Counts zeros passed through but not stopped on while rotating the dial
        val zerosByRotation = when {
            newOutOfBoundPosition == 0 -> 0 // No rotation through zero
            newOutOfBoundPosition > 0 -> newOutOfBoundPosition / Day1_2.DIAL_SIZE // Rotation through zero for each hundred
            previousPosition == 0 -> - newOutOfBoundPosition / Day1_2.DIAL_SIZE // Rotation through zero for each hundred
            else -> (- newOutOfBoundPosition / Day1_2.DIAL_SIZE) + 1 // + 1 to go from positive to negative
        }

        // Counts the zero the dial stops on
        val zerosByPosition = if (newOutOfBoundPosition == 0) 1 else 0

        val newZeros = zerosByRotation + zerosByPosition
        zeroCounts += newZeros
        println("$previousPosition\t + $it\t -> $currentPosition:\t $newZeros zeros")
    }

    println("Zero count: $zeroCounts")
}


