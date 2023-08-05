package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static model.DataBase.conn;
import utils.Messages;

/**
 * @author Alex
 */
public class CountryDAO {

    private DataBase db = new DataBase();
    private Statement st;
    private ResultSet resultSet;
    private PreparedStatement ps;

    public void insertNewCountry(String name, String residents, JComboBox box, String surface) {
        String query = "INSERT INTO pais (nombre_pais,num_habitantes,num_continente,superficie_km2) VALUES (?,?,?,?)";
        try {
            Connection conn = db.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(query);
                ps.setString(1, name.toUpperCase());
                ps.setInt(2, (int) Long.parseLong(residents));
                ps.setInt(3, box.getSelectedIndex());
                ps.setInt(4, (int) Long.parseLong(surface));

                int insertData = ps.executeUpdate();

                if (insertData >= 1) {
                    Messages.setInfoMessage(".:Insert Register:.", "Registro guardado con éxito");
                } else {
                    Messages.setInfoMessage(".:ERROR:.", "Fallo al registrar el país.");
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
    
    public void deleteCountry(String country) {
        String sql = "DELETE FROM pais WHERE nombre_pais = '" + country + "'";
        PreparedStatement pst;
        if (conn != null) {
            try {
                pst = conn.prepareStatement(sql);
                pst.executeUpdate();
                Messages.setInfoMessage("Eliminar", "Pais eliminado correctamente.");
            } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("No se ha podido establecer la conexión con el servidor.");
        }
    }

    public List<Country> getCountries() {
        String sql = "SELECT nombre_pais FROM PAIS";
        ArrayList<Country> countryList = new ArrayList<>();
        try {

            if (conn != null) {
                st = conn.createStatement();
                resultSet = st.executeQuery(sql);

                while (resultSet.next()) {
                    Country p = new Country();
                    p.setCountry(resultSet.getString("nombre_pais"));
                    countryList.add(p);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return countryList;
    }
    
    public List<Country> getAllCountries() {

        String sql = "SELECT nombre_pais, num_habitantes, num_continente,"
                + "superficie_km2 FROM PAIS";
        ArrayList<Country> countryList = new ArrayList<>();

        try {
            Connection conn = db.getConnection();
            if (conn != null) {
                st = conn.createStatement();
                resultSet = st.executeQuery(sql);

                while (resultSet.next()) {
                    Country p = new Country();
                    p.setCountry(resultSet.getString("nombre_pais"));
                    p.setNum_residents(resultSet.getInt("num_habitantes"));
                    p.setNum_continent(resultSet.getInt("num_continente"));
                    p.setSurface_km2(resultSet.getInt("superficie_km2"));

                    countryList.add(p);
                }

            } else {
                System.out.println("No se ha podido establecer la conexión con el servidor.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return countryList;
    }

    public List<Country> getCountriesByContinent(JComboBox box, JLabel question, JLabel answer) {
        int entries = 0;
        String query = "Select nombre_pais,num_habitantes, num_continente, superficie_km2"
                + " FROM pais WHERE num_continente = " + box.getSelectedIndex() + "";

        ArrayList<Country> list = new ArrayList<>();
        try {
            Connection conn = db.getConnection();
            if (conn != null) {
                st = conn.createStatement();
                resultSet = st.executeQuery(query);

                while (resultSet.next()) {
                    Country c = new Country();
                    c.setCountry(resultSet.getString("nombre_pais"));
                    c.setNum_residents((int) resultSet.getLong("num_habitantes"));
                    c.setNum_continent(resultSet.getInt("num_continente"));
                    c.setSurface_km2((int) resultSet.getLong("superficie_km2"));

                    list.add(c);
                    entries++;
                }

                question.setText("Lista de paises de " + box.getSelectedItem().toString() + ":");
                answer.setText(String.valueOf(entries));
            } else {
                JOptionPane.showMessageDialog(null, "No hay conexión a la base de datos para realizar la consulta",
                        "ERROR", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            System.out.println("Error:: " + ex.getMessage());
        }

        return list;
    }

    public void getTotalPopulationByContient(JComboBox box, JLabel question, JLabel answer) {
        long population = 0;
        String query = "Select SUM(num_habitantes) FROM pais "
                + "where num_continente = " + box.getSelectedIndex() + "";

        if (box.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un continente",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Connection conn = db.getConnection();
                if (conn != null) {
                    st = conn.createStatement();
                    resultSet = st.executeQuery(query);

                    while (resultSet.next()) {
                        String total = resultSet.getString(1);
                        population = Long.parseLong(total);

                    }
                    question.setText("Cálculo total de habitantes:");
                    answer.setText(String.valueOf(population));

                } else {
                    JOptionPane.showMessageDialog(null, "No hay conexión a la base de datos para realizar la consulta",
                            "ERROR", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException ex) {
                System.out.println("Error:: " + ex.getMessage());
            }
        }

    }

    public void getTotalSurfaceByContient(JComboBox box, JLabel question, JLabel answer) {
        long surface = 0;
        String query = "Select SUM(superficie_km2) FROM pais "
                + "where num_continente = " + box.getSelectedIndex() + "";

        if (box.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un continente",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Connection conn = db.getConnection();
                if (conn != null) {
                    st = conn.createStatement();
                    resultSet = st.executeQuery(query);

                    while (resultSet.next()) {
                        String total = resultSet.getString(1);
                        surface = Long.parseLong(total);

                    }
                    question.setText("Cálculo total de superficies en km2:");
                    answer.setText(String.valueOf(surface));

                } else {
                    JOptionPane.showMessageDialog(null, "No hay conexión a la base de datos para realizar la consulta",
                            "ERROR", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException ex) {
                System.out.println("Error:: " + ex.getMessage());
            }
        }
    }

    public boolean isCountryRegistered(String country) {
        List<Country> list = getCountries();
        for (int i = 0; i < list.size(); i++) {
            if (country.equalsIgnoreCase(list.get(i).getCountry())) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Método donde buscamos en la base de datos todos los elementos que
     * coincidan con el texto introduciso en el JTextField.
     *
     * @param nameCountry nombre del país.
     * @return lista con los elementos que coinciden con el texto introducido.
     */
    public ArrayList<Country> searchCountry(String nameCountry) {

        String sql = "SELECT * FROM PAIS WHERE nombre_pais LIKE ?";
        ArrayList<Country> countryNameList = new ArrayList<>();

        try {
            if (conn != null) {
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + nameCountry + "%");

                resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    Country c = new Country();
                    c.setCountry(resultSet.getString("nombre_pais"));
                    c.setNum_residents(resultSet.getInt("num_habitantes"));
                    c.setNum_continent(resultSet.getInt("num_continente"));
                    c.setSurface_km2(resultSet.getInt("superficie_km2"));

                    countryNameList.add(c);
                }

            } else {
                System.out.println("No se ha podido establecer la conexión con el servidor.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return countryNameList;
    }
}
