package foobar

fun broadcastSolution(l: List<Int>, t: Int): List<Int> {

    var sum = 0
    var offset = 0

    val sumList = MutableList(l.size) { 0 }

    l.forEachIndexed { index, value ->
        sum += value
        sumList[index] = sum
    }

    sumList.forEachIndexed { index, i ->
        sumList.forEachIndexed { index2, j ->
            val total = j - offset
            if (total > t) {
                return@forEachIndexed
            }
            if (total == t) {
                return listOf(index, index2)
            }

        }
        offset = i
    }

    return listOf(-1, -1)
}

fun broadcastSolutionBetter(l: List<Int>, t: Int): List<Int> {

    // say the target is 10
    // let say the sum up to index 3 = 6
    // the sum up to index 7 = 16
    // the array[3] to array[7] is a sub array some to 10
    // if currentSum - target = previousSum, there is a sub array
    // so we store the key - currentSum, value - index
    // and in the next run, check if (currentSum - target) as key is existed

    var sum = 0

    val sumList = MutableList(l.size) { 0 }


    val sumMap = mutableMapOf<Int,Int>()

    l.forEachIndexed { index, value ->
        sum += value
        val offset = sum - t

        if(sumMap.containsKey(offset)){
            val startIndex = sumMap[offset] as Int
            return listOf(startIndex, index)
            }

        sumMap[sum] = index

    }

//    sumList.forEachIndexed { index, i ->
//        sumList.forEachIndexed { index2, j ->
//            val total = j - offset
//            if (total > t) {
//                return@forEachIndexed
//            }
//            if (total == t) {
//                return listOf(index, index2)
//            }
//
//        }
//        offset = i
//    }

    return listOf(-1, -1)
}

fun main() {
    val input = listOf(4, 3, 10, 2, 8)
    val t = 16
    print(broadcastSolution(input,t))
    //MinWork.solution({1, 2, 2, 3, 3, 3, 4, 5, 5}, 1)
}