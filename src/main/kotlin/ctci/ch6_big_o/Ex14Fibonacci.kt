package ctci.ch6_big_o

import kotlin.system.measureTimeMillis


// O(2^N)
// in general it is O(branch^depth)
fun fib(n: Int): Int {
    if (n <= 1) return 1
    return fib(n - 1) + fib(n - 2)
}


// O(N)
fun fibMem(n: Int): Int {
    val mem = MutableList(n + 1) { 0 }
    return fibMemAux(n, mem)
}

fun fibMemAux(n: Int, mem: MutableList<Int>): Int {
    if (n <= 1) return 1
    if (mem[n] > 0) return mem[n]

    mem[n] = fibMemAux(n - 1, mem) + fibMemAux(n - 2, mem)
    return mem[n]
}



fun main(){
    val timeInMillis = measureTimeMillis {
        val result = fib(40)
        println(result)

    }
    println(timeInMillis)

    val timeInMillis2 = measureTimeMillis {
        val result2 = fibMem(40)
        println(result2)

    }
    println(timeInMillis2)
}