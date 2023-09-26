package T3.exercices.ex2

import java.io.Serializable
class Rute (var nom: String, var desnivell: Int, var desnivellAcumulat: Int, var llistaDePunts: MutableList<GeoPoint>): Serializable {
    companion object {
        private const val serialVersionUID: Long = 1
    }

    fun addPunt(p: GeoPoint) {
        llistaDePunts.add(p)
    }

    fun getPunt(i: Int): GeoPoint{
        return llistaDePunts.get(i)
    }

    fun getPuntNom(i: Int): String {
        return llistaDePunts.get(i).nom
    }

    fun getPuntLatitud(i: Int): Double {
        return llistaDePunts.get(i).coord.latitude
    }

    fun getPuntLongitud(i: Int): Double {
        return llistaDePunts.get(i).coord.length
    }

    fun size(): Int {
        return llistaDePunts.size
    }

    fun mostrarRuta() {
        val pointsNumber = llistaDePunts.size

        print("Ruta: "+nom+
                "\nDesnivell: "+ desnivell+
                "\nDesnivell acumulat: "+desnivellAcumulat+
                "\nTe "+pointsNumber+" punts\n")

        for (e in 0 until pointsNumber) {
            val actualPoint = e+1

            println("Punt "+ actualPoint +": " + getPuntNom(e) + " (" + getPuntLatitud(e) + ", " + getPuntLongitud(e) + ")")
        }
        println()
    }
}