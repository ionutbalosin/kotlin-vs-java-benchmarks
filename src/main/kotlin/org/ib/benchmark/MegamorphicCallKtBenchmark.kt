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
@Fork(value = 3, jvmArgsAppend = ["-XX:-TieredCompilation", "-Xbatch"])
@State(Scope.Benchmark)
open class MegamorphicCallKtBenchmark {

    @Benchmark
    @Group("Megamorphic1Kt")
    fun megamorphic1Call(state: Megamorphic1KtState): Int {
        return state.megamorphic1Call()
    }

    @Benchmark
    @Group("Megamorphic2Kt")
    fun megamorphic2Call(state: Megamorphic2KtState): Int {
        return state.megamorphic2Call()
    }

    @Benchmark
    @Group("Megamorphic3Kt")
    fun megamorphic3Call(state: Megamorphic3KtState): Int {
        return state.megamorphic3Call()
    }

    @Benchmark
    @Group("Megamorphic4Kt")
    fun megamorphic4Call(state: Megamorphic4KtState): Int {
        return state.megamorphic4Call()
    }

    @State(Scope.Thread)
    open class Megamorphic1KtState {

        internal var alg1: VirtualCallKtCMath = VirtualCallKtAlg1()

        internal fun megamorphic1Call(): Int {
            return compute(alg1, param)
        }

        companion object {
            @Param("3")
            var param: Int = 0
        }
    }

    @State(Scope.Thread)
    open class Megamorphic2KtState {

        internal var alg1: VirtualCallKtCMath = VirtualCallKtAlg1()
        internal var alg2: VirtualCallKtCMath = VirtualCallKtAlg2()

        internal fun megamorphic2Call(): Int {
            return compute(alg1, param) + compute(alg2, param)
        }

        companion object {
            @Param("3")
            var param: Int = 0
        }
    }

    @State(Scope.Thread)
    open class Megamorphic3KtState {

        internal var alg1: VirtualCallKtCMath = VirtualCallKtAlg1()
        internal var alg2: VirtualCallKtCMath = VirtualCallKtAlg2()
        internal var alg3: VirtualCallKtCMath = VirtualCallKtAlg3()

        internal fun megamorphic3Call(): Int {
            return compute(alg1, param) + compute(alg2, param) + compute(alg3, param)
        }

        companion object {
            @Param("3")
            var param: Int = 0
        }
    }

    @State(Scope.Thread)
    open class Megamorphic4KtState {

        internal var alg1: VirtualCallKtCMath = VirtualCallKtAlg1()
        internal var alg2: VirtualCallKtCMath = VirtualCallKtAlg2()
        internal var alg3: VirtualCallKtCMath = VirtualCallKtAlg3()
        internal var alg4: VirtualCallKtCMath = VirtualCallKtAlg4()

        internal fun megamorphic4Call(): Int {
            return compute(alg1, param) + compute(alg2, param) + compute(alg3, param) + compute(alg4, param)
        }

        companion object {
            @Param("3")
            var param: Int = 0
        }
    }

    internal abstract class VirtualCallKtCMath {
        abstract fun compute(i: Int): Int
    }

    internal class VirtualCallKtAlg1 : VirtualCallKtCMath() {
        override inline fun compute(i: Int): Int {
            return i * 17
        }
    }

    internal class VirtualCallKtAlg2 : VirtualCallKtCMath() {
        override inline fun compute(i: Int): Int {
            return i * 19
        }
    }

    internal class VirtualCallKtAlg3 : VirtualCallKtCMath() {
        override inline fun compute(i: Int): Int {
            return i * 23
        }
    }

    internal class VirtualCallKtAlg4 : VirtualCallKtCMath() {
        override inline fun compute(i: Int): Int {
            return i * 29
        }
    }

    companion object {

        @Throws(RunnerException::class)
        @JvmStatic
        fun main(args: Array<String>) {

            val opt = OptionsBuilder()
                    .include(MegamorphicCallKtBenchmark::class.java.simpleName)
                    .build()

            Runner(opt).run()
        }

        internal inline fun compute(cmath: VirtualCallKtCMath, i: Int): Int {
            return cmath.compute(i)
        }
    }

}