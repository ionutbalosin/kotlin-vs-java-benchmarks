package org.ib.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Iterator;
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
@Fork(value = 3, jvmArgsAppend = {"-XX:-TieredCompilation", "-Xbatch"})
@State(Scope.Benchmark)
public class SealedClassJavaBenchmark {

    private List<Shape> shapes;

    public static void main(String[] args) throws RunnerException {

        Options opt =
                new OptionsBuilder()
                        .include(SealedClassJavaBenchmark.class.getSimpleName())
                        .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        shapes = List.of(new Shape.Circle(4.5), new Shape.Square(4.0), new Shape.Rectangle(4.0, 5.0));
    }

    @Benchmark
    public final double area() {
        double area = 0.0D;
        Shape shape;
        for (Iterator iter = shapes.iterator(); iter.hasNext(); area += this.shapeArea(shape)) {
            shape = (Shape) iter.next();
        }

        return area;
    }

    public final double shapeArea(Shape shape) {
        if (shape instanceof Shape.Circle) {
            return 3.14D * ((Shape.Circle) shape).getRadius() * ((Shape.Circle) shape).getRadius();
        } else if (shape instanceof Shape.Square) {
            return ((Shape.Square) shape).getLength() * ((Shape.Square) shape).getLength();
        } else if (shape instanceof Shape.Rectangle) {
            return ((Shape.Rectangle) shape).getLength() * ((Shape.Rectangle) shape).getBreadth();
        } else {
            throw new RuntimeException("Unable to match a Shape instance");
        }
    }

    public abstract static class Shape {
        private Shape() {
        }

        public static final class Circle extends Shape {
            private double radius;

            public Circle(double radius) {
                super();
                this.radius = radius;
            }

            public final double getRadius() {
                return this.radius;
            }

            public final void setRadius(double var1) {
                this.radius = var1;
            }
        }

        public static final class Square extends Shape {
            private double length;

            public Square(double length) {
                super();
                this.length = length;
            }

            public final double getLength() {
                return this.length;
            }

            public final void setLength(double var1) {
                this.length = var1;
            }
        }

        public static final class Rectangle extends Shape {
            private double length;
            private double breadth;

            public Rectangle(double length, double breadth) {
                super();
                this.length = length;
                this.breadth = breadth;
            }

            public final double getLength() {
                return this.length;
            }

            public final void setLength(double var1) {
                this.length = var1;
            }

            public final double getBreadth() {
                return this.breadth;
            }

            public final void setBreadth(double var1) {
                this.breadth = var1;
            }
        }
    }

}
