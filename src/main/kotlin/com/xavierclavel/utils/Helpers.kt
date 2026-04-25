package com.xavierclavel.utils

import java.math.BigInteger

fun getResourceAsText(path: String): String? = object {}.javaClass.getResource(path)?.readText()

fun createBigIntegerRange(start: BigInteger, end: BigInteger): Sequence<BigInteger> =
    generateSequence(start) { current ->
        if (current < end) current + BigInteger.ONE else null
    }