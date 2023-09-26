package T3.exercices.ex2

import java.io.EOFException
import java.io.FileInputStream
import java.io.ObjectInputStream

fun main(args:Array<String>){
    val fileInput = ObjectInputStream(FileInputStream("Rutes.obj"))

    try {
        while (true) {
            val rute = fileInput.readObject() as Rute
            rute.mostrarRuta()
        }
    } catch (eof: EOFException) {
        fileInput.close()
    }

}