# 01. Основы Groovy и сравнение с Java и Kotlin.

[Groovy GPath in REST Assured – Part 1: Overview of Groovy](https://www.james-willett.com/groovy-gpath-in-rest-assured-part1-overview/)

## Closures & it

_Closure_ или _замыкания_ в Groovy работают аналогично _lambda_-функциям в Java и Kotlin:

```groovy
def multiply = { int x, int y ->
    return (x * y)
}
println multiply(2, 4) //8
```

Тот же самый код на Java:

```java
BiFunction<Integer, Integer, Integer> multiply = (x, y) -> x * y;
System.out.println(multiply.apply(2, 4));
```

И Kotlin:

```kotlin
val multiply = { x: Int, y: Int ->
    x * y
}
println(multiply(2, 4))
```

Другой пример использования замыканий - работа со списками.

Вот как это выглядит в Groovy:

```groovy
def myList = [2, 3, 4, 4, 4, 5, 5, 6] //создание списка
myList.each {
    println it //it - безымянная ссылка на каждый элемент в списке
}
```

Тот же самый код на Java:

```java
var myList = List.of(2, 3, 4, 4, 4, 5, 5, 6);
myList.forEach(it ->
    System.out.println(it)
);
```

Вариант выше может быть заменён на _method reference_:

```java
var myList = List.of(2, 3, 4, 4, 4, 5, 5, 6);
myList.forEach(System.out::println);
```

И Kotlin:

```kotlin
val myList = listOf(2, 3, 4, 4, 4, 5, 5, 6)
myList.forEach {
    println(it)
}
```

## Find и FindAll

`find {}` возвращает одно значение, удовлетворяющее условию-_предикату_ (_predicate_). Если же не удалось найти ни
одного значения, удовлетворяющего условию, `find {}` возвращает `null`, что, при работе с тестами, может
вызвать `NullPointerException` или привести к неожидаемому поведению.

Вот как это выглядит в Groovy:

```groovy
def myList = [2, 3, 4, 4, 4, 5, 5, 6]
def greaterThanFive = myList.find { it > 5 } //вернёт 6
```

Тот же самый код на Java:

```java
var myList = List.of(2, 3, 4, 4, 4, 5, 5, 6);
myList.stream()
    .filter(it -> it > 5)
    .findAny()
    .orElse(null);
```

И Kotlin:

```kotlin
val myList = listOf(2, 3, 4, 4, 4, 5, 5, 6)
myList.find { it > 5 }
```

`findAll {}`, в отличие от `find {}`, возвращает новый список с результатами, удовлетворяющими условию. Если же не
удалось найти ни одного значения, удовлетворяющего условию, `findAll {}` вернёт пустой список (`[]`).

Вот как это выглядит в Groovy:

```groovy
def myList = [2, 3, 4, 4, 4, 5, 5, 6]
def allValuesGreaterThanTwo = myList.findAll { it > 2 } //список из всех чисел, больше 2
```

Тот же самый код на Java:

```java
var myList = List.of(2, 3, 4, 4, 4, 5, 5, 6);
myList.stream()
    .filter(it -> it > 2)
    .collect(Collectors.toList());
```

И Kotlin:

```kotlin
val myList = listOf(2, 3, 4, 4, 4, 5, 5, 6)
myList.filter { it > 2 }
```

## Collect

_Collect_ в Groovy отвечает за трансформацию объектов в списке или словаре (_Map_) и сохранении этих данных в новую
коллекцию. Аналог в Java и Kotlin - `map`.

Вот как это выглядит в Groovy:

```groovy
def myList = ['Tom', 'Dick', 'Harry', 'Kester']
def peopleNamesToUpperCase = myList.collect { it.toUpperCase() }
```

Тот же самый код на Java:

```java
var myList = List.of("Tom", "Dick", "Harry", "Kester");
myList.stream()
    .map(String::toUpperCase)
    .collect(Collectors.toList());
```

И Kotlin:

```kotlin
val myList = listOf("Tom", "Dick", "Harry", "Kester")
myList.map { it.uppercase() }
```

## Max / Min / Sum

Иногда могут встречаться задачи на поиск минимума, максимума или суммы значений в коллекции. С этим могут
помочь `min`, `max` и `sum`.

Вот как это выглядит в Groovy:

```groovy
def myList = [2, 3, 4, 4, 4, 5, 5, 6]
def sumOfMyListValuesGreaterThanFour = myList.findAll { it > 4 }.sum() //16
def minValueGreaterThanTwo = myList.findAll { it > 2 }.min() //3
def maxValueBetweenThreeAndFive = myList.findAll { it > 3 && it < 5 }.max() //4
```

Тот же самый код на Java:

```java
var myList = List.of(2, 3, 4, 4, 4, 5, 5, 6);
myList.stream()
    .filter(it -> it > 4)
    .mapToInt(it -> it)
    .sum();
myList.stream()
    .filter(it -> it > 2)
    .min(comparing(it -> it))
    .orElse(null);
myList.stream()
    .filter(it -> it > 3 && it < 5)
    .max(comparing(it -> it))
    .orElse(null);    
```

И Kotlin:

```kotlin
val myList = listOf(2, 3, 4, 4, 4, 5, 5, 6)
myList.filter { it > 4 }.sum()
myList.filter { it > 2 }.min()
myList.filter { it > 3 && it < 5 }.max()
```