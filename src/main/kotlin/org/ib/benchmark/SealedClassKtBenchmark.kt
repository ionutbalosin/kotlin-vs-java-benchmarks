package org.ib.benchmark

import org.openjdk.jmh.annotations.*
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.RunnerException
import org.openjdk.jmh.runner.options.OptionsBuilder
import java.util.*
import java.util.concurrent.TimeUnit

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
@Fork(value = 3, jvmArgsAppend = ["-XX:-TieredCompilation", "-Xbatch"])
@State(Scope.Benchmark)
open class SealedClassKtBenchmark {

    sealed class Shape {
        class Circle(var radius: Double) : Shape()
        class Square(var length: Double) : Shape()
        class Rectangle(var length: Double, var breadth: Double) : Shape()
    }

    lateinit var shapes: List<Shape>

    @Setup
    fun setup() {
        shapes = Arrays.asList(Shape.Circle(4.5), Shape.Square(4.0), Shape.Rectangle(4.0, 5.0))
    }

    @Benchmark
    fun area(): Double {
        var area = 0.0
        for (shape in shapes) {
            area += shapeArea(shape);
        }
        return area
    }

    fun shapeArea(shape: Shape): Double {
        return when (shape) {
            is Shape.Circle -> 3.14 * shape.radius * shape.radius
            is Shape.Square -> shape.length * shape.length
            is Shape.Rectangle -> shape.length * shape.breadth
        }
    }

    companion object {

        @Throws(RunnerException::class)
        @JvmStatic
        fun main(args: Array<String>) {

            val opt = OptionsBuilder()
                    .include(SealedClassKtBenchmark::class.java.simpleName)
                    .build()

            Runner(opt).run()
        }

    }

}