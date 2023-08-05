package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static model.DataBase.conn;
import utils.Messages;
import utils.UIUtils;

/**
 * @author Alex
 */
public class ContinentDAO {

    private DataBase db = new DataBase();
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement ps;

    /**
     *
     * @param panel
     * @param name
     */
    public void insertNewContinent(String name) {
        String query = "INSERT INTO continente (nombre_continente) VALUES (?)";
        try {
            Connection conn = db.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(query);
                ps.setString(1, name.toUpperCase());

                int insertData = ps.executeUpdate();

                if (insertData >= 1) {
                    Messages.setInfoMessage(".:Insert Register:.", "Registro guardado con éxito");
                } else {
                    Messages.setInfoMessage(".:ERROR:.", "Fallo al registrar el continente.");
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al realizar la operacion, "
                        + "intentelo mas tarde.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, ".::ERROR::." + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }

    /**
     * Método para recuperar todos los Continentes guardados en la base de
     * datos.
     *
     * @return listado con el nombre de los contienentes
     */
    public List<Continent> getContinents() {
        String sql = "SELECT nombre_continente FROM Continente";
        ArrayList<Continent> listContinent = new ArrayList<>();

        try {

            if (conn != null) {
                statement = conn.createStatement();
                resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    Continent c = new Continent();
                    c.setNombre_continent(resultSet.getString("nombre_continente"));
                    listContinent.add(c);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return listContinent;
    }

    public boolean existContient(String continent) {
        if (!continent.equalsIgnoreCase("Oceania") && !continent.equalsIgnoreCase("Europa")
                && !continent.equalsIgnoreCase("Asia") && !continent.equalsIgnoreCase("Africa")
                && !continent.equalsIgnoreCase("America") && !continent.equalsIgnoreCase("Antartida")) {

            return false;
        } else {
            return true;
        }
    }

    public boolean isContinentRegistered(String continent) {
        List<Continent> list = getContinents();
        for (int i = 0; i < list.size(); i++) {
            if (continent.equalsIgnoreCase(list.get(i).getNombre_continent())) {
                return false;
            }
        }
        return true;

    }
}
