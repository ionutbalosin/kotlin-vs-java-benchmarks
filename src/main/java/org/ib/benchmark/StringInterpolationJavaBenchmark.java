package org.ib.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author Ionut Balosin [www.ionutbalosin.com / @ionutbalosin]
 * @copyright (c) Ionut Balosin
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
/**
 * @author Ionut Balosin [www.ionutbalosin.com / @ionutbalosin]
 * @copyright (c) Ionut Balosin
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */
public class StringInterpolationJavaBenchmark {


    @Param("1")
    private int amount;

    @Param("apples")
    private String fruits;

    @Param("1.59")
    private float price;

    @Param("EUR")
    private String currency;

    public static void main(String[] args) throws RunnerException {

        // sudo java -jar target/benchmarks.jar ".*StringInterpolationJavaBenchmark.*" -wi 10 -i 10 -r 1 -f 3 -prof perfasm:intelSyntax=true

        Options opt =
                new OptionsBuilder()
                        .include(StringInterpolationJavaBenchmark.class.getSimpleName())
                        .build();

        new Runner(opt).run();
    }

    @Benchmark
    public String testInterpolation() {
        return amount + " kilogram of " + fruits + " costs " + price + " " + currency;
    }
}
