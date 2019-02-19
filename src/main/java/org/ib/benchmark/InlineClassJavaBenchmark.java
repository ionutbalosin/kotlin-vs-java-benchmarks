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
public class InlineClassJavaBenchmark {

    @Param({"3.5"})
    private double param;

    public static void main(String[] args) throws RunnerException {

        Options opt =
                new OptionsBuilder()
                        .include(InlineClassJavaBenchmark.class.getSimpleName())
                        .build();

        new Runner(opt).run();
    }

    @Benchmark
    public double circleDiameter() {
        Circle circle = new Circle(param);
        return circle.getDiameter();
    }

    public static class Circle {
        final private double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        public double getDiameter() {
            return 2 * radius;
        }
    }

}
