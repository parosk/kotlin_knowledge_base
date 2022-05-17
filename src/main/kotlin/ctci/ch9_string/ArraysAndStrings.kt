package ctci.ch9_string

class ArraysAndStrings {
    // First, compute the key's hash code, which will usually be an in t or long.
    // Note that two different keys could have the same hash code,
    // as there may be an infinite number of keys and a finite number of ints
    //Then, map the hash code to an index in the array.
    // This could be done with something like hash (key) % array_length.
    // Two different hash codes could, of course, map to the same index.
    // Alternatively, we can implement the hash table with a balanced
    // binary search tree.This gives us an O( log N) lookup time.
    // ArrayList
    // array are fixed length, but arrayList is resizable
    // even resizable, the amortized insertion time is still O(1).
    // resizing factor is 2 for java
    // need to copy item from old array to new array when resize
    // 1 + 2 + 4 + ..... n/4 + n/2 ~= n
    // so to insert n, item, it will be O(n), making each insert is O(1)
    // imagine concatenating list of n string with length x,
    // without stringbuilder, it takes
    // x+ 2x + .... nx ~= O(n^2) , because each concat copy 2 string and parse to a new string
    // with stringbuilder it works like a ArrayList
    //so the amortized insertion is O(n)
    // Rabin-Karp String Matching Algorithm
    // a algo to find substring of size m by creating hash for substring
    // then compute the hash for size m in a n-length string
    // check only the string if the hash is match, (still need to check because by have collision)
    // O(n-m+1) if no hash collision
    // O(mn) worst, in the case of every one is collision

}



// extra able right shift bit

//ushr will always put a 0 in the left most bit, while shr will put a 1 or a 0 depending on what the sign of it is.



fun main(){
    //print(uniqueString4("apple"))
    //print(checkPermutation2("local", "lloca"))
    //print(urlify3("Mr John Smith     ".toCharArray(),"Mr John Smith".length))
    print(isRotation("apple","pplea"))
}
//1.1 write a algo to determin if a string has all unique characters
// what if not additional structure

// first ask if it is ascii -> has 128 char, 7 bit per char
// extended ascii -> 8 bit,  256 char
// edge case : if ascii and the length is longer then 128, must return false
// if unicode then no, unicode has 1.4million char  A unicode character in utf8 takes 1-4 bytes.
// the first 128 char of utf8 is 1-to-1 corresponding to ascii (same )
// a minimum of 2 bytes is required by the UTF-16, also it is not compatable with utf8
fun uniqueString(input: String): Boolean {
    //O(n)
    // assuming ascii, if not ascii, a hashtable will do
    if (input.length > 128) return false
    val charSet = MutableList<Boolean>(128) { false }
    input.forEach {
        if (charSet[it.code]) {
            return false
        } else {
            charSet[it.code] = true
        }
    }
    return true
}

fun uniqueString2(input: String): Boolean {
    //O(n)
    // assuming lower case a to z,
    // create 26 0 i.e. 0000..... 000 (26 zero as boolean flag)
    var checker = 0
    input.forEach {

        val value = it - 'a'

        // assume just 8 digit
        // checker 00010000

        if(checker and (1 shl value) > 0){
            // 00010000 & 00010000 means 2 char is same
            return false
        }
        checker = checker or (1 shl value) // 00010010 <- or operation
    }
    return true
}

fun uniqueString3(input: String): Boolean {
    //O(n^2)
    // assuming no other data structure
    input.forEachIndexed { index, c ->
        input.forEachIndexed { index2, c2 ->
            if(index != index2 && c == c2){
                return false
            }
        }
    }
    return true
}

fun uniqueString4(input: String): Boolean {
    val arr = input.toCharArray()
    val sortedInput = arr.sorted().joinToString("")
    sortedInput.forEachIndexed { index, c ->
        if (index > 0 && sortedInput[index - 1] == c) {
            return false
        }
    }
    return true
}

// 1.2  Check Permutation: Given two strings, write a method to decide if one is a permutation of the other.
// ask if case sensitive, and if whitespace count
fun checkPermutation(input1:String, input2:String): Boolean{
    // given
    val sorted1 = input1.toCharArray().sorted().joinToString("")
    val sorted2 = input2.toCharArray().sorted().joinToString("")

    return sorted1 == sorted2
}

fun checkPermutation2(input1:String, input2:String):Boolean{
    val map1 = mutableMapOf<Char,Int>()
    input1.forEach {
        if(map1.containsKey(it)){
            val value = map1[it]?.plus(1)
            map1[it] = value as Int
        }else{
            map1[it] = 1
        }
    }
    val map2 = mutableMapOf<Char,Int>()
    input2.forEach {
        if(map2.containsKey(it)){
            val value = map2[it]?.plus(1)
            map2[it] = value as Int
        }else{
            map2[it] = 1
        }
    }

    return map1 == map2
}

//1.3 Write a method to replace all spaces in a string with '%2e:
fun urlify(input: String): String{
    //my solution
    // replace is of O(n)
    // recursively replace, at most  O(logn) depth
    var output = input.replace("  ", " ")
    while (output !=output.replace("  ", " ")) {
        output = output.replace("  ", " ")
    }
    output= output.trim()
    return output.replace(" ","%20")
}

fun urlify2(input: String): String {
    //my solution
    // use string buffer
    //additional information , stringBuffer is synchronized, stringbuilder is not
    var output = StringBuilder()
    val trimmedInput = input.trim()
    trimmedInput.forEachIndexed { index, c ->
        if (c != ' ') {
            output.append(c)
        }

        if (index != 0 && c == ' ' && trimmedInput[index - 1] != ' ') {
            output.append("%20")
        }

    }
    return output.toString()

}

fun urlify3(str: CharArray, strSize: Int): String {

    if (str.size == strSize)
        return String(str)

    val requiredSize = strSize + (0 until strSize).count { str[it] == ' ' } * 2

    var targetIdx = requiredSize - 1
    for (idx in strSize - 1 downTo 0)
        if (str[idx] != ' ') {
            str[targetIdx--] = str[idx]
        }
        else {
            str[targetIdx] = '0'
            str[targetIdx - 1] = '2'
            str[targetIdx - 2] = '%'
            targetIdx -= 3
        }

    return String(str)
}

fun urlify4(str: String, strSize: Int): String {
    // run time: O(n), space: O(n)
    val replacementStr = "%20"
    val sb = StringBuilder()

    for (idx in 0 until strSize) { if (str[idx] != ' ') sb.append(str[idx]) else sb.append(replacementStr) }

    return sb.toString()
}

//1.4 PalindromePermutation
fun isPalindromePermutation(input: String): Boolean {
    // use hashmap
    //this is copied from https://github.com/careercup/CtCI-6th-Edition-Kotlin/blob/0ab6542eebb9862a8f5f4e260ea444627efa82b4/src/Chapter01/_04_palindrome_perm/IsPalindromePerm.kt
    val strMap = HashMap<Char, Int>()

    input.replace("[^A-Za-z]".toRegex(), "").forEach { strMap[it] = (strMap[it] ?: 0) + 1 }

    val oddCharacters = strMap.count { it.value.rem(2) == 1 }

    return oddCharacters <= 1
}
// can also create a bit vector
// iterate the string
// toggle the bit
// check if there is at most 1
// index means the value in 0 - 26
fun toggle( bitVector : Int, index :Int){
    var bit = bitVector
    var mask = 1 shl index
    if(mask and bit == 0 ){
        bit = bit or mask

    }else{
        bit = bit and mask
    }
}

//1.5 one away
//Given two strings, write a function to check if they are one edit (or zero edits) away.
// one edit include replacement/insert/remove
// basically it is quite brute force
// from original

fun oneEditReplace(s1: String, s2: String): Boolean {
    var foundDifference = false
    for (i in 0 until s1.length) {
        if (s1[i] != s2[i]) {
            if (foundDifference) {
                return false
            }
            foundDifference = true
        }
    }
    return true
}

/* Check if you can insert a character into s1 to make s2. */
fun oneEditInsert(s1: String, s2: String): Boolean {
    var index1 = 0
    var index2 = 0
    while (index2 < s2.length && index1 < s1.length) {
        if (s1[index1] != s2[index2]) {
            if (index1 != index2) {
                return false
            }
            index2++
        } else {
            index1++
            index2++
        }
    }
    return true
}

fun oneEditAway(first: String, second: String): Boolean {
    if (first.length == second.length) {
        return oneEditReplace(first, second)
    } else if (first.length + 1 == second.length) {
        return oneEditInsert(first, second)
    } else if (first.length - 1 == second.length) {
        return oneEditInsert(second, first)
    }
    return false
}


//1.6 string compress
// aabcccccaaa would become a2blc5a3
// will return original result if it is not compressed
fun compress(str: String): String? {
    val compressed = StringBuilder()
    var countConsecutive = 0
    for (i in str.indices) {
        countConsecutive++

        /* If next character is different than current, append this char to result.*/if (i + 1 >= str.length || str[i] != str[i + 1]) {
            compressed.append(str[i])
            compressed.append(countConsecutive)
            countConsecutive = 0
        }
    }
    return if (compressed.length < str.length) compressed.toString() else str
}


//1.7 rotate matrix to 90 degree
fun rotate(matrix: Array<IntArray>): Boolean {
    // O(n^2) // we have to touch all element
    if (matrix.isEmpty() || matrix.size != matrix[0].size) return false // Not a square
    val n = matrix.size
    for (layer in 0 until n / 2) {
        val last = n - 1 - layer
        for (i in layer until last) {
            val offset = i - layer
            val top = matrix[layer][i] // save top

            // left -> top
            matrix[layer][i] = matrix[last - offset][layer]

            // bottom -> left
            matrix[last - offset][layer] = matrix[last][last - offset]

            // right -> bottom
            matrix[last][last - offset] = matrix[i][last]

            // top -> right
            matrix[i][last] = top // right <- saved top
        }
    }
    return true
}

//1.8 write an algorithm such that if an element in an MxN matrix is 0, its entire row and column are set to 0
fun setZero(matrix :  Array<IntArray>): Boolean{
    // my solution,brute force
    //
    if (matrix.isEmpty()) return false //
    val emptyRow = mutableListOf<Int>()
    val emptyColumn = mutableListOf<Int>()

    matrix.forEachIndexed { columnIndex, ints ->
        ints.forEachIndexed { rowIndex, value ->
            if(value == 0){
                emptyColumn.add(columnIndex)
                emptyRow.add(rowIndex)
            }

        }
    }
    matrix.forEachIndexed { columnIndex, ints ->
        ints.forEachIndexed { rowIndex, value ->
            if(emptyRow.contains(rowIndex) || emptyColumn.contains(columnIndex)){
                matrix[columnIndex][rowIndex] = 0
            }
        }
    }
    return true
}

// official ans
// with better helper function
// also use list of bool for flag instead of using contain
fun nullifyRow(matrix: Array<IntArray>, row: Int) {
    for (j in 0 until matrix[0].size) {
        matrix[row][j] = 0
    }
}

fun nullifyColumn(matrix: Array<IntArray>, col: Int) {
    for (i in matrix.indices) {
        matrix[i][col] = 0
    }
}

fun setZeros(matrix: Array<IntArray>) {
    val row = BooleanArray(matrix.size)
    val column = BooleanArray(matrix[0].size)

    // Store the row and column index with value 0
    for (i in matrix.indices) {
        for (j in 0 until matrix[0].size) {
            if (matrix[i][j] == 0) {
                row[i] = true
                column[j] = true
            }
        }
    }

    // Nullify rows
    for (i in row.indices) {
        if (row[i]) {
            nullifyRow(matrix, i)
        }
    }

    // Nullify columns
    for (j in column.indices) {
        if (column[j]) {
            nullifyColumn(matrix, j)
        }
    }
}

//1.9
//String Rotation: Assume you have a method isSubstring
// which checks if one  word is a substring of another.
// Given two strings, S1 and S2, write code to check if S2 is a rotation of S1 using only
// one call to isSubstring (e.g., waterbottle a rotation erbottlewat).

fun isRotation(s1: String, s2: String): Boolean {
    // my solution
    // O(N) becuase foreach
    // not yet check for correctness but the waterbottle test past
    if (s1.length != s2.length) return false
    s1.forEachIndexed { index, c ->
        if (s1.substring(index, s1.length) == s2.substring(0, s1.length - index)) {
            if (s2.contains(s1.substring(0, index))) {
                return true
            }
        }
    }
    return false
}

fun isRotation2(s1: String, s2: String): Boolean {
    //O(N) for contain
    if (s1.length != s2.length) return false
    val s1s1 = s1 + s1
    return s1s1.contains(s2)
}