package com.xavierclavel.answers

import com.xavierclavel.answers.Day2_1.getInvalidIds
import com.xavierclavel.utils.createBigIntegerRange
import com.xavierclavel.utils.getResourceAsText
import java.math.BigInteger
import kotlin.math.pow
import kotlin.time.measureTime
import kotlin.time.measureTimedValue

object Day2_1 {

    fun getInvalidIds(range: Pair<String, String>): Set<BigInteger> {
        // Ids can only be invalid if they have an even number of digits
        if (range.first.length == range.second.length && range.first.length % 2 != 0) {
            println("${range.first} - ${range.second}: 0 invalid ids")
            return emptySet()
        }

        // Search range
        val min = range.first.takeIf { it.length % 2 == 0  }?.toBigInteger() ?: 10.0.pow(range.first.length).toInt().toBigInteger()
        val max = range.second.takeIf { it.length % 2 == 0  }?.toBigInteger() ?: 10.0.pow(range.second.length - 1).toInt().toBigInteger()
        if (min > max) {
            println("${range.first} - ${range.second}: 0 invalid ids")
        }

        val idsToSearch = createBigIntegerRange(min, max)
        val result = idsToSearch
            .map { it.toString() }
            .filter { it.length % 2 == 0 }
            .filter { it.substring(0, it.length / 2) == it.substring(it.length / 2, it.length) }
            .map { it.toBigInteger() }
            .toSet()

        println("${range.first} - ${range.second}: ${result.size} invalid ids: $result")
        return result
    }

}



fun main() {
    val data = getResourceAsText("/day2_input.csv")!!
    val inputs = data
        .split(",")
        .map { it.trim().split("-") }
        .map { it[0] to it[1] }
    val result = measureTimedValue {
        inputs.fold(mutableSetOf<BigInteger>()) { current, range ->
            return@fold current.apply { addAll( getInvalidIds(range) ) }
        }
    }

    println("Execution time: ${result.duration}")
    println("Sum of all invalid ids: ${result.value.sumOf { it }}")

}

