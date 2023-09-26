package T1.exercices

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

/**
 * Navegar por los directorios del sistema de archivos
 *
 * @author Pere Prior
 * @since 12/09/23
 */
fun main(args:Array<String>) {
    var currentFolder = File.listRoots()[0]
    val options = HashMap<String,String>()
    var end = false

    while (!end) {

        /*
        MENU PRINCIPAL
         */
        println("Files list: $currentFolder\n" +
                "-----------\n" +
                "0.- Parent File")

        val files = currentFolder.listFiles()

        for ((index,file) in files.withIndex()) {
            val num = index+1 //incrementamos en uno porque la opcion 0 es el directorio padre
            options.put(num.toString(),file.toString())

            if (file.isFile)
                println("$num.- $file" + "\t " + file.length())
            if (file.isDirectory)
                println("$num.- $file\t <Folder>")
        }

        /*
        ELECCIÓN DEL USUARIO
         */
        println("\nSelect a number (-1 to finish):")
        val input = BufferedReader(InputStreamReader(System.`in`)).readLine()

        /*
        INTERPRETACIÓN DE LA ELECCIÓN DEL USUARIO
         */
        if (input=="-1") {

            end=true

        } else if (options.containsKey(input)) {
            val newFolder = File(options.getValue(input))

            if (!newFolder.canRead()) {
                errorMessage("You don't have permission in this folder")
            } else

            if (newFolder.isFile) {
                errorMessage("You can't enter into a file")
            } else {
                currentFolder = newFolder
            }

        } else if (input=="0") {

            if (currentFolder.parentFile == null)
                errorMessage("The current folder ($currentFolder) doesn't have a parent file")
            else
                currentFolder = currentFolder.parentFile

        } else {

            errorMessage("Select a correct number!")

        }

    }
}

fun errorMessage(message : String) {
    println(message)
    Thread.sleep(2*1000)
}