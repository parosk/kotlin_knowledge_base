package foobar



fun solution(data: List<Int>, n:Int): List<Int> {

    val map = mutableMapOf<Int,Int>()
    data.forEach {
        map[it] = (map[it] ?: 0) + 1
    }
    val result = mutableListOf<Int>()
    data.forEach {
        if(map[it]!! <= n){
            result.add(it)
        }
    }
   return result
}

fun main() {
    val input = listOf(1, 2, 2, 3, 3, 3, 4, 5, 5)
    print(solution(input,1))
    //MinWork.solution({1, 2, 2, 3, 3, 3, 4, 5, 5}, 1)
}


