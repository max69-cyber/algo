/** from best to worst: O(1), O(log n), O(n), O(n log n), O(n^2), O(2^n) */
object BigOExamples {
    /** O(1) */
    fun getNumberByIndex(array: IntArray, index: Int): Int = array[index]
    /** O(1) AVG, but O(n) in the worst scenario */
    fun getValueByKey(hashMap: HashMap<Int, Int>, key: Int): Int = hashMap.getValue(key)

    /** O(log n) in worst case: each iteration if target is not found, the search range is divided by 2 */
    fun binarySearch(array: IntArray, target: Int): Int {
        var left = 0
        var right = array.lastIndex

        while (left <= right) {
            val mid = (right - left) / 2 + left

            when {
                array[mid] > target -> right = mid - 1
                array[mid] < target -> left = mid + 1
                else -> return mid
            }
        }
        return -1
    }

    /** O(n): `for` performs operation on every element */
    fun getArraySum(array: IntArray): Int {
        var res = 0

        for (num in array) {
            res += num
        }
        return res
    }

    /** O(n log n) time: O(log n) recursion levels, with O(n) total work per level */
    fun mergeSort(array: IntArray): IntArray {
        if (array.size <= 1) return array

        val middle = array.lastIndex / 2
        val left = mergeSort(array.sliceArray(0..middle))
        val right = mergeSort(array.sliceArray(middle + 1..array.lastIndex))

        return merge(left, right)
    }

    /** helper for `mergeSort`. checks every element of two arrays one time, so it's a O(n) for time and memory. */
    fun merge(array1: IntArray, array2: IntArray): IntArray {
        val res = IntArray(array1.size + array2.size) { -1 }

        var firstIndex = 0
        var secondIndex = 0

        for (i in res.indices) {
            val takeFromFirst = secondIndex == array2.size || (firstIndex != array1.size && array1[firstIndex] <= array2[secondIndex])

//            if (firstIndex == array1.size && secondIndex != array2.size) {
//                res[i] = array2[secondIndex]
//                secondIndex++
//                continue
//            } else if (firstIndex != array1.size && secondIndex == array2.size) {
//                res[i] = array1[firstIndex]
//                firstIndex++
//                continue
//            }

            if (takeFromFirst) {
                res[i] = array1[firstIndex]
                firstIndex++
            } else {
                res[i] = array2[secondIndex]
                secondIndex++
            }
        }

        return res
    }
}