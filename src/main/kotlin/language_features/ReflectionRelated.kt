package language_features


//Sprint framework - use reflection to read all the method you wrote
// make sure that innocent change like method name dont break the code with reflection
// reflection is runing thing, not compile time
fun main(){
    val myCat = Cat("ketty",6)
    val fields = myCat.javaClass.declaredFields
    fields.forEach {
        println(it.name)
        if(it.name == "name"){
            it.isAccessible = true //change accessibilty for private final variable
            it.set(myCat,"orange")
        }
    }

    val methods = myCat.javaClass.declaredMethods
    methods.forEach {
        if(it.name == "hey"){
            it.isAccessible = true //change accessibilty for private fun
            it.invoke(myCat)
        }

        if(it.name == "a"){
            it.invoke(null)
        }
    }
    println(myCat.name)
}



class Cat(val name: String, val age: Int){
    private fun hey(){
        print("hey")
    }

    companion object {
        fun a() {
            print("a")
        }
    }
}