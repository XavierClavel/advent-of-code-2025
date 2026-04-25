package com.xavierclavel.answers

import com.xavierclavel.answers.Day2_2.getInvalidIds
import com.xavierclavel.answers.Day3_1.findJoltage
import com.xavierclavel.utils.createBigIntegerRange
import com.xavierclavel.utils.getResourceAsText
import com.xavierclavel.utils.indexesOfMax
import com.xavierclavel.utils.splitByLength
import java.math.BigInteger
import kotlin.math.pow
import kotlin.time.measureTime
import kotlin.time.measureTimedValue

object Day3_1 {
    fun findJoltage(bank: String): Int {
        val digits = bank.toCharArray().map { it.digitToInt() }
        val maxFirstDigit = digits.take(bank.length - 1).max()
        val indexOfMaxFirstDigit = digits.indexOf(maxFirstDigit)
        val secondMaxDigit = digits.subList(indexOfMaxFirstDigit + 1, bank.length).max()
        val result = maxFirstDigit * 10 + secondMaxDigit

        println("$bank\t -> $result")
        return result
    }
}


fun main() {
    val data = getResourceAsText("/day3_input.csv")!!
    val inputs = data
        .split("\n")
        .map { it.trim() }

    val result = measureTimedValue {
        inputs.fold(mutableListOf<Int>()) { current, bank ->
            return@fold current.apply { add(findJoltage(bank) ) }
        }
    }

    println("Execution time: ${result.duration}")
    println("Total joltage: ${result.value.sumOf { it }}")

}

