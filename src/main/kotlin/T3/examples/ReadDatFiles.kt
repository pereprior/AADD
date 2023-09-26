package T3.examples

import java.io.DataInputStream
import java.io.FileInputStream

fun main(args: Array<String>) {
    val f = DataInputStream(FileInputStream("Employees.dat"))

    while (f.available() > 0) {
        System.out.println("Number: " + f.readInt())
        System.out.println("Name: " + f.readUTF())
        System.out.println("Department: " + f.readInt())
        System.out.println("Age: " + f.readInt())
        System.out.println("Salary: " + f.readDouble())
        System.out.println()
    }
    f.close()
}