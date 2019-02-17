package org.ib.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Optional;
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
@Fork(value = 3, jvmArgsAppend = {})
@State(Scope.Benchmark)
public class NullSafetyJavaBenchmark {

    private Outer outer;
    @Param({"42"})
    private int value = 0;

    public static void main(String[] args) throws RunnerException {

        Options opt =
                new OptionsBuilder()
                        .include(NullSafetyJavaBenchmark.class.getSimpleName())
                        .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        outer = new Outer(value);
    }

    @Benchmark
    public int ofNullable() {
        return Optional.ofNullable(outer)
                .flatMap(o -> Optional.ofNullable(o.getNested()))
                .flatMap(n -> Optional.ofNullable(n.getInner()))
                .flatMap(i -> Optional.ofNullable(i.getValue()))
                .orElse(null);
    }

    @Benchmark
    public int elvisEquivalent() {
        Outer localOuter = this.outer;

        Integer var3;
        if (localOuter != null) {
            Nested var1 = localOuter.getNested();
            if (var1 != null) {
                Inner var2 = var1.getInner();
                if (var2 != null) {
                    var3 = var2.getValue();
                    return var3;
                }
            }
        }

        var3 = null;
        return var3;
    }

    class Outer {
        private Nested nested;

        public Outer(Integer value) {
            this.nested = new Nested(value);
        }

        public Nested getNested() {
            return nested;
        }
    }

    class Nested {
        private Inner inner;

        public Nested(Integer value) {
            this.inner = new Inner(value);
        }

        public Inner getInner() {
            return inner;
        }
    }

    class Inner {
        private Integer value;

        public Inner(Integer foo) {
            this.value = foo;
        }

        public Integer getValue() {
            return value;
        }
    }
}
