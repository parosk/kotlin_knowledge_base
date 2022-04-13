package ctci.ch7_bud

import kotlin.math.pow

/**
 * Step to walk through problem
 * Listen - listen to problem description
 * Example - list out and debug example
 * Brute Force - get a brute force solution as soon as possible
 * BUD Optimization - Bottleneck/Unnecessary work /Duplicated work
 * Test
 * Optimize
 *  - time vs space tradeoff
 *  - hash table
 * Walk through
 * implement
 *
 *
 *
 */

fun main() {
    cubitSumBruteForce()
}

//Print all positive integer solutions to the equation
// a^3 + b^3 =  c^3 + d^3 where a, b, c, and d are integers between 1 and 1000.
fun cubitSumBruteForce() {
    //O(n^4)
    val n = 1000
    for (a in IntRange(1, n)) {
        for (b in IntRange(1, n)) {
            for (c in IntRange(1, n)) {
                for (d in IntRange(1, n)) {
                    if (a.toDouble().pow(3.0) + b.toDouble().pow(3.0) == c.toDouble().pow(3.0) + d.toDouble()
                            .pow(3.0)
                    ) {
                        println("$a $b with $c $d")
                    }
                }
            }
        }
    }
}

fun cubitSum2() {
    //O(n^3)
    val n = 1000
    for (a in IntRange(1, n)) {
        for (b in IntRange(1, n)) {
            for (c in IntRange(1, n)) {
                //compute the supposed d value
                //remove unnecessary work
                val d = (a.toDouble().pow(3.0) + b.toDouble().pow(3.0) - c.toDouble().pow(3.0)).pow(1.toDouble() / 3)
                    .toInt()
                // validate the integer value works
                if (a.toDouble().pow(3.0) + b.toDouble().pow(3.0) == c.toDouble().pow(3.0) + d.toDouble().pow(3.0)) {
                    println("$a $b with $c $d")
                }

            }
        }
    }
}

fun cubitSum3() {
    //O(n^2)
    val n = 1000
    val resultMap = mutableMapOf<Int,MutableList<Pair<Int,Int>>>() //key: result,
    for (c in IntRange(1, n)){
        for (d in IntRange(1, n)){
           val result = (c.toDouble().pow(3.0) + d.toDouble().pow(3.0)).toInt()
            if(resultMap.containsKey(result)){
                resultMap[result]?.add(Pair(c,d))
            }else{
                resultMap[result] = mutableListOf(Pair(c,d))
            }
        }
    }

    for (a in IntRange(1, n)){
        for (b in IntRange(1, n)) {
            val result = (a.toDouble().pow(3.0) + b.toDouble().pow(3.0)).toInt()
            if (resultMap.containsKey(result)) {
                resultMap[result]?.forEach {
                    println("$a $b with ${it.first},  ${it.second}")
                }
            }
        }
    }

}

fun cubitSum4() {
    //O(n^2)
    val n = 1000
    // no need to iterate again for c,d
    val resultMap = mutableMapOf<Int, MutableList<Pair<Int, Int>>>() //key: result,
    for (a in IntRange(1, n)) {
        for (b in IntRange(1, n)) {
            val result = (a.toDouble().pow(3.0) + b.toDouble().pow(3.0)).toInt()
            if (resultMap.containsKey(result)) {
                resultMap[result]?.add(Pair(a, b))
            } else {
                resultMap[result] = mutableListOf(Pair(a, b))
            }
        }
    }
    resultMap.forEach { entry ->
        entry.value.forEach {
            println("${it.first},  ${it.second}")
        }
    }

}