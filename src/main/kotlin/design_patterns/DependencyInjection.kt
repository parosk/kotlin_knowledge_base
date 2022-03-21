package design_patterns

class Engine {
    fun start() {
        print("start")
    }
}

class CarWithoutDI {
    // class car obtain engine itself, not get from external provider
    // car and engine are tightly coupled
    private val engine = Engine()
    fun start() {
        engine.start()
    }
}

class CarConstructorInjection(private val engine: Engine) {
    // engine is injected at constructor
    fun start() {
        engine.start()
    }
}

class CarSetterInjection {
    //Certain Android framework classes such as activities and fragments are instantiated by the system, so constructor
    //injection is not possible. With field injection, dependencies are instantiated after the class is created.
    lateinit var engine: Engine

    fun start() {
        engine.start()
    }
}

fun main() {
    // No DI
    val carWithoutDI = CarWithoutDI()
    carWithoutDI.start()

    // Constructor DI
    val engine = Engine()
    val carConstructorInjection = CarConstructorInjection(engine)
    carConstructorInjection.start()

    // Setter DI
    val carSetterInjection = CarSetterInjection()
    carSetterInjection.engine = Engine()
    carSetterInjection.start()

}