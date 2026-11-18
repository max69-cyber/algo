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
}