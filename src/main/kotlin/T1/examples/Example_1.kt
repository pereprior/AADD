package T1.examples

import java.io.File

val file1 = File("T1/examples/files/example1.txt")
val file2 = File("T1/examples/filesles/files","example2.txt")

val dir = File("T1/examples/filesles/files")
val file3 = File(dir,"example3")

fun main(args: Array<String>){
    val file = File(".")

    println(" Files list: ")
    for (e in file.list()?.sorted()!!){
        println("- $e")
    }
}