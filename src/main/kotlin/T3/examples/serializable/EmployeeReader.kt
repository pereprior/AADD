package T3.examples.serializable

import T3.examples.Employee
import java.io.EOFException
import java.io.FileInputStream
import java.io.ObjectInputStream

fun main(args: Array<String>) {
    val f = ObjectInputStream(FileInputStream("Employees.obj"))

    try {
        while (true) {
            val e = f.readObject() as Employee
            println("Number: " + e.num)
            println("Name: " + e.nom)
            println("Department: " + e.departament)
            println("Age: " + e.edat)
            println("Salary: " + e.sou)
            println()
        }
    } catch (eof: EOFException) {
        f.close()
    }
}