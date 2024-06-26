package ctci.ch10_linked_list

data class Node<T>(var value: T, var next: Node<T>? = null) {
    override fun toString(): String {
        return if (next != null) {
            "$value -> ${next.toString()}"
        } else {
            "$value"
        }
    }
}

class LinkedListRelated {
    // link list doesnt provide constant time access to a particular index
    // it will be iterate through K elements to get the Kth element

    // runner technique
    // run 2 pointers simultaneously
    // faster pointer iterate a few times ahead compare to the slow one

}

fun main() {
//    val node = Node(6)
//    node.next = Node(1)
//    node.next?.next = Node(7)
//
//    val node2 = Node(2)
//    node2.next = Node(9)
//    node2.next?.next = Node(5)
//// (6 -> 1 -> 7) + (2 -> 9 -> 5) = 617 + 295 = 912
//    var result = reserve(node)
//    iterateNode(result)
//    //removeDub(node)
    test()

}

fun <T> printReverse(n: Node<T>?) {
    if(n == null) return
    printReverse(n.next)
    print(n.value)
}

fun  reserve(n: Node<Int>): Node<Int>{
    var head: Node<Int>? = Node(value = -1)
    var runner = head


    fun  reserveAux(n: Node<Int>?){
        if (n?.next != null) {
            reserveAux(n?.next)
        }
        if(runner?.value == -1){
            runner!!.value = n!!.value
        }else{
            runner?.next = Node(n!!.value)
            runner = runner!!.next
        }
    }
    reserveAux(n)
    return head!!
}




fun <T> iterateNode(n: Node<T>) {
    var current: Node<T>? = n
    while (current != null) {
        print(current.value)
        if (current.next != null) {
            print("->")
        }
        current = current.next
    }
}

// 2.1 remove duplicated value
fun <T> removeDub(n: Node<T>) {
    val set: HashSet<T> = HashSet()
    var pervious: Node<T>? = null
    var current: Node<T>? = n
    while (current != null) {
        println(pervious?.value)
        println(current?.value)
        println("round")
        if (set.contains(current.value)) {
            pervious?.next = current.next
        } else {
            set.add(current.value)
            pervious = current
        }
        current = current.next
    }
}

// in O(1) space but O(n^2) time
fun <T> removeDubNoBuffer(n: Node<T>) {
    var current: Node<T>? = n
    while (current != null) {
        var runner: Node<T>? = current
        while (runner?.next != null) {
            if (runner.next!!.value == current.value) {
                runner.next = runner.next!!.next
            } else {
                runner = runner.next
            }
        }
        current = current.next
    }
}


// 2.2 Return K-th to last
// change to print k-th first
fun printKthToLast(head: Node<*>?, k: Int): Int {
    if (head == null) {
        return 0
    }
    val index = printKthToLast(head.next, k) + 1
    if (index == k) {
        print(head.value.toString())
    }
    return index
}

// use aux class to do the
fun kthToLast(head: Node<*>?, k: Int): Node<*>? {
    return kthToLastAux(head, k, 0)
}

fun kthToLastAux(head: Node<*>?, k: Int, index: Int): Node<*>? {
    if (head == null) {
        return null
    }
    val node = kthToLastAux(head.next, k, index + 1)
    if (index == k) {
        return head
    }
    return node
}

// use iterative to solve  brute force
fun nthToLast(head: Node<*>?, k: Int): Node<*>? {
    var p1 = head
    var p2 = head

    for (i in 1..k) {
        if (p1 == null) return null
        p1 = p1.next
    }

    // move at same pa
    while (p1 != null) {
        p1 = p1.next
        p2 = p2!!.next
    }
    return p2
}


//2.3 delete a node in linklist given only access to that know
// solution is to copy value of next node to current know,
// then delete the next node
fun <T> deleteNode(node: Node<T>?): Boolean {
    if (node?.next == null || node == null) return false
    var nextNode = node.next
    node.value = nextNode!!.value
    node.next = nextNode.next!!.next
    return true
}

//2.4 partition
// given a node, put the smaller node infront and larger node after
fun <T : Comparable<T>> partition(start: Node<T>?, x: T): Node<T> {
    var node = start
    var head = start
    var tail = start

    while (node != null) {
        val next = node.next
        if (node.value < x) {
            node.next = head
            head = node
        } else {
            tail!!.next = node
            tail = node
        }
        node = next
    }
    tail!!.next = null
    return head!!
}

//2.5 sum list
// (7 -> 1 -> 6) + (5 ->9 -> 2) = (617 + 295) = (2 -> 1 -> 9) = 912

fun sum2List(incomingNode1: Node<Int>, incomingNode2: Node<Int>): Node<Int> {

    var node1: Node<Int>? = incomingNode1
    var node2: Node<Int>? = incomingNode2
    val start = Node(value = (node1!!.value + node2!!.value) % 10)
    var runner: Node<Int>? = start
     var carrier = (node1.value + node2.value) / 10
    while (node1?.next != null || node2?.next != null || carrier > 0) {
        runner?.next = Node(
            value = ((node1?.next?.value ?: 0) + (node2?.next?.value ?: 0) + carrier) % 10
        )
        node1 = node1?.next
        node2 = node2?.next
        runner = runner?.next
        carrier = ((node1?.value ?: 0) + (node2?.value ?: 0)) / 10
    }
   return start
}

fun sum2ListRecurse(incomingNode1: Node<Int>, incomingNode2: Node<Int>): Node<Int> {
    return sum2ListRecurseAux(incomingNode1, incomingNode2, 0)!!
}

fun sum2ListRecurseAux(incomingNode1: Node<Int>?, incomingNode2: Node<Int>?, carrier: Int): Node<Int>? {
    if (incomingNode1 == null && incomingNode2 == null && carrier == 0) return null
    val node = Node(value = ((incomingNode1?.value ?: 0) + (incomingNode2?.value ?: 0) + carrier) % 10)
    val nextCarrier = ((incomingNode1?.value ?: 0) + (incomingNode2?.value ?: 0)) / 10
    node.next = sum2ListRecurseAux(incomingNode1?.next, incomingNode2?.next, nextCarrier)
    return node
}

// what if the list is forward order
// (6 -> 1 -> 7) + (2 -> 9 -> 5) = 617 + 295 = 912

//fun sum2Forward
// my ans
fun sum2ListForward(incomingNode1: Node<Int>, incomingNode2: Node<Int>): Node<Int> {
    val reserve1 = reserve(incomingNode1)
    val reserve2 = reserve(incomingNode2)
    return  sum2ListRecurse(reserve1,reserve2)
}

// can come back to this for official ans in the book

//2.6 palindrome check if 0 -> 1 -> 2 -> 1 -> 0
// one solution is put it in the stack
// another one is reverse it and compare
// method 1 reverse and compare
// there are iterative approach and recursive approach to this question but would skip for now

fun isPalindrome(node: Node<Any>): Boolean {
    fun reverseAndClone(node: Node<Any>?): Node<Any> {
        var runner = node
        var head: Node<Any>? = null
        while (runner != null) {
            val n = Node(runner.value)
            n.next = head
            head = n
            runner = runner.next
        }
        return head!!
    }

    fun isEqual(node1: Node<Any>, node2: Node<Any>): Boolean {
        var runner1: Node<Any>? = node1
        var runner2: Node<Any>? = node2
        while (runner1 != null && runner2 != null) {
            if (runner1.value != runner2.value) {
                return false
            }
            runner1 = runner1.next
            runner2 = runner2.next
        }
        return runner1 == null && runner2 == null
    }

    val reversed = reverseAndClone(node)
    return isEqual(node, reversed)
}

//2.7 intersection
//given 2 link list, check  if the 2 list intersect , return the intersection node
// how to tell if there is intersection? if the last node is the same node,
// there is a interaction some where
// to find the interaction node, if the 2 link list is the same length, we can transverse at the same pace to check if
// it is the same node
fun test(){
    val a = Node(value = 5)
    val b = Node(value = 5)
    // check for reference
    print(a === b)
    // check for the data field
    print(a == b)
}
class Result(tail: Node<*>, size: Int) {
    var tail: Node<*>
    var size: Int

    init {
        this.tail = tail
        this.size = size
    }
}

fun getTailAndSize(list: Node<*>?): Result? {
    if (list == null) return null
    var size = 1
    var current: Node<*> = list
    while (current.next != null) {
        size++
        current = current.next!!
    }
    return Result(current, size)
}

fun getKthNode(head: Node<*>?, k: Int): Node<*>? {
    var k = k
    var current: Node<*>? = head
    while (k > 0 && current != null) {
        current = current.next
        k--
    }
    return current
}

fun findIntersection(list1: Node<*>?, list2: Node<*>?): Node<*>? {
    if (list1 == null || list2 == null) return null

    /* Get tail and sizes. */
    val result1 = getTailAndSize(list1)
    val result2 = getTailAndSize(list2)

    /* If different tail nodes, then there's no intersection. */if (result1!!.tail !== result2!!.tail) {
        return null
    }

    /* Set pointers to the start of each linked list. */
    var shorter: Node<*> = if (result1!!.size < result2!!.size) list1 else list2
    var longer: Node<*>? = if (result1.size < result2.size) list2 else list1

    /* Advance the pointer for the longer linked list by the difference in lengths. */longer = getKthNode(
        longer, Math.abs(
            result1.size - result2.size
        )
    )

    /* Move both pointers until you have a collision. */while (shorter !== longer) {
        shorter = shorter.next!!
        longer = longer!!.next
    }

    /* Return either one. */return longer
}

/**
 * 2.8 Given a circular linked list, implement an algorithm that returns
 * the node at the beginning of the loop
 * Input: A -> B -> C -> D -> E -> C[the same C as earlier)
 * Output: C
 *
 * first part : detect if linked List has a loop
 * */


