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
open class VarargsKtBenchmark {

    @Param("3")
    internal var param1: Int = 0
    @Param("5")
    internal var param2: Int = 0
    @Param("7")
    internal var param3: Int = 0
    @Param("9")
    internal var param4: Int = 0
    @Param("11")
    internal var param5: Int = 0
    @Param("13")
    internal var param6: Int = 0
    @Param("15")
    internal var param7: Int = 0
    @Param("17")
    internal var param8: Int = 0

    @Benchmark
    fun average(): Float {
        return getAverage(param1, param2, param3, param4, param5, param6, param7, param8)
    }

    private fun getAverage(vararg input: Int): Float {
        var sum = 0.0f
        for (item in input) {
            sum += item
        }
        return (sum / input.size)
    }

    companion object {

        @Throws(RunnerException::class)
        @JvmStatic
        fun main(args: Array<String>) {

            val opt = OptionsBuilder()
                    .include(VarargsKtBenchmark::class.java.simpleName)
                    .build()

            Runner(opt).run()
        }
    }
}
