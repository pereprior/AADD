package T3.exercices.ex5

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.*
import T3.exercices.ex2.Rute

class Rutes(var rutes: MutableList<Rute> = mutableListOf())

fun main(args: Array<String>) {
    val doc = ObjectInputStream(FileInputStream("Rutes.obj"))
    val rutes = Rutes()

    try {
        while (true) {
            val obj = doc.readObject()
            if (obj is Rute) {
                rutes.rutes.add(obj)
            }
        }
    } catch (eof: EOFException) {
        doc.close()
    }

    val moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<Rutes> = moshi.adapter(Rutes::class.java)
    val json = adapter.toJson(rutes)

    File("Rutes.json").writeText(json)

    println("File correctly done.")
}
