package T3.exercices.ex3

import T3.exercices.ex2.Rute
import java.io.EOFException
import java.io.FileInputStream
import java.io.ObjectInputStream
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

fun main(args:Array<String>){
    val inputStream = ObjectInputStream(FileInputStream ("Rutes.obj"))

    val document = DocumentBuilderFactory.newInstance ().newDocumentBuilder().newDocument()
    val element = document.createElement ("rutes")
    document.appendChild(element)

    try {
        while (true) {
            val currentRute = inputStream.readObject () as Rute
            val rute = document.createElement ("ruta")

            val name = document.createElement ("nom")
            name.appendChild(document.createTextNode(currentRute.nom))
            rute.appendChild(name)

            val desnivell = document.createElement ("desnivell")
            desnivell.appendChild(document.createTextNode(currentRute.desnivell.toString()))
            rute.appendChild(desnivell)

            val desnivellAcumulat = document.createElement ("desnivellAcumulat")
            desnivellAcumulat.appendChild(document.createTextNode(currentRute.desnivellAcumulat.toString()))
            rute.appendChild(desnivellAcumulat)

            val points = document.createElement ("punts")
            for (e in 0 until currentRute.size()) {
                val desnivellAcumulat = document.createElement ("desnivellAcumulat")
                desnivellAcumulat.appendChild(document.createTextNode(currentRute.desnivellAcumulat.toString()))
                points.appendChild(desnivellAcumulat)
            }
            rute.appendChild(points)

            element.appendChild(rute)
        }

    } catch (eof: EOFException) {
        inputStream.close();
    }
    val trans = TransformerFactory.newInstance().newTransformer()

    trans.transform(DOMSource(document), StreamResult("Empleats.xml"))
}