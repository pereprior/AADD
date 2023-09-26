package T1.examples

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

fun main(args: Array<String>) {
    //scanner in kotlin
    println("Put a folder:")
    val input = BufferedReader(InputStreamReader(System.`in`)).readLine()

    val file = File(input)

    println(" Files list in: $input")
    for (e in file.list()?.sorted()!!)
        println("- $e")
}