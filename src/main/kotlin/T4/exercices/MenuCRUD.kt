package T4.exercices

import java.sql.DriverManager
import java.sql.Statement

fun main(){

    val url = "jdbc:sqlite:PerePruebas.sqlite"
    val con = DriverManager.getConnection(url)
    val st = con.createStatement()
    var userInput: Int

    do {
        menu1()
        userInput = readln().toInt()

        when(userInput){
            1 -> printUsers1(st)
            2 -> insert(st)
            3 -> update(st)
            4 -> delete(st)
        }

        Thread.sleep(2000)
    } while (userInput != 0)

    st.close();
    con.close()

}

fun menu1(){
    println("1. Visualizar datos\n" +
            "2. Insertar nuevos datos\n" +
            "3. Actualizar datos\n" +
            "4. Eliminar datos\n" +
            "0. Salir\n" +
            "SELECT AN OPTION: ")
}

fun printUsers1(st: Statement) {
    val select = "SELECT * FROM USUARIOS"
    val rs = st.executeQuery(select)

    System.out.println("ID \tNombre \tUser \tPassword \tTlf \tEmail")
    System.out.println("--------------------------------------------------")

    var count = 0
    while (rs.next()) {
        print("" + rs.getInt(1) + "\t")
        print(rs.getString(2) + "\t")
        print(rs.getString(3) + "\t")
        print(rs.getString(4) + "\t")
        print("" + rs.getInt(5) + "\t")
        println(rs.getString(6) + "\t")
        count++
    }

    println("Registros totales: $count")

    rs.close()
}

fun insert(st: Statement) {
    println()
    val name = readString("Introduce el nombre")
    val user = readString("Introduce el user")
    val passwd = readString("Introduce la contraseña")
    val tlf = readInt("Introduce el teléfono")
    val email = readString("Introduce el email")

    val checkQuery = "SELECT COUNT(*) FROM USUARIOS WHERE user = ?"
    val checkStatement = st.connection.prepareStatement(checkQuery)
    checkStatement.setString(1, user)
    val resultSet = checkStatement.executeQuery()

    if (resultSet.next() && resultSet.getInt(1) == 0) {
        val insertQuery = "INSERT INTO USUARIOS (nombre, user, password, telefono, email) VALUES (?, ?, ?, ?, ?)"

        val insertStatement = st.connection.prepareStatement(insertQuery)
        insertStatement.setString(1, name)
        insertStatement.setString(2, user)
        insertStatement.setString(3, passwd)
        insertStatement.setInt(4, tlf)
        insertStatement.setString(5, email)
        insertStatement.executeUpdate()

    } else println("El usuario '$user' ya existe")
}

fun update(st: Statement) {
    println()
    val id = readInt("Introduce el ID del usuario que quieras editar")
    if (!checkID(st, id)) {
        println("Ese ID no existe")
        return
    }

    val name = readString("Introduce el nuevo nombre")
    val passwd = readString("Introduce la nueva contraseña")

    val updateQuery = "UPDATE USUARIOS SET nombre = ?, password = ? WHERE id = ?"
    val updateStatement = st.connection.prepareStatement(updateQuery)
    updateStatement.setString(1, name)
    updateStatement.setString(2, passwd)
    updateStatement.setInt(3, id)
    updateStatement.executeUpdate()
}

fun delete(st: Statement) {
    println()
    val id = readInt("Introduce el ID del usuario que quieras eliminar")
    if (!checkID(st, id)) {
        println("Ese ID no existe")
        return
    }

    val deleteQuery = "DELETE FROM USUARIOS WHERE id = ?"
    val deleteStatement = st.connection.prepareStatement(deleteQuery)
    deleteStatement.setInt(1, id)
    deleteStatement.executeUpdate()
}

fun checkID(st: Statement, id:Int):Boolean {
    val select = "SELECT * FROM USUARIOS"
    val rs = st.executeQuery(select)

    while (rs.next()) {
        if (rs.getInt(1) == id) {
            return true
        }
    }

    return false
}

fun readString1(message:String):String{
    print("$message: ")
    return readln()
}

fun readInt1(message:String):Int{
    print("$message: ")
    return readln().toInt()
}