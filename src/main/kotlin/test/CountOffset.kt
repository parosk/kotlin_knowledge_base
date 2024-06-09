package test

class CountOffset {
}

fun countOffSet(i: Int, j: Int): Int {
    var m = j / 2 * 16
    var n = 0
    n = if (j % 2 == 0) {
        //m = j/2 * 16
        m + i
    } else {
        m + 15 - i
    }
    return n
}


fun main(){
    println(countOffSet(0,0))
    println(countOffSet(5,0))
    println(countOffSet(0,1))
    println(countOffSet(7,2))
}