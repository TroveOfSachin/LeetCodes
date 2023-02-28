import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.time.Instant
import java.util.Vector
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureNanoTime


fun main() {

//    val inputArray: LongArray =
//        longArrayOf(4, 16, 5, 23, 12, 88, 99, 109, 38, 590, 37, 56, 353, 204, 345, 2342, 567, 980, 223)
    val sum = 155L
    val fileName = "src/Arrays.txt"

    val file = File(fileName)
    if (!file.exists()) {
        Main().generateLargeUniqueNumberArray()
    }

    val myfile = FileReader(fileName)


    val br = BufferedReader(myfile)

    var line = br.readText()
    line = line.replace("[", "").replace("]", "")
    val inputList = line.split(",")

    val inputArray = inputList.map { it.toLong() }.toTypedArray()
val v = Vector<Int>()
    v.add(1)


    println("File Read Successfully")

//    val sol = Solution()
//    val result = sol.twoSum(inputArray.toIntArray(), sum)
//    result?.let {
//        println("Result is ${it.joinToString()}")
//    }?:run{
//        println("No Combination Found")
//    }

    val twoSum = TwoSum()


    val result1 = twoSum.twoSum(inputArray.toLongArray(), sum) //twoSum.calculateTwoSumUsingHashMap(inputArray, sum)
    result1?.let {
        println("Result is ${it.joinToString()}")
    } ?: run {
        println("No Combination Found")
    }

//    println("\n*******************************\nOwn Approach")
    val twoSum2 = TwoSum()
    var result = twoSum2.calculateTwoSumUsingHashMap(inputArray.toLongArray(), sum)

    if (result.isNotEmpty()) {
        println("Pair found for sum of $sum = ${result.joinToString()}")
    } else {
        println("No Combination Found")
    }


}


class TwoSum {


    /**
     * Function to find the two elements of given sum without sorting an array.
     *
     * @param inputArray Input Array to find the two elements of given sum
     * @param sum to find two element of sum from given inputArray
     *
     * @return it will return a [Pair]<[Int],[Int]> as soon as it found and won't see for another combination.
     *
     *
     */
    fun calculateTwoSumUsingBinarySearchSingleOutput(inputArray: Array<Int>, sum: Int): Pair<Int, Int>? {

        for (i in 0 until inputArray.size - 1) {
            for (j in i + 1 until inputArray.size) {
                val itemSum = inputArray[i] + inputArray[j]
                if (itemSum == sum) {
                    return Pair(i, j)
                }
            }
        }
        return null
    }

    /**
     * Function to find the two elements of given sum without sorting an array.
     *
     * @param inputArray Input Array to find the two elements of given sum
     * @param sum to find two element of sum from given inputArray
     *
     * @return it will return a [List]<[Pair]<[Int],[Int]>> as soon as it found and won't see for another combination.
     *
     *
     */
    fun calculateTwoSumUsingBinarySearchMultipleOutput(inputArray: Array<Int>, sum: Int): List<Pair<Int, Int>> {

        val outputList = mutableListOf<Pair<Int, Int>>()

        for (i in 0 until inputArray.size - 1) {
            for (j in i + 1 until inputArray.size) {
                val itemSum = inputArray[i] + inputArray[j]
                if (itemSum == sum) {
                    outputList.add(Pair(i, j))
                }
            }
        }
        return outputList
    }

    /**
     * Function to find the two elements of given sum using sorting an array.
     *
     * @param inputArray Input Array to find the two elements of given sum
     * @param sum to find two element of sum from given inputArray
     *
     * @return it will return a [Pair]<[Int],[Int]> as soon as it found and won't see for another combination.
     *
     *
     */
    fun calculateTwoSumUsingHashMap(inputArray: LongArray, sum: Long): List<Pair<Int, Int>> {
        val startTime = Instant.now().nano
        var loopCount = 0
//        val outputHashMap = mutableMapOf<Int, Int>()
        val sortedArray = inputArray.sortedArray()
        var left = 0
        var right = sortedArray.lastIndex

        while (left < right) {
            loopCount++
            val itemSum = sortedArray[left] + sortedArray[right]

            if (sum - sortedArray[left] == sortedArray[right]) {
//                outputHashMap[left] = right
                println("Result Found")

                println("Array Size =  ${sortedArray.size}")
                println("Loop count =  $loopCount")
                calculateEndTime(startTime)
                return listOf(Pair(left, right))
            }

            if (itemSum < sum) left++
            if (itemSum > sum) right--

        }

        println("Array Size =  ${sortedArray.size}")
        println("Loop count =  $loopCount")

        val outputList = mutableListOf<Pair<Int, Int>>()
//        outputHashMap.forEach { (t, u) -> outputList.add(Pair(t, u)) }
        calculateEndTime(startTime)
        return outputList
    }


    fun calculateTwoSumUsingArrayIndex(inputArray: Array<Int>, sum: Int): List<Pair<Int, Int>> {

        var loopCount = AtomicInteger()
        val outputList = mutableListOf<Pair<Int, Int>>()

        inputArray.forEachIndexed { index, i ->
            loopCount.incrementAndGet()
            val inx = inputArray.indexOf(sum - i)
            if (inx >= 0 && inx > index) {
                outputList.add(Pair(index, inx))
            }
        }

        println("Total Loop Count = ${loopCount.get()} for array size = ${inputArray.size}")

        return outputList
    }


    fun twoSum(nums: LongArray, target: Long): LongArray? {
        val startTime = Instant.now().nano
        val map: MutableMap<Long, Long> = HashMap()
        val loopCount = AtomicInteger()
        for (i in nums.indices) {
            loopCount.incrementAndGet()
            val complement = target - nums[i]
            if (map.containsKey(complement)) {
                println("Array Size =  ${nums.size}")
                println("Loop count =  ${loopCount.get()}")
                calculateEndTime(startTime)
                return longArrayOf(map[complement]!!, i.toLong())
            }
            map[nums[i]] = i.toLong()
        }
        println("Array Size =  ${nums.size}")
        println("Loop count =  ${loopCount.get()}")
        // In case there is no solution, we'll just return null
        calculateEndTime(startTime)
        return null
    }


    private fun calculateEndTime(startTime: Int) {


        val endTime = Instant.now().nano
        val totalTime = endTime - startTime
        val totalTimeInMillis = TimeUnit.NANOSECONDS.toMillis(endTime.toLong())
        println("TimeRequired : $totalTimeInMillis milliseconds")
    }
}

