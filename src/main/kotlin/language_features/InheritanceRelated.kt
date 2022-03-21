package language_features

open class Shape1(open var length: Int, open var height: Int)
// declare new variable in primary constructor by adding 'val' in front
class Rect(val isOne: Boolean, length: Int, height: Int) : Shape1(length, height)

data class Square1(val isOne: Boolean, override var length: Int, override var height: Int) : Shape1(length + 1, height + 2) {
    fun printParentLength(): Int {
        // in the subclass, can get super class value by super. sth
        return super.length
    }
}

fun main() {
    val asq = Square1(true, 3, 5)


    print(asq.length) //3
    print((asq as Shape1).length) // still 3
    print(asq.printParentLength()) // 4
    val bsq = asq
    bsq.length = 10
    print(asq.length) //10
    print((asq as Shape1).length) // still 10
    print(asq.printParentLength()) // 4


    val csq = asq.copy()
    csq.length = 20
    print(asq.length) //10
}

