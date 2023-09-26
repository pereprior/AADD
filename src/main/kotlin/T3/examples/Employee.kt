package T3.examples

import java.io.Serializable

class Employee (var num: Int,var nom: String,var departament: Int,var edat: Int,var sou: Double): Serializable {
    companion object {
        private const val serialVersionUID: Long = 1
    }
}