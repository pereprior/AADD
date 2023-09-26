package T3.exercices.ex2

import java.io.Serializable

class Coordinate (var latitude:Double, var length: Double): Serializable {
    companion object {
        private const val serialVersionUID: Long = 1
    }
}