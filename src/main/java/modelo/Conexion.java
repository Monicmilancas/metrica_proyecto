package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    /**
     * Conecta a la base de datos.
     *
     * @return La conexión a la base de datos o null si falla.
     */
    public static Connection conectar() {

        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ddb253208", "root", "");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
