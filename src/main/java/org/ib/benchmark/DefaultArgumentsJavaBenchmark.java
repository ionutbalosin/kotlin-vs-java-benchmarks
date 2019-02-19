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
@Fork(value = 3, jvmArgsAppend = {})
@State(Scope.Benchmark)
public class DefaultArgumentsJavaBenchmark {

    @Param("8")
    private int param = 0;

    @Param("2")
    private int shift = 0;

    public static void main(String[] args) throws RunnerException {

        Options opt =
                new OptionsBuilder()
                        .include(DefaultArgumentsJavaBenchmark.class.getSimpleName())
                        .build();

        new Runner(opt).run();
    }

    @Benchmark
    public int defaultArguments() {
        return expression(param, 0, 0, 0);
    }

    @Benchmark
    public int namedArguments() {
        return expression(param, 0, shift, 0);
    }

    private int expression(int value, int shl_1, int shl_2, int shl_3) {
        return value << shl_1 << shl_2 << shl_3;
    }

}
