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
public class StringInterpolationJavaBenchmark {

    @Param("1")
    private int quantity;

    @Param("apples")
    private String fruits;

    @Param("1.59")
    private float price;

    @Param("EUR")
    private String currency;

    @Param("Rectangle")
    private String shape;

    @Param("2")
    private double length;

    @Param("3")
    private double width;

    @Param("m2")
    private String unit;

    public static void main(String[] args) throws RunnerException {

        Options opt =
                new OptionsBuilder()
                        .include(StringInterpolationJavaBenchmark.class.getSimpleName())
                        .build();

        new Runner(opt).run();
    }

    @Benchmark
    public String fruitsPrice() {
        return quantity + " kilogram of " + fruits + " costs " + price + ' ' + currency;
    }

    @Benchmark
    public String shapeArea() {
        return shape + " area is " + length * width + ' ' + unit;
    }
}
