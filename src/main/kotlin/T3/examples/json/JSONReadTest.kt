package T3.examples.json

import java.io.FileReader
import org.json.JSONTokener
import org.json.JSONObject

fun main(args: Array<String>) {

    val r_json = FileReader("Employees.json")

    val root = JSONTokener(r_json).nextValue() as JSONObject

    val employee = root.get("empleat") as JSONObject

    println("" + employee.get("nom") + " (" + employee.get("sou") + ")")
}