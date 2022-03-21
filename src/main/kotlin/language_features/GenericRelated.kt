package language_features

//src : https://medium.com/mobile-app-development-publication/in-and-out-type-variant-of-kotlin-587e4fa2944c
//create some parent/child class
open class Food
open class FastFood : Food()
class Burger : FastFood()

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
        println("Eat food")
    }
}

class ModernPeople : Consumer<FastFood> {
    override fun consume(item: FastFood) {
        println("Eat fast food")
    }
}

class American : Consumer<Burger> {
    override fun consume(item: Burger) {
        println("Eat burger")
    }
}

/** assign Consumer<Food> to  Consumer<Burger> is possible */
val consumer1 : Consumer<Burger> = Everybody()
val consumer2 : Consumer<Burger> = ModernPeople()
val consumer3 : Consumer<Burger> = American()


/** if it is used both as in and out, its invariant*/
interface ProductionConsumer<T> {
    fun produce(): T
    fun consume(item: T)
}





