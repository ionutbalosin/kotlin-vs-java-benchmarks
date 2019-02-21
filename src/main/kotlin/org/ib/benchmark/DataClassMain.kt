package org.ib.benchmark;

/**
 * @author Ionut Balosin [www.ionutbalosin.com / @ionutbalosin]
 * @copyright (C) 2019  Ionut Balosin
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
class Conference(val name: String, val city: String, val year: Int)

data class Talk(val name: String, val rating: Double)

fun main(args: Array<String>) {
    val conference = Conference("Java", "Fallgate", 2030)
    println("Welcome to ${conference.name} conference from ${conference.city} city in ${conference.year} year")

    //@see https://kotlinlang.org/docs/reference/data-classes.html
    // Component functions generated for data classes enable their use in destructuring declarations
    val talk = Talk("Kotlin under the hood", 10.0)
    val (name, rating) = talk
    println("Talk $name has a rating of $rating out of 10")
}
