import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    println("Input number of TVs:")
    val numOfTVs = readLine()!!.toInt()
    val range = 1..numOfTVs
    val rand = Random()
    var TVs: ArrayList<TV> = ArrayList(0)
    for (value in range) {
        val left = rand.nextInt(10)
        var right = rand.nextInt(10)
        while (right < left) {
            right = rand.nextInt(10)
        }
        TVs.add(TV(value, left, right))
    }
    for (value in TVs) {
        println(value.getNum())
        print(value.getLeft())
        print("-")
        println(value.getRight())
    }
    var timeMoments: ArrayList<Int> = ArrayList(0)

    for (value in TVs) {
        for (i in value.getLeft()..value.getRight()) {
            timeMoments.add(i)
        }
    }
    for (value in TVs) {
        if (isSolid(TVs, timeMoments)) {
            for (i in value.getLeft()..value.getRight()) {
                timeMoments.remove(i)
            }
            if (isSolid(TVs, timeMoments)) {
                println(value.getNum())
                exitProcess(0)
            } else {
                for (i in value.getLeft()..value.getRight()) {
                    timeMoments.add(i)
                }
            }
        } else {
            println(-1)
            exitProcess(0)
        }
    }
    println(-1)
}

fun isSolid (TVs:ArrayList<TV>,timeMoments: ArrayList<Int>): Boolean {
    var max = TVs[0].getRight()
    var min = TVs[0].getLeft()
    for (value in TVs) max = if (value.getRight() > max) value.getRight() else max
    for (value in TVs) min = if (value.getLeft() < min) value.getLeft() else min

    for (i in min..max) {
        if (timeMoments.contains(i)) continue
        else return false
    }
    return true
}