package T4.exercices;

import java.sql.*;

public class Prueba {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://172.17.0.3:5432/postgres";
        String usuario = "postgres";
        String password = "secret";
        Connection con = DriverManager.getConnection(url,usuario,password);

        CallableStatement cStm = con.prepareCall(
                "{call CantidadPersonas(?)}"
        );
        cStm.registerOutParameter(1,Types.INTEGER);
        cStm.setString(1,"M%");

        cStm.execute();
        int resultado = cStm.getInt(1);
        System.out.println("Resultado: "+resultado);
        con.close();
    }

}
