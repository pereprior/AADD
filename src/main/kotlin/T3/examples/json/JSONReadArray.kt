package T3.examples.json

import org.json.JSONObject
import org.json.JSONTokener
import java.io.FileReader
import org.json.JSONArray

fun main(args: Array<String>) {

    val r_json = FileReader("Bicicas.json")

    val arrel = JSONTokener(r_json).nextValue() as JSONArray

    val estacions = arrel.getJSONObject(0).getJSONArray("ocupacion")

    for (e in estacions){
        val est = e as JSONObject
        println("" + e.get("id") + ".- " + e.get("punto") + " (" + e.get("ocupados") + "/" + e.get("puestos") + ")")
    }
}