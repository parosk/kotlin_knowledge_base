package language_features


/**
 * Class delegation is kotlin way to deal with
 * composition over inheritance
 * instead of inherit to use function in interface, pass object that interited it in to the class,
 * then call the function there.
 * the compiler handle the inherit internally without us caring it
 * by is the syntax sugar so we can skip some code
 *
 */
interface SuperPower {
    fun use()
}

class SuperSpeed : SuperPower {
    override fun use() {
        println("run fast")
    }
}

class SuperStrength : SuperPower {
    override fun use() {
        println("great force")
    }
}

class HeroNoDelegation(private val superPower: SuperPower) : SuperPower {

    override fun use() {
        superPower.use()
    }
}

class HeroWithDelegation(superPower: SuperPower) : SuperPower by superPower

fun main() {
    val superSpeed = SuperSpeed()
    val flash = HeroNoDelegation(superSpeed)
    flash.use()

    val superStrength = SuperStrength()
    val superman = HeroWithDelegation(superStrength)
    superman.use() //directly use interface's method
}