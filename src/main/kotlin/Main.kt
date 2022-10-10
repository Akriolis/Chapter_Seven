import java.beans.PropertyChangeListener
import java.math.BigDecimal
import kotlin.IndexOutOfBoundsException
import java.time.LocalDate

/**
 * Conventions in Kotlin
 *
 * operator keyword
 */

operator fun Point.plus (other: Point): Point{
    return Point(x + other.x, y + other.y)
}

/**
 * times stands for a * b
 *
 * div stands for a / b
 *
 * mod stands for a % b
 *
 * plus stands for a + b
 *
 * minus stands for a - b
 */

operator fun Point.times (scale: Double): Point{
    return Point ((x * scale).toInt(), (y * scale).toInt())
}

operator fun Char.times(count: Int): String{
    return toString().repeat(count)
}

/**
 * Overloading compound assignment operators
 *
 * +=, -= and etc are called compound assignment operators
 */

/**
 * Overloading unary operators
 *
 * unaryPlus stands for +a
 *
 * unaryMinus stands for -a
 *
 * not stands for !a
 *
 * inc stands for ++a, a++
 *
 * dec stands for --a, a--
 *
 */

operator fun Point.unaryMinus(): Point{
    return Point(-x, -y)
}

operator fun BigDecimal.inc() = this + BigDecimal.ONE

/**
 * Overloading comparison operators
 *
 * === is identity equals operator, it can't be overloaded
 *
 */

/**
 * Ordering operators: compareTo
 */

/**
 * Conventions used for collections and ranges
 *
 * a[b] - index operator
 */

operator fun Point.get(index: Int): Int{
    return when (index){
        0 -> x
        1 -> y
        else ->
            throw IndexOutOfBoundsException ("Invalid coordinate $index")
    }
}

operator fun MutablePoint.set(index: Int, value: Int){
    when(index) {
        0 -> x = value
        1 -> y = value
        else ->
            throw IndexOutOfBoundsException ("Invalid coordinate $index")
    }
}

/**
 * The "in" convention
 *
 * a in c -> c.contains(a)
 */

operator fun Rectangle.contains(p: Point): Boolean{
    return p.x in upperLeft.x until lowerRight.x &&
            p.y in upperLeft.y until lowerRight.y
}

/**
 * The rangeTo convention
 *
 * start...end -> start.rangeTo(end)
 * .. operator
 *
 * operator fun <T: Comparable<T>> T.rangeTo (that: T): ClosedRange<T>

 */

/**
 * The "iterator" convention for the "for" loop
 *
 * for (x in list) {} -> list.iterator()
 */

/*operator fun CharSequence.iterator(): CharIterator*/

operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
    object: Iterator<LocalDate>{
        var current = start

        override fun hasNext(): Boolean =
            current <= endInclusive


        override fun next(): LocalDate = current.apply {
            current = plusDays(1)
        }
    }

/**
 * Destructing declarations and component functions
 *
 * val (a,b) = p
 * val a = p.component1()
 * val b = p.component2()
 *
 */

fun splitFilename(fullName: String): NameComponents{
    val result = fullName.split('.', limit = 2)
    return NameComponents(result[0], result[1])
}

fun splitFilename2(fullName: String): NameComponents{
    val (name, extension) = fullName.split(".", limit =2)
    return NameComponents(name,extension)
}

/**
 * Destructuring declarations and loops
 */

fun printEntries (map: Map<String, String>){
    for ((key, value) in map){
        println("$key -> $value")
    }
}

/**
 * Delegated properties
 *
 * delegation is a design pattern where an object, instead of performing a task,
 * delegates that task to another helper object (which called a delegate)
 *
 * class Foo{
 * var p: Type by Delegate()
 * }
 *
 * Lazy initialization
 * pattern that entails creating part of an object on demand,
 * when it's accessed for the first time
 *
 * backing property technique
 *
 * lazy standard function
 *
 */

/**
 * Implementing delegated properties
 */

/**
 * Expando objects
 */

fun main(){
    val p1 = Point(10,20)
    val p2 = Point(30,40)
    println(p1 + p2)
    // under the hood p1 + p2 is p1.plus(b)
    println(p1.plus(p2))

    val p3 = Point(10,20)
    println(p3 * 1.5)

    println('a' *  3)

    var point = Point(1,2)
    point += Point(3,4)
    println(point)

    val numbers = ArrayList<Int>()
    numbers += 42
    numbers += 40
    numbers += 20
    println(numbers[0])
    println(numbers[1])
    println(numbers[2])

    val list = arrayListOf(1,2)
    list += 3
    val newList = list + listOf(4,5)
    println(list)
    println(newList)

    val p4 = Point(10,20)
    println(-p4)

    var bd = BigDecimal.ZERO
    println(bd++)
    println(++bd)

    println(Point2(10,20) == Point2(10,20))
    println(Point2(10,20) != Point2(5,5))
    println(null == Point2(1,2))
    println(Point2(5,3) == Point2(2,1))

    val p5 = Person("Alice", "Smith")
    val p6 = Person("Bob", "Johnson")

    println(p5 < p6)

    println("abc" > "bac")

    val p7 = Point(10,20)
    println(p7[1])

    val p8 = MutablePoint(10,20)
    p8.set(0,30)
    p8[1] = 25
    println(p8)

    val rect = Rectangle(Point(10,20), Point (50, 50))
    val testPoint = Point(20,30)
    val testPoint2 = Point(5,40)
    println(testPoint in rect)
    println(testPoint2 in rect)

    val range = 1..10

    val now = LocalDate.now()
    val vacation = now..now.plusDays(10)
    println(now.plusWeeks(1) in vacation)

    val n = 9
    println(0..(n+ 1))
    (0..n).forEach { print(it) }
    println()
    for (char in "abc") println(char)

    val newYear = LocalDate.ofYearDay(2023, 1)
    val daysOff = newYear.minusDays(1)..newYear
    for (dayOff in daysOff) { println(daysOff) }

    val p9 = Point(10,20)
    val (x, y) = p9
    println(x)
    println(y)

    val (name, ext) = splitFilename("example.kt")
    println(name)
    println(ext)

    val map = mapOf("Oracle" to "Java", "JetBrains" to "Kotlin")
    printEntries(map)

    val p = Person2("Alice")
    p.emails

    val p10 = Person4("Dmitry", 34, 2000)

    p10.addPropertyChangeListener(
        PropertyChangeListener { event ->
            println("Property ${event.propertyName} changed " +
            "from ${event.oldValue} to ${event.newValue}")

        }
    )

    p10.age = 35
    p10.salary = 2100

    val p11 = Person5("Stuart", 25, 1000)
    p11.age = 26
    p11.salary = 1100

    val p12 = Person6("Joe", 20, 500)
    p12.age = 21
    p12.salary = 510

    val p13 = Person8()
    val data = mapOf("name" to "Dmitry", "company" to "JetBrains")

    for ((attrName, value) in data)
        p13.setAttribute(attrName, value)

    println(p13.name)

}