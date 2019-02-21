package org.ib.benchmark

import kotlin.reflect.KProperty

/**
 * @author Ionut Balosin [www.ionutbalosin.com / @ionutbalosin]
 * @copyright (C) 2019  Ionut Balosin
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

class Delegate {

    lateinit var value: String

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        println("$thisRef, thank you for delegating '${property.name}' to me!")
        return value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
        this.value = value
    }
}

class Wrapper {
    var property: String by Delegate()
}

fun main(args: Array<String>) {
    var wrapper = Wrapper()
    wrapper.property = "myProperty"

    println("Property value is ${wrapper.property}")
}