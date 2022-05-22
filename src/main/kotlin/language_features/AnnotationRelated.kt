package language_features

import org.junit.Rule
import org.junit.rules.TemporaryFolder

//An annotation allows you to associate additional metadata with a declaration.
// The metadata can then be accessed by tools that work with source code,
// with compiled class files, or at runtime, depending on how the annotation is configured.


// apply annotation
@Deprecated("Use removeAt(index) instead.", ReplaceWith("removeAt(index)"))
fun remove(index: Int) {

}
/**Annotation can have parameter of the following types
 * primitive/string/enums/class reference/ other annotation/ array thereof
 * refer class by @MyAnnotation(MyClass::class)
 * refer other annotation by not using, dont need @ before the name, just like the ReplaceWith in above
 * refer array by @RequestMapping(path = arrayOf("/foo", "/bar"))
 * Annotation need to be known at compile time
 */

/**
 * Annotation target
 * 1 kotlin declaration corresponds to multiple Java declaration
 * by default the annotation is applied to the corresponding field
 * here a few example
 *  property - java cant be applied with this use-site
 *  field - the field generated
 *  get - property getter
 *  set - property setter
 */

/**
 * Controlling Java API with annotation
 *  @JvmName Specifies the name for the Java class or method which is generated from this element.
 *  @JvmStatic Specifies that an additional static method needs to be generated from this element if it's a function.
 *  If this element is a property, additional static getter/setter methods should be generated.
 *  @JvmOverloads instructs the Kotlin compiler to generate overloads for a function that has default parameter values.
 *  data class Event(var name: String? = "", var date: Date? = Date(), var isPrivate: Boolean = false)
 *  with above annotation, overloaded constructor is also generated so we can use Event("Name"); in java side
 *  @JvmField Instructs the Kotlin compiler not to generate getters/setters for this property and expose it as a field.
 */


class HasTempFolder {
    @get:Rule
    val folder = TemporaryFolder()
}