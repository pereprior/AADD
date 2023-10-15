package T3.examples.json

import org.json.JSONObject
import org.json.JSONTokener
import java.io.FileReader

fun main(args: Array<String>) {

    val r_json = FileReader("Empresa.json")

    val arrel = JSONTokener(r_json).nextValue() as JSONObject
    val empresa = arrel.getJSONObject("empresa")

    for (e in empresa.getJSONArray("empleats")){
        val emp = e as JSONObject
        println("" + emp.get("nom") + " (" + emp.get("sou") + ")")
    }
}