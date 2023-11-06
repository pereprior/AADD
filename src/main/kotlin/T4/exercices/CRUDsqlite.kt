package T4.exercices

import java.sql.DriverManager
import java.sql.Statement

fun main() {

    val url = "jdbc:sqlite:PerePruebas.sqlite"
    val con = DriverManager.getConnection(url)
    val st = con.createStatement()

    crud(st)
    printTable(st)

    st.close();
    con.close()

}

fun crud(st: Statement){
    val sentC = "CREATE TABLE IF NOT EXISTS USUARIOS(" +
                "id INTEGER CONSTRAINT pk_empleat PRIMARY KEY, " +
                "nombre VARCHAR(50), " +
                "user VARCHAR(50), " +
                "password VARCHAR(50), " +
                "telefono INTEGER(9)," +
                "email VARCHAR(50)" +
                ")"
    st.executeUpdate(sentC)

    val sentR = "INSERT INTO USUARIOS (id,nombre, user, password, telefono, email) " +
                "VALUES (1,'Juan Pérez', 'juanp', 'contraseña123', 123456789, 'juan.perez@gmail.com');\n" +

                "INSERT INTO USUARIOS (id,nombre, user, password, telefono, email) " +
                "VALUES (2,'Pere Prior', 'perep', 'contraseña321', 987654321, 'pere.prior@gmail.com');"
    st.executeUpdate(sentR)

    val sentU = "UPDATE USUARIOS " +
                "SET user = 'pprior' " +
                "WHERE id = 2;"
    st.executeUpdate(sentU)

    val sentD = "DELETE FROM USUARIOS\n" +
                "WHERE user = 'juanp';\n"
    st.executeUpdate(sentD)
}

fun printTable(st: Statement) {
    val select = "SELECT * FROM USUARIOS"
    val rs = st.executeQuery(select)

    System.out.println("ID \tNombre \tUser \tPassword \tTlf \tEmail")
    System.out.println("--------------------------------------------------")

    while (rs.next()) {
        print("" + rs.getInt(1) + "\t")
        print(rs.getString(2) + "\t")
        print(rs.getString(3) + "\t")
        print(rs.getString(4) + "\t")
        print("" + rs.getInt(5) + "\t")
        println(rs.getString(6) + "\t")
    }

    rs.close()
}