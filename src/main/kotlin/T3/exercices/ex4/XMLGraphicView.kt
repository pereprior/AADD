package T3.exercices.ex4

import javax.swing.*
import java.awt.*
import org.w3c.dom.Element
import java.lang.StringBuilder
import javax.xml.parsers.DocumentBuilderFactory

fun main(args: Array<String>) {
    EventQueue.invokeLater {
        Window().isVisible = true
    }
}

class Window : JFrame() {

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setTitle("Punts d'una ruta")
        setSize(400, 300)
        setLayout(BorderLayout())

        val panell1 = JPanel(FlowLayout())
        val panell2 = JPanel(BorderLayout())
        add(panell1, BorderLayout.NORTH)
        add(panell2, BorderLayout.CENTER)

        val llistaRutes = routesName()

        val combo = JComboBox(llistaRutes.toArray())
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

    fun routesName():ArrayList<String> {
        var result = ArrayList<String>()

        val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("Rutes.xml")
        val root = doc.documentElement
        val list = root.getElementsByTagName("ruta")

        for (i in 0 until list.getLength()) {
            val element = list.item(i) as Element

            val name = element.getElementsByTagName("nom").item(0).getChildNodes().item(0).getNodeValue()
            result.add(name)
        }

        return result
    }

    fun pointsList(index: Int): String {
        var result = StringBuilder()

        val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("Rutes.xml")
        val root = doc.documentElement
        val rutes = root.getElementsByTagName("ruta")

        val ruta = rutes.item(index) as Element
        val punts = ruta.getElementsByTagName("point")

        for (i in 0 until punts.length) {
            val point = punts.item(i) as Element

            val name = point.getElementsByTagName("nom").item(0).textContent
            val latitude = point.getElementsByTagName("latitud").item(0).textContent
            val longitude = point.getElementsByTagName("longitud").item(0).textContent

            result.append("$name ($latitude, $longitude)\n")
        }

        return result.toString()
    }

}
