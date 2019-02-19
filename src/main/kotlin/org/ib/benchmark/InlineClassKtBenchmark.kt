package org.ib.benchmark

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.RunnerException
import org.openjdk.jmh.runner.options.OptionsBuilder
import java.util.concurrent.TimeUnit

/**
 * @author Ionut Balosin [www.ionutbalosin.com / @ionutbalosin]
 * @copyright (C) 2019  Ionut Balosin
 * <p>
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 3, jvmArgsAppend = [])
@State(Scope.Benchmark)
open class InlineClassKtBenchmark {

    @Param("3.5")
    private var param: Double = 0.0

    companion object {

        // @see https://github.com/Kotlin/KEEP/blob/master/proposals/inline-classes.md
        // @see https://kotlinlang.org/docs/reference/inline-classes.html

        @Throws(RunnerException::class)
        @JvmStatic
        fun main(args: Array<String>) {

            val opt = OptionsBuilder()
                    .include(InlineClassKtBenchmark::class.java.simpleName)
                    .build()

            Runner(opt).run()
        }
    }

    @Benchmark
    fun circleDiameter(): Double {
        val circle = Circle(param)
        return circle.diameter
    }
}

/**
 * Inline class limitations:
 * - Inline class must have a public primary constructor with a single value parameter
 * - Inline class must have a single read-only (val) property as an underlying value, which is defined in primary constructor
 * - Inline class cannot have init block
 * - Inline class must be final
 * - Inline class can implement only interfaces
 * - Inline class must be a toplevel class
 */
inline class Circle(private val radius: Double) {
    val diameter get() = 2 * radius
}