fun main() {
    val exampleArray = IntArray(5) { it + 1 }

    reverseArray(exampleArray).forEach { print("$it ") }
    println()

    println(allPairs(exampleArray))
    printAllPairs(exampleArray)

    println(factorial(20))
    println(forFactorial(20))
    println(tailrecFactorial(20))
    println(tailrecFactorialEquivalent(20))

    println(getFibonacciNumberByPos(10))
    println(getFibonacciNumberByPosMemo(10))

    getFibonacciNumbersRecursive(10).forEach { print("$it ") }
    println()
    getFibonacciNumbersRecursiveMemo(10).forEach { print("$it ") }
    println()
    getFibonacciNumbersWithFor(10).forEach { print("$it ") }
    println()
}

// вернуть новый перевернутый массив (O(n) где n - кол-во элементов и по памяти и по времени)
fun reverseArray(array: IntArray): IntArray {
    val result = IntArray(array.size)
    for(i in array.indices) {
        result[result.size - 1 - i] = array[i]
    }
    return result
}

// получить все возможные сочетания чисел в массиве (O(n²) от кол-ва элементов массива и для памяти и для времени)
fun allPairs(array: IntArray): String {
    val result = StringBuilder()

    for (i in array.indices) {
        for (j in array.indices) {
            result.append("(${array[i]}, ${array[j]}), ")
        }
    }

    return result.toString().removeSuffix(", ")
}

// вывести все возможные сочетания чисел в массиве (O(n²) от кол-ва элементов массива для времени, O(1) для памяти)
fun printAllPairs(array: IntArray) {
    for (x in array) {
        for (y in array) {
            print("($x, $y) ")
        }
    }
    println()
}

// infinite recursion - causes Stack Overflow on big numbers of calls
fun stackOverflow() {
    stackOverflow()
}

// calculate factorial (O(n) time and memory)
fun factorial(num: Long): Long {
    require(num >= 0)

    if (num <= 1L) {
        return 1
    }

    val res = factorial(num - 1)
    return num * res
}

// calculate factorial (O(n) time, but O(1) memory)
fun forFactorial(num: Long): Long {
    require(num >= 0)

    var res = 1L

    for(number in 2..num) {
        res *= number
    }

    return res
}

// recursion optimization, not creating functions chains, because there's no operations after recursion fun call.
// O(n) time, O(1) memory.
tailrec fun tailrecFactorial(num: Long, acc: Long = 1L): Long {
    require(num >= 0)

    if (num <= 1L) {
        return acc
    }

    return tailrecFactorial(num - 1, acc * num)
}
// that's the same
fun tailrecFactorialEquivalent(num: Long): Long {
    require(num >= 0)

    var numVar = num
    var accVar = 1L

    while(numVar > 1L) {
        accVar *= numVar
        numVar -= 1
    }

    return accVar
}

// returns Fibonacci numbers item by position (recursion ver.): O(2^n) time, O(n) memory
fun getFibonacciNumberByPos(index: Int): Int {
    require(index > 0)

    if (index == 1 || index == 2) return index - 1

    val res = getFibonacciNumberByPos(index - 1) + getFibonacciNumberByPos(index - 2)
    return res
}
// counts sum one time for one Fibonacci number,
// so the time is O(n) AVG, and the memory is O(n), but in reality we need more place for map.
fun getFibonacciNumberByPosMemo(index: Int): Int {
    require(index > 0)

    val memory = HashMap<Int, Int>()

    fun helper(i: Int): Int {
        if (i == 1 || i == 2) return i - 1
        // hash map get counts as O(1) average
        if (memory.containsKey(i)) return memory.getValue(i)

        val sum = helper(i - 1) + helper(i - 2)
        memory[i] = sum

        return sum
    }

    return helper(index)
}

// returns array of defined length with Fibonacci numbers.
// time: O(n^2), mem: O(n) (a*n for elements of array, + b*n for calls stack)
fun getFibonacciNumbersRecursive(count: Int): IntArray {
    require(count > 0)

    val res = IntArray(count) { -1 }
    res[0] = 0
    if (count > 1) res[1] = 1

    fun helper(index: Int): Int {
        if (index == 1 || index == 2) return index - 1
        //println("computing...")
        res[index - 1] = helper(index - 1) + helper(index - 2)
        return res[index - 1]
    }

    helper(count)
    return res
}
// time is O(n) after optimization, memory is still O(n).
fun getFibonacciNumbersRecursiveMemo(count: Int): IntArray {
    require(count > 0)

    val res = IntArray(count) { -1 }
    res[0] = 0
    if (count > 1) res[1] = 1

    fun helper(index: Int): Int {
        if (index == 1 || index == 2) return index - 1
        if (res[index - 1] != -1) return res[index - 1]
        //println("computing...")

        res[index - 1] = helper(index - 1) + helper(index - 2)
        return res[index - 1]
    }

    helper(count)
    return res
}

// here time is O(n) and memory is O(n), without recursive call stack
fun getFibonacciNumbersWithFor(count: Int): IntArray {
    require(count > 0)

    val res = IntArray(count) { -1 }
    res[0] = 0
    if (count > 1) res[1] = 1

    for (index in 2..<count) {
        res[index] = res[index - 1] + res[index - 2]
    }

    return res
}


