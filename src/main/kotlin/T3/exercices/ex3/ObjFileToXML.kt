package T3.exercices.ex3

import T3.exercices.ex2.Rute
import java.io.EOFException
import java.io.FileInputStream
import java.io.ObjectInputStream
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

fun main(args: Array<String>) {
    val f = ObjectInputStream(FileInputStream ("Rutes.obj"))

    val doc = DocumentBuilderFactory.newInstance ().newDocumentBuilder().newDocument()
    val root = doc.createElement ("rutes")
    doc.appendChild(root)

    try {
        while (true) {
            val e = f.readObject () as Rute
            val rute = doc.createElement ("ruta")

            val name = doc.createElement ("nom")
            name.appendChild(doc.createTextNode(e.nom))
            rute.appendChild(name)

            val desnivell = doc.createElement("desnivell")
            desnivell.setTextContent(e.desnivell.toString())
            rute.appendChild(desnivell)

            val acumulat = doc.createElement("desnivellAcumulat")
            acumulat.setTextContent(e.desnivellAcumulat.toString())
            rute.appendChild(acumulat)

            val points = doc.createElement ("punts")

            for (i in 0 until e.size()){
                val point = doc.createElement("point")
                point.setAttribute("num", Integer.toString(i))

                val pointName = doc.createElement("nom")
                pointName.setTextContent(e.getPuntNom(i))
                point.appendChild(pointName)

                val latitude = doc.createElement("latitud")
                latitude.setTextContent(e.getPuntLatitud(i).toString())
                point.appendChild(latitude)

                val length = doc.createElement("longitud")
                length.setTextContent(e.getPuntLongitud(i).toString())
                point.appendChild(length)

                points.appendChild(point)
            }

            rute.appendChild(points)
            root.appendChild(rute)
        }

    } catch (eof: EOFException) {
        f.close();
    }
    val trans = TransformerFactory.newInstance().newTransformer()

    trans.setOutputProperty(OutputKeys.INDENT, "yes")
    trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")
    trans.transform(DOMSource(doc), StreamResult("Rutes.xml"))

    println("File correctly created")
}
