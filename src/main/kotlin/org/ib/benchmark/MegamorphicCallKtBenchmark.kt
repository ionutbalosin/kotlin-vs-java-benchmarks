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
    @Group("MonomorphicKt")
    fun monomorphicCall(state: MonomorphicKtState): Int {
        return state.monomorphicCall()
    }

    @Benchmark
    @Group("BimorphicKt")
    fun bimorphicCall(state: BimorphicKtState): Int {
        return state.bimorphicCall()
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
    open class MonomorphicKtState {

        internal var alg1: VirtualCallKtCMath = VirtualCallKtAlg1()

        internal fun monomorphicCall(): Int {
            return execute(alg1, param)
        }

        companion object {
            @Param("3")
            var param: Int = 0
        }
    }

    @State(Scope.Thread)
    open class BimorphicKtState {

        internal var alg1: VirtualCallKtCMath = VirtualCallKtAlg1()
        internal var alg2: VirtualCallKtCMath = VirtualCallKtAlg2()

        internal fun bimorphicCall(): Int {
            return execute(alg1, param) + execute(alg2, param)
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
            return execute(alg1, param) + execute(alg2, param) + execute(alg3, param)
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
            return execute(alg1, param) + execute(alg2, param) + execute(alg3, param) + execute(alg4, param)
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
        override fun compute(i: Int): Int {
            return i * 17
        }
    }

    internal class VirtualCallKtAlg2 : VirtualCallKtCMath() {
        override fun compute(i: Int): Int {
            return i * 19
        }
    }

    internal class VirtualCallKtAlg3 : VirtualCallKtCMath() {
        override fun compute(i: Int): Int {
            return i * 23
        }
    }

    internal class VirtualCallKtAlg4 : VirtualCallKtCMath() {
        override fun compute(i: Int): Int {
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

        //internal inline fun execute(cmath: VirtualCallKtCMath, i: Int): Int {
        internal inline fun execute(cmath: VirtualCallKtCMath, i: Int): Int {
            return cmath.compute(i)
        }
    }

}