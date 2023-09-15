package examples

import java.io.File
import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    println("Put a folder:")
    val input = BufferedReader(InputStreamReader(System.`in`)).readLine()
    val file = File(input)

    println("Files list:")
    for (e in file.walk().sorted())
        println(e)
}