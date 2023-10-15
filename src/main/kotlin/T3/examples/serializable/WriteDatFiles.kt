package T3.examples.serializable

import java.io.DataOutputStream
import java.io.FileOutputStream

fun main(args: Array<String>) {
    val file = DataOutputStream(FileOutputStream("Employees.dat"))

    val names = arrayOf("Andreu", "Bernat", "Clàudia", "Damià")
    val departments = arrayOf( 10, 20, 10, 10 )
    val ages = arrayOf( 32, 28, 26, 40 )
    val salary = arrayOf( 1000.0, 1200.0, 1100.0, 1500.0 )

    for (i in 0..3){
        file.writeInt(i + 1)
        file.writeUTF(names[i])
        file.writeInt(departments[i])
        file.writeInt(ages[i])
        file.writeDouble(salary[i])
    }
    file.close()
    println("File created")
}