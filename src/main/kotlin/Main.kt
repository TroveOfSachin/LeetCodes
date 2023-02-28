import java.io.File
import kotlin.math.abs
import kotlin.random.Random


fun main(args: Array<String>) {

    val main = Main()
    main.generateLargeUniqueNumberArray()


    var hashMap = hashMapOf<Int, Int>()
    hashMap[1] = 1

    hashMap.entries.forEach {
        it.key
    }


}

class Main {


    fun generateLargeUniqueNumberArray() {
        val random = Random(Int.MAX_VALUE)
        val set = mutableSetOf<Int>()

        for (i in 1..Int.MAX_VALUE / 1000) {
            set.add(abs(random.nextInt()))
        }
        println("Set Created")

        val fileName = "src/Arrays.txt"
        val myfile = File(fileName)

        myfile.createNewFile()

        myfile.bufferedWriter().use { out ->

            out.write(set.joinToString(",", "[", "]"))
        }

        println("Wrote to file")

//        File("~/Document/Array.txt").printWriter().use { out ->
//            out.println(set.joinToString(",","[", "]"))
//        }
//
//        println("Done")
    }
}






