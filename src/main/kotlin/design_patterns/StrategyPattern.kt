package design_patterns

// very coupled way
open class Duck() {
    fun swim() { /*Implementation*/
    }

    fun quack() {/*Implementation*/
    }

    fun fly() {/*Implementation*/
    }   //just added this function
}
// when a new class RubberDuck is added

class RubberDuck : Duck() {
    // need to thing what to do with the fly function!!
}


//another way is to isolate the fly() and quack() into interface
// causing multiple interface
interface Flyable {
    fun fly()
}

interface Quackable {
    fun quack()
}

open class Duck2() {
    open fun swim() { /*Implementation*/
    }
}

class RubberDuck2 : Duck2() {
    // need to thing what to do with the fly function!!
}

class RedNeckDuck2 : Duck2(), Flyable, Quackable {
    override fun fly() {
        TODO("Not yet implemented")
    }

    override fun quack() {
        TODO("Not yet implemented")
    }

    // need to thing what to do with the fly function!!
}


//Best way is to use is to put to constructor
//FlyBehavior is a interface for different fly behavior
interface FlyBehavior{
    fun fly()
}

class FlyWithWing: FlyBehavior{
    override fun fly(){
        print("fly with wing")
    }

}

class FlyNoWay: FlyBehavior{
    override fun fly(){
        print("dont fly")
    }
}

interface QuackBehavior{
    fun quack()
}

class QuackWithQuack: QuackBehavior{
    override fun quack(){
        print("quack quack")
    }
}

class QuackNoWay: QuackBehavior{
    override fun quack(){
        print("dont quack")
    }
}


// !!! Duck has interface for behavior
// and function such as quack()/fly() to call the delegated method
open class Duck3(val quackBehavior: QuackBehavior, val flyBehavior: FlyBehavior){
    fun swim(){ /*Implementation*/}
    fun quack(){
        quackBehavior.quack()
    }
    fun fly(){
        flyBehavior.fly()
    }
}

// new class are easily defined
class RubberDuck3: Duck3(QuackNoWay(),FlyNoWay()){
}

class RedNeckDuck3: Duck3(QuackWithQuack(), FlyNoWay())




