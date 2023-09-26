package T3.examples

import java.io.FileOutputStream
import java.io.ObjectOutputStream

fun main(args: Array<String>) {
    val f = ObjectOutputStream(FileOutputStream("Employees.obj"))

    val names = arrayOf("Andreu", "Bernat", "Clàudia", "Damià")
    val departaments = arrayOf(10, 20, 10, 10)
    val ages = arrayOf(32, 28, 26, 40)
    val salary = arrayOf(1000.0, 1200.0, 1100.0, 1500.0)

    for (i in 0..3){
        val e = Employee (i + 1, names[i], departaments[i], ages[i], salary[i])
        f.writeObject(e)
    }

    println("File created")
    f.close();
}