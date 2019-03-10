package org.ib.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

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
@Fork(value = 3, jvmArgsAppend = {"-XX:-TieredCompilation", "-Xbatch"})
@State(Scope.Benchmark)
public class MegamorphicCallJavaBenchmark {

    public static void main(String[] args) throws RunnerException {

        Options opt =
                new OptionsBuilder()
                        .include(MegamorphicCallJavaBenchmark.class.getSimpleName())
                        .build();

        new Runner(opt).run();
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public static int execute(VirtualCallJavaCMath cmath, int i) {
        return cmath.compute(i);
    }

    @Benchmark
    @Group("MonomorphicJava")
    public int monomorphicCall(MonomorphicJavaState state) {
        return state.monomorphicCall();
    }

    @Benchmark
    @Group("BimorphicJava")
    public int bimorphicCall(BimorphicJavaState state) {
        return state.bimorphicCall();
    }


    @Benchmark
    @Group("Megamorphic3Java")
    public int megamorphic3Call(Megamorphic3JavaState state) {
        return state.megamorphic3Call();
    }

    @Benchmark
    @Group("Megamorphic4Java")
    public int megamorphic4Call(Megamorphic4JavaState state) {
        return state.megamorphic4Call();
    }

    @State(Scope.Thread)
    public static class MonomorphicJavaState {
        @Param({"3"})
        public static int param;

        VirtualCallJavaCMath alg1 = new VirtualCallJavaAlg1();

        int monomorphicCall() {
            return execute(alg1, param);
        }
    }

    @State(Scope.Thread)
    public static class BimorphicJavaState {
        @Param({"3"})
        public static int param;

        VirtualCallJavaCMath alg1 = new VirtualCallJavaAlg1();
        VirtualCallJavaCMath alg2 = new VirtualCallJavaAlg2();

        int bimorphicCall() {
            return execute(alg1, param) + execute(alg2, param);
        }
    }

    @State(Scope.Thread)
    public static class Megamorphic3JavaState {
        @Param({"3"})
        public static int param;

        VirtualCallJavaCMath alg1 = new VirtualCallJavaAlg1();
        VirtualCallJavaCMath alg2 = new VirtualCallJavaAlg2();
        VirtualCallJavaCMath alg3 = new VirtualCallJavaAlg3();

        int megamorphic3Call() {
            return execute(alg1, param) + execute(alg2, param) + execute(alg3, param);
        }
    }

    @State(Scope.Thread)
    public static class Megamorphic4JavaState {
        @Param({"3"})
        public static int param;

        VirtualCallJavaCMath alg1 = new VirtualCallJavaAlg1();
        VirtualCallJavaCMath alg2 = new VirtualCallJavaAlg2();
        VirtualCallJavaCMath alg3 = new VirtualCallJavaAlg3();
        VirtualCallJavaCMath alg4 = new VirtualCallJavaAlg4();

        int megamorphic4Call() {
            return execute(alg1, param) + execute(alg2, param) + execute(alg3, param) + execute(alg4, param);
        }
    }

    static abstract class VirtualCallJavaCMath {
        public abstract int compute(int i);
    }

    static class VirtualCallJavaAlg1 extends VirtualCallJavaCMath {
        public int compute(int i) {
            return i * 17;
        }
    }

    static class VirtualCallJavaAlg2 extends VirtualCallJavaCMath {
        public int compute(int i) {
            return i * 19;
        }
    }

    static class VirtualCallJavaAlg3 extends VirtualCallJavaCMath {
        public int compute(int i) {
            return i * 23;
        }
    }

    static class VirtualCallJavaAlg4 extends VirtualCallJavaCMath {
        public int compute(int i) {
            return i * 29;
        }
    }

}

