package T4.exercices

import java.sql.DriverManager
import java.sql.Statement

fun main() {
    val url = "jdbc:mysql://172.17.0.2:3306/mysql"
    val usuario = "root"
    val password = "secret"

    val con = DriverManager.getConnection(url, usuario, password)
    val st = con.createStatement()

    createTable(st)
    var userInput: Int

    do {
        menu2()
        userInput = readln().toInt()

        when (userInput) {
            1 -> printUsers2(st)
            2 -> insert2(st)
            3 -> update2(st)
            4 -> delete2(st)
        }

        Thread.sleep(2000)
    } while (userInput != 0)

    st.close()
    con.close()
}

private fun createTable(st: Statement) {
    val createTable = """
        CREATE TABLE IF NOT EXISTS USUARIOS (
            id INT AUTO_INCREMENT PRIMARY KEY,
            nombre VARCHAR(50),
            user VARCHAR(50),
            password VARCHAR(50),
            telefono VARCHAR(15),
            email VARCHAR(50)
        )
    """.trimIndent()

    st.executeUpdate(createTable)
}

fun menu2(){
    println("1. Visualizar datos\n" +
            "2. Insertar nuevos datos\n" +
            "3. Actualizar datos\n" +
            "4. Eliminar datos\n" +
            "0. Salir\n" +
            "SELECT AN OPTION: ")
}

fun printUsers2(st: Statement) {
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

fun insert2(st: Statement) {
    println()
    val name = readString2("Introduce el nombre")
    val user = readString2("Introduce el user")
    val passwd = readString2("Introduce la contraseña")
    val tlf = readInt2("Introduce el teléfono")
    val email = readString2("Introduce el email")

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
    } else {
        println("El usuario '$user' ya existe")
    }
}

fun update2(st: Statement) {
    println()
    val id = readInt2("Introduce el ID del usuario que quieras editar")
    if (!checkID2(st, id)) {
        println("Ese ID no existe")
        return
    }

    val name = readString2("Introduce el nuevo nombre")
    val passwd = readString2("Introduce la nueva contraseña")

    val updateQuery = "UPDATE USUARIOS SET nombre = ?, password = ? WHERE id = ?"
    val updateStatement = st.connection.prepareStatement(updateQuery)
    updateStatement.setString(1, name)
    updateStatement.setString(2, passwd)
    updateStatement.setInt(3, id)
    updateStatement.executeUpdate()
}

fun delete2(st: Statement) {
    println()
    val id = readInt2("Introduce el ID del usuario que quieras eliminar")
    if (!checkID2(st, id)) {
        println("Ese ID no existe")
        return
    }

    val deleteQuery = "DELETE FROM USUARIOS WHERE id = ?"
    val deleteStatement = st.connection.prepareStatement(deleteQuery)
    deleteStatement.setInt(1, id)
    deleteStatement.executeUpdate()
}

fun checkID2(st: Statement, id:Int):Boolean {
    val select = "SELECT * FROM USUARIOS"
    val rs = st.executeQuery(select)

    while (rs.next()) {
        if (rs.getInt(1) == id) {
            return true
        }
    }

    return false
}

fun readString2(message:String):String{
    print("$message: ")
    return readln()
}

fun readInt2(message:String):Int{
    print("$message: ")
    return readln().toInt()
}