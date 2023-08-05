package model;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import utils.Messages;

/**
 * @author Alex
 */
public class DataBase {

    //Establecemos la conexión en nulo.
    public static Connection conn = null;
    //Declaramos los datos de conexión de la BD
    //private static final String DRIVER = "com.mysql.jdbc.Driver";//Deprecado
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";//Nuevo driver
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/continentes";


    //Método con el que nos conectamos a la Base de datos.
    public void connector() {
        //Resetamos la conexión a nulo.
        conn = null;
        try {
            Class.forName(DRIVER);
            //Nos conectamos a la base de datos.
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            //Mostramos mensaje por pantalla si la conexion a tenido éxito.
            if (conn != null) {
                System.out.println("Conexión realizada con éxito.");
            } else {
                System.out.println("Conexión erronea");
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Error de drivers..");
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Método que nos devuelve el estado de la conexión.
     *
     * @return estado de la conexión a la Base de datos.
     */
    public Connection getConnection() {
        return conn;
    }


}
