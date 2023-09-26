package T1.examples

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

fun main(args: Array<String>) {
    println("Put a directory:")
    val input = BufferedReader(InputStreamReader(System.`in`)).readLine()
    val file = File(input)

    if (file.exists()) {
        if (file.isDirectory)
            inputList(file)
         else
            println("Is not a directory")
    } else
        println("The file doesn't exists")
}

fun inputList(file: File) {
    val message = "Files list: " + file.canonicalPath

    println(message)
    //repeat - with the same message length
    println("-".repeat(message.length))

    for (e in file.listFiles()?.sorted()!!) {
        if (e.isFile)
            println(e.name + "\t " + e.length())
        if (e.isDirectory)
            println(e.name + "\t <Folder>")
        // tab = /t
    }
}