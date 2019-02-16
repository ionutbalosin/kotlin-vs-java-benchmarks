# Kotlin vs Java Performance Tests

A set of JMH Benchmarks for various Kotlin and Java language constructions and standard library functions.

## Build
```
mvn clean package
```

Note: need clean package every time, otherwise removed benchmarks will be hanging around.

## Run

To run full tests suite, proceed with below command:

```
java -jar target/kotlin-vs-java-benchmarks-jmh.jar
```

### Run specific tests

To run a specific tests suite, proceed with below commands which also redirects the results to a dedicated benchmark file:

#### StringInterpolation tests
```
java -jar target/kotlin-vs-java-benchmarks-jmh.jar ".*StringInterpolation.*" > results/StringInterpolationBenchmark.out
```

#### Inline class tests
```
java -jar target/kotlin-vs-java-benchmarks-jmh.jar ".*InlineClass.*" -prof gc > results/InlineClassBenchmark.out
```

#### Megamorphic call site tests
```
java -jar target/kotlin-vs-java-benchmarks-jmh.jar ".*MegamorphicCall.*" -prof perfasm:intelSyntax=true > results/MegamorphicCallBenchmark.out
```

## License

The project is licensed under [GNU](https://www.gnu.org/licenses/). For the full copyright and license information, please check the LICENSE file included in the `src/main/resources` folder
