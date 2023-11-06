package T4.exercices;

import java.sql.*;

public class PruebaUsers {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://172.17.0.3:5432/postgres";
        String usuario = "postgres";
        String password = "secret";
        Connection con = DriverManager.getConnection(url,usuario,password);

        Statement s = con.createStatement();
        String consulta = "select cantidadtotalusuarios()";
        ResultSet rs = s.executeQuery(consulta);
        rs.next();
        System.out.println("Resultado: " + rs.getInt(1));
        con.close();
    }

}
