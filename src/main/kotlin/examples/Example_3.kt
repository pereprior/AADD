package examples

import java.io.File

val rootFolder = File.listRoots()[0]

fun main(args: Array<String>) {
    val file = File("examples/files/example1.txt")
    println("File name: " + file.getName())
    println("File Path: " + file.getPath())
    println("File Absolute Path: " + file.getAbsolutePath())
    //without redundancies
    println("File Canonical Path: " + file.getCanonicalPath())
}