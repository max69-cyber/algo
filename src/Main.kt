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