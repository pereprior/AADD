package T4.exercices;

import java.sql.*;

public class PruebaLista {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://172.17.0.3:5432/postgres";
        String usuario = "postgres";
        String password = "secret";
        Connection con = DriverManager.getConnection(url,usuario,password);

        PreparedStatement pSmt = con.prepareStatement(
                "SELECT listapersonas(?)"
        );
        pSmt.setString(1,"%A%");
        ResultSet rs = pSmt.executeQuery();

        while (rs.next()) {
            System.out.println("Detalles: "+rs.getString(1));
        }

        rs.close();
        con.close();
    }

}
