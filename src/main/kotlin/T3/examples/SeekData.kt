package T3.examples

import java.io.RandomAccessFile
import java.util.*

fun main(args: Array<String>) {
    val f = RandomAccessFile("Employees.dat", "r")
    f.seek(56) /* La formula de la posicion es la siguiente en cada fila
                       INT: 4 bytes
                       UTF: 2 bytes + numero de caracteres
                       DOUBLE: 8 bytes */

    showEmployeeData(f)
    f.close()
}

fun seek(f:RandomAccessFile) {
    // ESTO SOLO FUNCIONA SI TODAS LAS COLUMNAS DEL FICHERO .DAT TIENEN LOS MISMOS BYTES
    val sc = Scanner(System.`in`)
    println("Select a employee? (-1 to exit): ")

    var num = sc.nextInt ()
    while (num != -1) {
        f.seek(32 * (num - 1).toLong())

        showEmployeeData(f)

        println("Quin registre? (-1 per a eixir): ")
        num = sc.nextInt()
    }

    f.close()
}

fun showEmployeeData(f:RandomAccessFile){
    println("Number: " + f.readInt())
    println("Name: " + f.readUTF())
    println("Department: " + f.readInt())
    println("Age: " + f.readInt())
    println("Salary: " + f.readDouble())
    println()
}