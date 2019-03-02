package org.ib.benchmark

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.RunnerException
import org.openjdk.jmh.runner.options.OptionsBuilder
import java.util.*
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
open class NullSafetyKtBenchmark {

    @Param("42")
    private var value: Int = 0

    @Param("-1")
    private var defaultValue: Int = 0

    private var notNullString: String = "conference"
    private lateinit var lateInitString: String

    private var maybeNullString: String? = getString()
    private lateinit var outer: Outer

    @Setup
    fun setup() {
        lateInitString = "conference"
        outer = Outer(value)
    }

    private fun getString(): String? {
        return if (Random().nextBoolean()) "conference" else null;
    }

    @Benchmark
    fun lateinit(): Int {
        // Compiler explicitly adds the NULL check and it throws UninitializedPropertyAccessException if variable is not initialized at the moment of usage
        return lateInitString.length;
    }

    @Benchmark
    fun notNull(): Int {
        // Compiler does not add the NULL check (just return the lenght) since it is sure the variable is not NULL
        return notNullString.length;
    }

    @Benchmark
    fun maybeNull(): Int? {
        // Compiler explicitly adds the NULL check and it returns NULL or the length of the variable but without throwing any exception
        return maybeNullString?.length;
    }

    @Benchmark
    fun maybeNullThrowException(): Int? {
        // Compiler explicitly adds the NULL check and it throws NPE if the variable is not initialized or returns the length otherwise
        return maybeNullString!!.length;
    }

    @Benchmark
    fun elvis(): Int? {
        // Compiler explicitly adds NULL checks for every nested call. It returns defaultValue if any of the reference is NULL or the value otherwise
        return outer?.nested?.inner?.value ?: defaultValue;
    }

    companion object {

        @Throws(RunnerException::class)
        @JvmStatic
        fun main(args: Array<String>) {

            val opt = OptionsBuilder()
                    .include(NullSafetyKtBenchmark::class.java.simpleName)
                    .build()

            Runner(opt).run()
        }
    }

    internal class Outer(value: Int?) {
        var nested: Nested = Nested(value)
    }

    internal class Nested(value: Int?) {
        var inner: Inner = Inner(value)
    }

    internal class Inner(var value: Int?)
}

