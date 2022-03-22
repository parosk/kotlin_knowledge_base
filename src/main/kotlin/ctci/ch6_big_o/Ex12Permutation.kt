package ctci.ch6_big_o


//O(N* N!), for N characters in string
//there are N! end case
//each print take O(N)
fun permutation(str: String) {
    permutationAux(str, "")
}

fun permutationAux(str: String, prefix: String) {
    if (str.isEmpty()) {
        println(prefix)
        return
    }

    str.forEachIndexed { index, c ->
        val remain = str.substring(0, index) + str.substring(index + 1, str.length)
        permutationAux(remain, prefix + c)
    }
}

fun main() {
    permutation("cat")
}