package org.ib.benchmark

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.infra.Blackhole
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.RunnerException
import org.openjdk.jmh.runner.options.OptionsBuilder
import java.util.Arrays.asList
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
open class SpreadOperatorKtBenchmark {

    @Param("10", "100", "1000", "10000")
    private var size: Int = 0

    private lateinit var arrayOfInteger: Array<Int> // Integer[] arrayOfInteger
    private lateinit var intArray: IntArray // int[] intArray

    @Setup
    fun setup() {
        arrayOfInteger = Array(size) { it }
        intArray = IntArray(size) { it }
    }

    @Benchmark
    fun spreadOperatorAsList(): MutableList<Int> {
        return asList(*arrayOfInteger)
    }

    @Benchmark
    fun spreadOperatorVarArgs(blackHole: Blackhole) {
        varargs(blackHole, *intArray)
    }

    private fun varargs(blackHole: Blackhole, vararg data: Int) {
        blackHole.consume(data)
    }

    companion object {

        @Throws(RunnerException::class)
        @JvmStatic
        fun main(args: Array<String>) {

            val opt = OptionsBuilder()
                    .include(SpreadOperatorKtBenchmark::class.java.simpleName)
                    .build()

            Runner(opt).run()
        }
    }

}

