package T3.exercices.ex7

import org.w3c.dom.Element
import java.io.File
import java.io.FileNotFoundException
import javax.xml.parsers.DocumentBuilderFactory

fun main() {
    val data = readXMLFile()
    println(data)
}

fun readXMLFile():CarList {
    val cars = CarList()
    val file = File("cars.xml")
    if (!file.exists()) throw FileNotFoundException() else println("siu")

    val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file)
    val root = doc.documentElement
    val list = root.getElementsByTagName("vehiculo")

    for (i in 0 until list.getLength()) {
        val element = list.item(i)
        if (element is Element) {
            val brand = element.getElementsByTagName("marca").item(0).getChildNodes().item(0).getNodeValue()
            val attModel = element.getElementsByTagName("color").item(0) as Element
            val color = attModel.getAttribute("color")
            val model = attModel.textContent
            val carModel = CarModel(brand,model,color)

            val attEngine = element.getElementsByTagName("motor").item(0) as Element
            val fuel = attEngine.getAttribute("combustible")
            val engine = attEngine.textContent
            val carEngine = CarEngine(engine,fuel)

            val id = element.getElementsByTagName("matricula").item(0).getChildNodes().item(0).getNodeValue()
            val km = element.getElementsByTagName("kilometros").item(0).getChildNodes().item(0).getNodeValue().toInt()
            val price = element.getElementsByTagName("precio_inicial").item(0).getChildNodes().item(0).getNodeValue()
            val offer = element.getElementsByTagName("precio_oferta").item(0).getChildNodes().item(0).getNodeValue()

            val extraElements = element.getElementsByTagName("extra")
            val features = ArrayList<ExtraFeatures>()
            for (j in 0 until extraElements.getLength()) {
                val extraElement = extraElements.item(j) as Element

                val extraValue = extraElement.getAttribute("valor")
                val extraName = extraElement.textContent
                val feature = ExtraFeatures(extraName,extraValue.toDouble())

                features.add(feature)
            }
            val totalPrice = CarPrice(price.toDouble(),offer.toDouble(),features)

            val picElements = element.getElementsByTagName("foto")
            val pictures = ArrayList<String>()
            for (j in 0 until picElements.getLength()) {
                val picElement = picElements.item(j) as Element

                val picture = picElement.textContent
                pictures.add(picture)
            }

            val car = Car(carModel,carEngine,id,km,totalPrice,pictures)
            cars.rutes[i] = car
        }
    }

    return cars
}

/*private fun writeJSONFile() {

}*/