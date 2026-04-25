package com.xavierclavel.answers

import com.xavierclavel.answers.Day2_2.getInvalidIds
import com.xavierclavel.utils.createBigIntegerRange
import com.xavierclavel.utils.getResourceAsText
import com.xavierclavel.utils.splitByLength
import java.math.BigInteger
import kotlin.math.pow
import kotlin.time.measureTime
import kotlin.time.measureTimedValue

object Day2_2 {

    fun getInvalidIds(range: Pair<String, String>): Set<BigInteger> {

        // Search range
        val min = range.first.toBigInteger()
        val max = range.second.toBigInteger()
        if (min > max) {
            println("${range.first} - ${range.second}: 0 invalid ids")
        }

        val idsToSearch = createBigIntegerRange(min, max)
        val result = idsToSearch
            .map { it.toString() }
            .filter { isIdInvalid(it) }
            .map { it.toBigInteger() }
            .toSet()

        println("${range.first} - ${range.second}: ${result.size} invalid ids: $result")
        return result
    }

    fun isIdInvalid(id: String): Boolean {
        val possibleDividers = 1..id.length / 2
        val dividers = possibleDividers.filter { id.length % it == 0 }
        dividers.forEach {
            val items = id.splitByLength(it)
            if (items.all { it == items[0] }) {
                return true
            }
        }
        return false
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

