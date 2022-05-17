package data_structures_algo

/**
 * Time complexity O(2^n)
 * space complexity O(n)
 */
fun nonTailRecuFib(n: Int): Long {
    return when (n) {
        0 -> 0
        1 -> 1
        else -> nonTailRecuFib(n - 1) + nonTailRecuFib(n - 2)
    }
}

/**
 * Time complexity O(n)
 * space complexity O(1)
 */
fun tailRecuFib(n: Int): Long {
    fun tailFibAux(n: Int, a: Long, b: Long): Long {
        if (n == 0) {
            return b
        }
        return tailFibAux(n - 1, b, a + b)
    }
    return tailFibAux(n - 1, 0, 1)
}

enum class Tower(val title: String) {
    LEFT("left"),
    MIDDLE("middle"),
    RIGHT("right")
}

fun moveDisk(source: Tower, destination: Tower) =
    println("Move ${source.title}'s top disk to ${destination.title}")

fun towerOfHanoi(size: Int, source: Tower, destination: Tower) {
    if (size > 0) {
        val tmp = (Tower.values().toList() - source - destination).first()
        towerOfHanoi(size - 1, source, tmp)
        moveDisk(source, destination)
        towerOfHanoi(size - 1, tmp, destination)
    }
}

