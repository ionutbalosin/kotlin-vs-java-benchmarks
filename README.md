# Kotlin vs Java Performance Tests

A set of JMH Benchmarks for various Kotlin and Java language constructions and standard library functions.

## Build
```
mvn clean package
```

Note: need clean package every time, otherwise removed benchmarks will be hanging around.

## Run

To run full tests suite, please proceed with below command:

```
java -jar target/kotlin-vs-java-benchmarks-jmh.jar
```

### Run specific tests

To run a specific tests suit, please proceed with each below command which also redirects the output into dedicated file:

```
java -jar target/kotlin-vs-java-benchmarks-jmh.jar ".*StringInterpolation.*" > results/StringInterpolationBenchmark.out
```

## License

The project is licensed under [GNU](https://www.gnu.org/licenses/). For the full copyright and license information, please check the LICENSE file included in the `resources` folder
