package org.ib.benchmark

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.RunnerException
import org.openjdk.jmh.runner.options.OptionsBuilder
import java.util.concurrent.TimeUnit

/**
 * @author Ionut Balosin [www.ionutbalosin.com / @ionutbalosin]
 * @copyright (c) Ionut Balosin
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
open class StringInterpolationKtBenchmark {

    @Param("1")
    private var quantity: Int = 0

    @Param("apples")
    private var fruits: String = ""

    @Param("1.59")
    private var price: Float = .0f

    @Param("EUR")
    private var currency: String = ""

    @Param("Rectangle")
    private var shape: String = ""

    @Param("2")
    private var length: Double = 0.0

    @Param("3")
    private var width: Double = 0.0

    @Param("m2")
    private var unit: String = ""

    companion object {

        @Throws(RunnerException::class)
        @JvmStatic
        fun main(args: Array<String>) {

            val opt = OptionsBuilder()
                    .include(StringInterpolationKtBenchmark::class.java.simpleName)
                    .build()

            Runner(opt).run()
        }
    }

    @Benchmark
    fun fruitsPrice(): String {
        return "$quantity kilogram of $fruits costs $price $currency"
    }

    @Benchmark
    fun shapeArea(): String {
        return "${shape} area is ${length * width} $unit"
    }
}
