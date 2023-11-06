package T4.exercices

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

fun main() {
    val url = "jdbc:sqlite:PerePruebas.sqlite"
    DriverManager.getConnection(url).use { con ->
        con.autoCommit = false
        val st = con.createStatement()
        var userInput: Int

        do {
            menu()
            userInput = readln().toInt()

            when (userInput) {
                1 -> printUsers(st)
                2 -> insert(con)
                3 -> update(con)
                4 -> delete(con)
            }

            Thread.sleep(2000)
        } while (userInput != 0)
    }
}

fun menu() {
    println("1. Visualizar datos\n" +
            "2. Insertar nuevos datos\n" +
            "3. Actualizar datos\n" +
            "4. Eliminar datos\n" +
            "0. Salir\n" +
            "SELECT AN OPTION: ")
}

fun printUsers(st: Statement) {
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


fun insert(con: Connection) {
    println()
    val name = readString("Introduce el nombre")
    val user = readString("Introduce el usuario")
    val passwd = readString("Introduce la contraseña")
    val tlf = readInt("Introduce el teléfono")
    val email = readString("Introduce el email")

    val checkQuery = "SELECT COUNT(*) FROM USUARIOS WHERE user = ?"
    con.prepareStatement(checkQuery).use { checkStatement ->
        checkStatement.setString(1, user)
        val resultSet = checkStatement.executeQuery()

        if (resultSet.next() && resultSet.getInt(1) == 0) {
            val insertQuery = "INSERT INTO USUARIOS (nombre, user, password, telefono, email) VALUES (?, ?, ?, ?, ?)"

            con.prepareStatement(insertQuery).use { insertStatement ->
                insertStatement.setString(1, name)
                insertStatement.setString(2, user)
                insertStatement.setString(3, passwd)
                insertStatement.setInt(4, tlf)
                insertStatement.setString(5, email)
                insertStatement.executeUpdate()
                con.commit()
            }
        } else println("El usuario '$user' ya existe")
    }
}

fun update(con: Connection) {
    println()
    val id = readInt("Introduce el ID del usuario que quieras editar")
    if (!checkID(con, id)) {
        println("Ese ID no existe")
        return
    }

    val name = readString("Introduce el nuevo nombre")
    val passwd = readString("Introduce la nueva contraseña")

    val updateQuery = "UPDATE USUARIOS SET nombre = ?, password = ? WHERE id = ?"
    con.prepareStatement(updateQuery).use { updateStatement ->
        updateStatement.setString(1, name)
        updateStatement.setString(2, passwd)
        updateStatement.setInt(3, id)
        updateStatement.executeUpdate()
        con.commit()
    }
}

fun delete(con: Connection) {
    println()
    val id = readInt("Introduce el ID del usuario que quieras eliminar")
    if (!checkID(con, id)) {
        println("Ese ID no existe")
        return
    }

    val deleteQuery = "DELETE FROM USUARIOS WHERE id = ?"
    con.prepareStatement(deleteQuery).use { deleteStatement ->
        deleteStatement.setInt(1, id)
        deleteStatement.executeUpdate()
        con.commit()
    }
}

fun checkID(con: Connection, id: Int): Boolean {
    val selectQuery = "SELECT id FROM USUARIOS WHERE id = ?"
    con.prepareStatement(selectQuery).use { selectStatement ->
        selectStatement.setInt(1, id)
        val resultSet = selectStatement.executeQuery()
        return resultSet.next()
    }
}

fun readString(message:String):String {
    print("$message: ")
    return readln()
}

fun readInt(message:String):Int {
    print("$message: ")
    return readln().toInt()
}