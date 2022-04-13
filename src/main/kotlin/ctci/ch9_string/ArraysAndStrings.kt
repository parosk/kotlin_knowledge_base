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
    print(urlify3("Mr John Smith     ".toCharArray(),"Mr John Smith".length))
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

