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
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 3, jvmArgsAppend = ["-Xss10M"])
@State(Scope.Benchmark)
open class TailRecursiveFunctionKtBenchmark {

    @Param("10000")
    private var param = 0

    @Benchmark
    fun fact(): Int {
        return factRec(param, 0, 1)
    }

    private tailrec fun factRec(n: Int, a: Int, b: Int): Int =
            if (n == 0)
                a;
            else if (n == 1)
                b;
            else factRec(n - 1, b, a + b);

    companion object {

        @Throws(RunnerException::class)
        @JvmStatic
        fun main(args: Array<String>) {

            val opt = OptionsBuilder()
                    .include(TailRecursiveFunctionKtBenchmark::class.java.simpleName)
                    .build()

            Runner(opt).run()
        }
    }
}
