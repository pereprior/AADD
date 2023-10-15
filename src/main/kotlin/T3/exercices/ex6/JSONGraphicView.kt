package T3.exercices.ex6

import T3.exercices.ex5.Rutes
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.awt.BorderLayout
import java.awt.EventQueue
import java.awt.FlowLayout
import java.io.File
import java.lang.StringBuilder
import javax.swing.*

fun main(args: Array<String>) {
    EventQueue.invokeLater {
        FinestraJSON().isVisible = true
    }
}

class FinestraJSON : JFrame() {

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setTitle("Punts d'una ruta")
        setSize(400, 300)
        setLayout(BorderLayout())

        val panell1 = JPanel(FlowLayout())
        val panell2 = JPanel(BorderLayout())
        add(panell1, BorderLayout.NORTH)
        add(panell2, BorderLayout.CENTER)

        val combo = JComboBox(routesName().toArray())
        panell1.add(combo)

        val area = JTextArea()
        area.append(pointsList(0))
        panell2.add(JLabel("Llista de punts de la ruta:"), BorderLayout.NORTH)
        panell2.add(area)

        combo.addActionListener {
            area.text = ""
            var index = combo.selectedIndex

            area.append(pointsList(index))
        }
    }

    fun routesName(): ArrayList<String> {
        val result = ArrayList<String>()

        val json = File("Rutes.json").readText()

        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Rutes> = moshi.adapter(Rutes::class.java)
        val rutes = adapter.fromJson(json)

        rutes?.rutes?.forEach {
            rute -> result.add(rute.nom)
        }

        return result
    }

    fun pointsList(index: Int): String {
        var result = StringBuilder()

        val json = File("Rutes.json").readText()

        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Rutes> = moshi.adapter(Rutes::class.java)
        val rutes = adapter.fromJson(json)

        val rutesList = rutes?.rutes?.get(index)
        if (rutesList != null) {
            for (e in rutesList.llistaDePunts.listIterator())
                result.append("${e.nom} (${e.coord.latitude}, ${e.coord.length})\n")
        }

        return result.toString()
    }

}