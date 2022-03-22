package ctci.ch6_big_o

import com.sun.source.tree.BinaryTree

fun main(){
    val list = mutableListOf(1,2,3,4,5)
    list.reverse()
    print(list)
}


// O(N)
private fun <E> MutableList<E>.reverse() {
    // integer division result in floor
    for (i in 0..this.size / 2) {
        val otherIndex = this.size - 1 - i
        val temp = this[otherIndex]
        this[otherIndex] = this[i]
        this[i] = temp
    }
}
