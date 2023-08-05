package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import model.Country;
import model.CountryDAO;
import model.DataBase;
import utils.Messages;
import utils.ModelTable;
import utils.TableRender;
import utils.UIUtils;
import view.DeletePanel;

/**
 * @author Alex
 */
public class Delete implements ActionListener {

    private final DeletePanel v;
    private final DataBase db = new DataBase();
    private CountryDAO countryDAO = new CountryDAO();

    private final JButton btnDelete = new JButton();

    private final ImageIcon iconDelete = new ImageIcon("src/resources/delete.png");

    public Delete(DeletePanel view) {
        this.v = view;

        setup();
    }

    private void setup() {
        v.btnSearch.addActionListener(this);

        addKeyListeners(v);
        v.scrollPane.setBackground(Color.WHITE);

        initTable();
        propiertiesBtnDelete();
        deleteItem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object ae = e.getSource();

        if (ae == v.btnSearch) {
            searchCountry();
            UIUtils.resetFields(v);
        }

    }

    private void addKeyListeners(Container container) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                KeyListener keyListener = new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        UIUtils.setTextFields(e, v.txtSearch);
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        // Cambiar el texto a mayúsculas
                        JTextField txt = (JTextField) e.getSource();
                        txt.setText(txt.getText().toUpperCase());
                    }
                };

                textField.addKeyListener(keyListener);
            }
        }
    }

    private void searchCountry() {
        ModelTable.deleteCountryTable(v.table, header(), searchResults());

    }

    private void initTable() {
        ModelTable.deleteCountryTable(v.table, header(), getCountriesData());
    }

    private Object[] header() {
        return new Object[]{"Nº Continente", "País", "Nº residentes", "Superficie km2", ""};
    }

    private Object[][] getCountriesData() {

        v.table.setDefaultRenderer(Object.class, new TableRender());

        btnDelete.setIcon(iconDelete);

        List<Country> results = countryDAO.getAllCountries();

        int numDocs = results.size();
        Object[][] rows = new Object[numDocs][5];

        int index = 0;
        for (int i = 0; i < results.size(); i++) {
            rows[index][0] = results.get(i).getNum_continent();
            rows[index][1] = results.get(i).getCountry();
            rows[index][2] = results.get(i).getNum_residents();
            rows[index][3] = results.get(i).getSurface_km2();
            rows[index][4] = btnDelete;
            index++;
        }
        return rows;
    }

    private Object[][] searchResults() {

        v.table.setDefaultRenderer(Object.class, new TableRender());

        btnDelete.setIcon(iconDelete);

        ArrayList<Country> results = countryDAO.searchCountry(v.txtSearch.getText());

        int numDocs = results.size();
        Object[][] rows = new Object[numDocs][5];

        int index = 0;
        for (int i = 0; i < results.size(); i++) {
            rows[index][0] = results.get(i).getNum_continent();
            rows[index][1] = results.get(i).getCountry();
            rows[index][2] = results.get(i).getNum_residents();
            rows[index][3] = results.get(i).getSurface_km2();
            rows[index][4] = btnDelete;
            index++;
        }
        return rows;
    }

    private void propiertiesBtnDelete() {
        btnDelete.setBorder(null);
        btnDelete.setBorderPainted(false);
        btnDelete.setContentAreaFilled(false);
    }

    private void deleteItem() {

        MouseListener listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //capturo fila o columna dependiendo de mi necesidad
                int row = v.table.rowAtPoint(e.getPoint());
                int column = v.table.columnAtPoint(e.getPoint());

                switch (column) {
                    case 4:
                        int reply = Messages.setWarningMessage("Eliminar País", "¿Está seguro que "
                                + "desea eliminar el país de la base de datos?");
                        if (reply == 0) {
                            delete(row);
                            initTable();
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        // Agregar el MouseListener al componente JTable
        v.table.addMouseListener(listener);
    }

    private void delete(int row) {
        UIUtils.selectRow = row;
        String country = v.table.getValueAt(row, 1).toString();
        countryDAO.deleteCountry(country);
    }

    
}
