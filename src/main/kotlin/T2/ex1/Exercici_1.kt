package T2.ex1

import java.io.File

fun main(args: Array<String>){
    val file = File("Ex1-Images/Penyagolosa.bmp")

    val formatFile = ImageFile(file)
    formatFile.negativeFormat()
    formatFile.darkFormat()
    formatFile.blackWhiteFormat()

    println("Files done")
}