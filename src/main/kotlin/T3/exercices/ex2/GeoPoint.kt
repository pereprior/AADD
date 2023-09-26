package T3.exercices.ex2

import java.io.Serializable

class GeoPoint(var nom:String, var coord:Coordinate):Serializable {

    companion object {
        private const val serialVersionUID: Long = 1
    }

}