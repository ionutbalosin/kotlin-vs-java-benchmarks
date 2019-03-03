package org.ib.benchmark

/**
 * @author Ionut Balosin [www.ionutbalosin.com / @ionutbalosin]
 * @copyright (C) 2019  Ionut Balosin
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
private fun String.wrap(): String = "< $this >"

private fun Int.square(): Int = this * this

fun main(args: Array<String>) {
    // @see https://kotlinlang.org/docs/reference/extensions.html
    // Extensions are resolved statically
    println("Welcome to Java Conference".wrap());
    println(12.square());
}