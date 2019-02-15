## Kotlin vs Java Performance Tests

A set of JMH Benchmarks for various Kotlin and Java language constructions and standard library functions.

### Build
```
mvn clean package
```

Note: need clean package every time, otherwise removed benchmarks will be hanging around.

### Run
```
java -jar target/kotlin-vs-java-benchmarks-jmh.jar
```

### License
The project is licensed under [GNU](https://www.gnu.org/licenses/). For the full copyright and license information, please check the LICENSE file included in the `resources` folder
