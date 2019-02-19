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
open class DefaultArgumentsKtBenchmark {

    @Param("8")
    private var param: Int = 0

    @Param("2")
    private var shift: Int = 0

    companion object {

        @Throws(RunnerException::class)
        @JvmStatic
        fun main(args: Array<String>) {

            val opt = OptionsBuilder()
                    .include(DefaultArgumentsKtBenchmark::class.java.simpleName)
                    .build()

            Runner(opt).run()
        }
    }

    @Benchmark
    fun defaultArguments(): Int {
        return expression(param)
    }

    @Benchmark
    fun namedArguments(): Int {
        return expression(param, shl_2 = shift)
    }

    private fun expression(value: Int, shl_1: Int = 0, shl_2: Int = 0, shl_3: Int = 0): Int {
        return value shl shl_1 shl shl_2 shl shl_3;
    }
}
