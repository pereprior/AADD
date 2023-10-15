package T3.examples.xml

import T3.examples.Employee
import java.io.ObjectInputStream
import java.io.FileInputStream
import javax.xml.parsers.DocumentBuilderFactory
import java.io.EOFException
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

fun main(args: Array<String>) {
    val f = ObjectInputStream(FileInputStream ("Employees.obj"))

    val doc = DocumentBuilderFactory.newInstance ().newDocumentBuilder().newDocument()
    val arrel = doc.createElement ("empleats")
    doc.appendChild(arrel)

    try {
        while (true) {
            val e = f.readObject () as Employee
            val emp = doc.createElement ("empleat")
            emp.setAttribute("numero", Integer.toString(e.num))

            val nom = doc.createElement ("nom")
            nom.appendChild(doc.createTextNode(e.nom))
            emp.appendChild(nom)

            val dep = doc.createElement("departament")
            dep.setTextContent(e.departament.toString())
            emp.appendChild(dep)

            val edat = doc.createElement("edat")
            edat.setTextContent(e.edat.toString())
            emp.appendChild(edat)

            val sou = doc.createElement("sou");
            sou.setTextContent(e.sou.toString())
            emp.appendChild(sou)

            arrel.appendChild(emp)
        }

    } catch (eof: EOFException) {
        f.close();
    }
    val trans = TransformerFactory.newInstance().newTransformer()

    trans.setOutputProperty(OutputKeys.INDENT, "yes")
    trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")
    trans.transform(DOMSource(doc), StreamResult("Employees.xml"))
}
