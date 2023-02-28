import java.util.*
import java.util.concurrent.atomic.AtomicInteger

class Solution {




    fun twoSum(nums: IntArray, target: Int): IntArray? {
        var loopCount = AtomicInteger()
        var i = 0
        var j = nums.size - 1
        val sorted = Arrays.copyOf(nums, nums.size)
        Arrays.sort(sorted) // quickSort, O(log(n))
        while (i < j) {
            loopCount.incrementAndGet()
            val sum = sorted[i] + sorted[j]
            if (sum == target) {
                val indx1 = search(sorted[i], nums, -1)
                val indx2 = search(sorted[j], nums, indx1)
                println("Loop count =  ${loopCount.get()}")
                return intArrayOf(indx1, indx2)
            }
            if (sum < target) i++
            if (sum > target) j--


        }
        println()
        println("Loop count =  ${loopCount.get()}")
        println("Target sum $target not possible in list!")
        return intArrayOf(-1, -1)
    }

    // returns index of given int in an array, O(n)
    private fun search(num: Int, arr: IntArray, found_already: Int): Int {
        var innerLoopCounter = 0
        for (i in arr.indices) {
            innerLoopCounter++
            if (arr[i] == num && i != found_already) {
                println("InnerLoopCount = $innerLoopCounter")
                return i
            } else {

            }
        }
        println("InnerLoopCount = $innerLoopCounter")
        return 0
    }
}