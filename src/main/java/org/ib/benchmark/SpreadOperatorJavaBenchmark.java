package org.ib.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.List;
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
public class SpreadOperatorJavaBenchmark {

    @Param({"0", "10", "100", "1000", "10000"})
    private int size;

    private Integer[] arrayOfInteger;
    private int[] intArray;

    public static void main(String[] args) throws RunnerException {

        Options opt =
                new OptionsBuilder()
                        .include(SpreadOperatorJavaBenchmark.class.getSimpleName())
                        .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void spreadOperatorVarArgs(Blackhole blackHole) {
        varargs(blackHole, intArray);
    }

    @Benchmark
    public final List spreadOperatorAsList() {
        return Arrays.asList(arrayOfInteger);
    }

    private final void varargs(Blackhole blackHole, int... data) {
        blackHole.consume(data);
    }

    @Setup
    public void setup() {
        intArray = new int[size];
        for (int i = 0; i < size; i++)
            intArray[i] = i;

        arrayOfInteger = new Integer[size];
        for (int i = 0; i < size; i++) {
            Integer var11 = i;
            intArray[i] = var11;
        }
    }

}
