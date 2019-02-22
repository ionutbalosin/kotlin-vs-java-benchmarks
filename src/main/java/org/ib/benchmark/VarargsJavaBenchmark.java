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
public class VarargsJavaBenchmark {

    @Param({"3"})
    int param1;
    @Param({"5"})
    int param2;
    @Param({"7"})
    int param3;
    @Param({"9"})
    int param4;
    @Param({"11"})
    int param5;
    @Param({"13"})
    int param6;
    @Param({"15"})
    int param7;
    @Param({"17"})
    int param8;

    public static void main(String[] args) throws RunnerException {

        Options opt =
                new OptionsBuilder()
                        .include(TailRecursiveFunctionJavaBenchmark.class.getSimpleName())
                        .build();

        new Runner(opt).run();
    }

    @Benchmark
    public float average() {
        return getAverage(param1, param2, param3, param4, param5, param6, param7, param8);
    }

    private float getAverage(int... input) {
        var sum = 0.0f;
        for (int i = 0; i < input.length; i++) {
            sum += input[i];
        }
        return sum / (float) input.length;
    }
}
