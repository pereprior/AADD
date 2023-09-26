package T1.exercices

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

/**
 * Navegar por los directorios del sistema de archivos
 * Añadido de mayor detalle de los ficheros que muestra
 *
 * @author Pere Prior
 * @since 14/09/23
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

            println(fileData(num,file))
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
                errorsMessage("You don't have permission in this folder")
            } else

                if (newFolder.isFile) {
                    errorsMessage("You can't enter into a file")
                } else {
                    currentFolder = newFolder
                }

        } else if (input=="0") {

            if (currentFolder.parentFile == null)
                errorMessage("The current folder ($currentFolder) doesn't have a parent file")
            else
                currentFolder = currentFolder.parentFile

        } else {

            errorsMessage("Select a correct number!")

        }

    }
}

fun fileData(index:Int,file: File):String {
    val sb = StringBuilder("$index.- ")

    if (file.isDirectory)
        sb.append("d")
    else
        sb.append("-")

    if (file.canRead())
        sb.append("r")
    else
        sb.append("-")

    if (file.canWrite())
        sb.append("w")
    else
        sb.append("-")

    if (file.canExecute())
        sb.append("x")
    else
        sb.append("-")

    // Conseguir la fecha y la hora de creacion del fichero
    val lastModifyDate = file.lastModified()
    val date = Date(lastModifyDate)
    val format = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
    val formatDate = format.format(date)

    sb.append("\t${file.length()}\t$formatDate\t${file.toString()}")

    return sb.toString()
}

fun errorsMessage(message:String) {
    println(message)
    Thread.sleep(2*1000)
}