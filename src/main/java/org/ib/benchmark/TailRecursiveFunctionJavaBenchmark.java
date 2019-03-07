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
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 3, jvmArgsAppend = {"-Xss10M"})
@State(Scope.Benchmark)
public class TailRecursiveFunctionJavaBenchmark {

    @Param("10000")
    private int param = 0;

    public static void main(String[] args) throws RunnerException {

        Options opt =
                new OptionsBuilder()
                        .include(TailRecursiveFunctionJavaBenchmark.class.getSimpleName())
                        .build();

        new Runner(opt).run();
    }

    @Benchmark
    public int fibonacciRec() {
        return fibonacciRec(param, 0, 1);
    }

    private int fibonacciRec(int n, int a, int b) {
        if (n == 0)
            return a;
        else if (n == 1)
            return b;
        else return fibonacciRec(n - 1, b, a + b);
    }
}
