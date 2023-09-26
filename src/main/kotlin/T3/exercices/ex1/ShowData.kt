package T3.exercices.ex1

import java.io.DataInputStream
import java.io.FileInputStream

fun main(){
    val f = DataInputStream(FileInputStream("Rutes.dat"))

    while (f.available() > 0) {
        println("Ruta: " + f.readUTF())
        println("Desnivell: " + f.readInt())
        println("Desnivell acumulat: " + f.readInt())

        val pointsNumber = f.readInt()
        println("Té " + pointsNumber + " punts")
        for (e in 0 until pointsNumber) {
            val actualPoint = e+1

            println("Punt "+ actualPoint +": " + f.readUTF() + " (" + f.readDouble() + ", " + f.readDouble() + ")")
        }

        println()

    }
    f.close()
}