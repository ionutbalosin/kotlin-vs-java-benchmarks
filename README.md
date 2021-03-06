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

#### High-order function tests
```
java -jar target/kotlin-vs-java-benchmarks-jmh.jar ".*HighOrderFunction.*" -prof gc > results/HighOrderFunctionBenchmark.out
java -jar target/kotlin-vs-java-benchmarks-jmh.jar ".*HighOrderFunction.*" -prof perfasm:intelSyntax=true > results/HighOrderFunctionBenchmark-asm.out
```

#### Tail recursion function tests
```
java -jar target/kotlin-vs-java-benchmarks-jmh.jar ".*TailRecursiveFunction.*" > results/TailRecursiveFunctionBenchmark.out
```

#### Null safety tests
```
java -jar target/kotlin-vs-java-benchmarks-jmh.jar ".*NullSafety.*" -prof perfasm:intelSyntax=true > results/NullSafetyBenchmark.out
```

#### Sealed classes tests
```
java -jar target/kotlin-vs-java-benchmarks-jmh.jar ".*SealedClass.*" > results/SealedClassBenchmark.out
```

#### Default arguments tests
```
java -jar target/kotlin-vs-java-benchmarks-jmh.jar ".*DefaultArguments.*" -prof perfasm:intelSyntax=true > results/DefaultArgumentsBenchmark.out
```

#### Varargs tests
```
java -jar target/kotlin-vs-java-benchmarks-jmh.jar ".*Varargs.*" -prof perfasm:intelSyntax=true > results/VarargsBenchmark.out
```

#### Spread operator tests
```
java -jar target/kotlin-vs-java-benchmarks-jmh.jar ".*SpreadOperator.*" > results/SpreadOperatorBenchmark.out
```

## License

The project is licensed under [GNU](https://www.gnu.org/licenses/). For the full copyright and license information, please check the LICENSE file included in the `src/main/resources` folder
