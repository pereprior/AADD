package T3.examples.xml

import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory

fun main(args: Array<String>) {

    //example1()
    //example2()
    example3()

}

fun example1() {
    val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("cars.xml")
    println(doc.getNodeName())
    val arrel = doc.getDocumentElement()
    println(arrel.getNodeName())
    println(arrel.getNodeValue())
}

fun example2(){
    val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("cars.xml")
    val arrel = doc.getDocumentElement()
    val fills = arrel.getChildNodes()
    println(fills.item(0).getNodeName())
    println(fills.item(1).getNodeName())
    println(fills.item(2).getNodeName())
    println(fills.item(3).getNodeName())
    println(fills.item(4).getNodeName())
    println(fills.item(5).getNodeName())
}

fun example3(){
    val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("cars.xml")
    val arrel = doc.getDocumentElement()
    val llista = arrel.getElementsByTagName("vehiculo")

    for (i in 0 until llista.getLength()) {
        val el = llista.item(i) as Element
        println(el.getNodeName() + " " + (i + 1))
        println("Marca: " + el.getElementsByTagName("marca").item(0).getChildNodes().item(0).getNodeValue())
        println("Matrícula: " + el.getElementsByTagName("matricula").item(0).getFirstChild().getNodeValue())
        println("Motor: " + el.getElementsByTagName("motor").item(0).getTextContent())
        println("Combustible: " + el.getElementsByTagName("motor").item(0).getAttributes().item(0).getNodeValue())
        val m = el . getElementsByTagName ("motor").item(0) as Element
        println("Combustible: " + m.getAttribute("combustible"))
        println()
    }
    println(arrel.getTextContent())
}