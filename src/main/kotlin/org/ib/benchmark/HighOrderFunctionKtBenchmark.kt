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
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 3, jvmArgsAppend = [])
@State(Scope.Benchmark)
open class HighOrderFunctionKtBenchmark {

    @Param("1000000")
    private var param: Int = 0

    @Param("1")
    private var factor: Int = 1

    private val sumOfSquares_anonymousFunction = fun(x: Int): Int = x * x
    //private val sumOfSquares_anonymousFunction_capturing = fun(x : Int) : Int = x * x * factor

    companion object {

        @Throws(RunnerException::class)
        @JvmStatic
        fun main(args: Array<String>) {

            val opt = OptionsBuilder()
                    .include(HighOrderFunctionKtBenchmark::class.java.simpleName)
                    .build()

            Runner(opt).run()
        }
    }

    @Benchmark
    fun sumOfSquares_lambda(): Long {
        return sumOfSquares(param) {
            it * it;
        }
    }

    @Benchmark
    fun sumOfSquares_lambda_inline(): Long {
        return sumOfSquares_inline(param) {
            it * it;
        }
    }

    @Benchmark
    fun sumOfSquares_methodRef(): Long {
        return sumOfSquares(param, this::square)
    }

    @Benchmark
    fun sumOfSquares_methodRef_inline(): Long {
        return sumOfSquares_inline(param, this::square)

    }

    @Benchmark
    fun sumOfSquares_capturingLambda(): Long {
        return sumOfSquares(param) {
            it * it * factor;
        }
    }

    @Benchmark
    fun sumOfSquares_capturingLambda_inline(): Long {
        return sumOfSquares_inline(param) {
            it * it * factor;
        }
    }

    @Benchmark
    fun sumOfSquares_anonymousFunction(): Long {
        return sumOfSquares(param, sumOfSquares_anonymousFunction)
    }

    @Benchmark
    fun sumOfSquares_anonymousFunction_inline(): Long {
        return sumOfSquares_inline(param, sumOfSquares_anonymousFunction)
    }

    //@Benchmark
    //fun sumOfSquares_anonymousFunction_capturing(): Long {
    //    return sumOfSquares(param, sumOfSquares_anonymousFunction_capturing)
    //}

    //@Benchmark
    //fun sumOfSquares_anonymousFunction_capturing_inline(): Long {
    //    return sumOfSquares_inline(param, sumOfSquares_anonymousFunction_capturing)
    //}

    private fun square(it: Int): Int {
        return it * it
    }

    private fun sumOfSquares(max: Int, square: (Int) -> Int): Long {
        var sum = 0L
        for (i in 1..max) {
            sum += square(i)
        }
        return sum
    }

    private inline fun sumOfSquares_inline(max: Int, square: (Int) -> Int): Long {
        var sum = 0L
        for (i in 1..max) {
            sum += square(i)
        }
        return sum
    }

}
