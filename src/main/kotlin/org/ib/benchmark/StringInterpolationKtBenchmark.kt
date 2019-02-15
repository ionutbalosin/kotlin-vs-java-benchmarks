package org.ib.benchmark

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.RunnerException
import org.openjdk.jmh.runner.options.OptionsBuilder
import java.util.concurrent.TimeUnit

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 10, time = 5, timeUnit = TimeUnit.NANOSECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.NANOSECONDS)
@Fork(value = 3, jvmArgsAppend = [])
@State(Scope.Benchmark)
open class StringInterpolationBenchmark {


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
                    .include(StringInterpolationBenchmark::class.java.simpleName)
                    .build()

            Runner(opt).run()
        }
    }

    @Benchmark
    fun testInterpolation(): String {
        return "$amount kilogram of $fruits costs $price $currency"
    }
}
