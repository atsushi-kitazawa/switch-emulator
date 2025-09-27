package com.atsushi.kitazawa

import kotlin.random.Random
import kotlin.random.nextInt

class MacAddressGenerator {
    companion object {
        fun generate(): String {
//            val value1 = Random.nextInt(256).toString(16)
//            val value2 = Random.nextInt(256).toString(16)
//            val value3 = Random.nextInt(256).toString(16)
//            val value4 = Random.nextInt(256).toString(16)
//            val value5 = Random.nextInt(256).toString(16)
//            val value6 = Random.nextInt(256).toString(16)
//            return (value1 + ":" + value2 + ":" + value3 + ":" + value4 + ":" + value5 + ":" + value6).uppercase()
            return (1..6)
                .map { Random.nextInt(256) }
                .joinToString(":") {
                    String.format("%02x", it)
                }
                .uppercase()
        }
    }
}