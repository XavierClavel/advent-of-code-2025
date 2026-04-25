package com.xavierclavel.utils

import java.math.BigInteger

fun getResourceAsText(path: String): String? = object {}.javaClass.getResource(path)?.readText()

fun createBigIntegerRange(start: BigInteger, end: BigInteger): Sequence<BigInteger> =
    generateSequence(start) { current ->
        if (current < end) current + BigInteger.ONE else null
    }

fun String.splitByLength(substringSize: Int): List<String> =
    (0..<this.length / substringSize).map {
        this.substring(it * substringSize, (it+1) * substringSize)
    }

fun <T> Iterable<T>.indexesOfAll(predicate: (T) -> Boolean): List<T> {
    val list = mutableListOf<T>()
    this.forEach {
        if (predicate(it)) {
            list.add(it)
        }
    }
    return list
}

fun Iterable<Int>.indexesOfMax(): List<Int> {
    val max = this.maxOrNull() ?: return emptyList()
    return this.indexesOfAll { it == max }
}