package language_features

import kotlin.reflect.KClass

//src : https://medium.com/mobile-app-development-publication/in-and-out-type-variant-of-kotlin-587e4fa2944c
//create some parent/child class
open class Food{
    open fun eaten(){
        print("food eaten")
    }
}
open class FastFood : Food(){
    override fun eaten(){
        print("fastfood eaten")
    }
}
class Burger : FastFood(){
    override fun eaten(){
        print("burger eaten")
    }
}

/** covariant - e.g. Int is subtype of Number, Box<Int> is subtype of Box<Number> */
/** contravariant - e.g Int is subtype of Number, however, Comparator<Number> is a subtype of Comparator<Int> */


/** out - if generic type is only use as output, out is used*/
// its producer - you can return subtype to sth that expects its parents type

interface Production<out T> {
    fun produce(): T
}
class FoodStore : Production<Food> {
    override fun produce(): Food {
        println("Produce food")
        return Food()
    }
}

class FastFoodStore : Production<FastFood> {
    override fun produce(): FastFood {
        println("Produce fast food")
        return FastFood()
    }
}

class InOutBurger : Production<Burger> {
    override fun produce(): Burger {
        println("Produce burger")
        return Burger()
    }
}
/** assign Production<Burger> to  Production<Food> is possible */
val production1 : Production<Food> = FoodStore()
val production2 : Production<Food> = FastFoodStore()
val production3 : Production<Food> = InOutBurger()

/** in - if generic type is only use as input, in is used*/
// its consumer - i am expected to receive this type, please at least provide the subtype
interface Consumer<in T> {
    fun consume(item: T)
}

class Everybody : Consumer<Food> {
    override fun consume(item: Food) {
        item.eaten()
    }
}

class ModernPeople : Consumer<FastFood> {
    override fun consume(item: FastFood) {
        item.eaten()
    }
}

class American : Consumer<Burger> {
    override fun consume(item: Burger) {
        item.eaten()
    }
}

/** assign Consumer<Food> to  Consumer<Burger> is possible */
val consumer1 : Consumer<Burger> = Everybody()
val consumer2 : Consumer<Burger> = ModernPeople()
val consumer3 : Consumer<Burger> = American()

fun main(){
    // because you must pass in burger for consumer1,
    // it is safe to pass in burger to Consumer<Food>,
    // because burger is subtype of Food
    // hence Consumer<Burger> is supertype of Consumer<Food>
    consumer1.consume(Burger())

    // validator
    println(Validators[String::class].validate("Kotlin"))
}

/** Use site variance */

// you could copy a  subtype to a super type list
fun <T : R, R> copyData(
    source: MutableList<T>,
    destination: MutableList<R>
) {
    for (item in source) {
        destination.add(item)
    }
}


// same as the above but better,
fun <T> copyData2(
    source: MutableList<out T>,
    destination: MutableList<T>
) {
    //source.add("st" as T) // without out keyword, will warn,
    for (item in source) {
        destination.add(item)
    }

    val newList : MutableList<out Number> = mutableListOf(1)
    //newList.add(42) // will return error as the type is projected
}

// example for using star projection
// write a validator for multiple class
interface FieldValidator<in T> {
    fun validate(input: T): Boolean
}

object DefaultStringValidator : FieldValidator<String> {
    override fun validate(input: String) = input.isNotEmpty()
}

object DefaultIntValidator : FieldValidator<Int> {
    override fun validate(input: Int) = input >= 0
}

object failValidatior{

    fun fail(){
        val validators = mutableMapOf<KClass<*>, FieldValidator<*>>()

        validators[String::class] = DefaultStringValidator
        validators[Int::class] = DefaultIntValidator
        // fail becuase the is validator is of  FieldValidator<*>, the type is unknown
        //validators[String::class]!!.validate("")


        // it is unsafe to case like this, from <*> to <String>
        // the type could be error
        val stringValidator = validators[Int::class] as FieldValidator<String>

    }

}


// type-safe api, all unsafe logic is hidden in the body of hte class
// this part need further study
object Validators {
    // it is private
    private val validators =
        mutableMapOf<KClass<*>, FieldValidator<*>>()
    // the register is bound by type T
    fun <T : Any> registerValidator(
        kClass: KClass<T>, fieldValidator: FieldValidator<T>
    ) {
        validators[kClass] = fieldValidator
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T : Any> get(kClass: KClass<T>): FieldValidator<T> =
        validators[kClass] as? FieldValidator<T>
            ?: throw IllegalArgumentException(
                "No validator for ${kClass.simpleName}"
            )
}




/** if it is used both as in and out, its invariant*/
interface ProductionConsumer<T> {
    fun produce(): T
    fun consume(item: T)
}





