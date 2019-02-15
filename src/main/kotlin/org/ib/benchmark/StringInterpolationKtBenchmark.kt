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
    private var amount: Int = 0

    @Param("apples")
    private var fruits: String = ""

    @Param("1.59")
    private var price: Float = .0f

    @Param("EUR")
    private var currency: String = ""

    companion object {

        @Throws(RunnerException::class)
        @JvmStatic
        fun main(args: Array<String>) {

            // sudo java -jar target/benchmarks.jar ".*StringInterpolationBenchmark.*" -wi 10 -i 10 -r 1 -f 3 -prof perfasm:intelSyntax=true

            val opt = OptionsBuilder()
                    .include(StringInterpolationKtBenchmark::class.java.simpleName)
                    .build()

            Runner(opt).run()
        }
    }

    @Benchmark
    fun testInterpolation(): String {
        return "$amount kilogram of $fruits costs $price $currency"
    }
}
