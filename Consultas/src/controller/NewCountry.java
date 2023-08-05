package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;
import model.ContinentDAO;
import model.CountryDAO;
import utils.Messages;
import utils.UIUtils;
import view.NewCountryView;

/**
 *
 * @author Alex
 */
public class NewCountry implements ActionListener {

    private final NewCountryView v;
    private final ContinentDAO continentDAO = new ContinentDAO();
    private final CountryDAO countryDAO = new CountryDAO();

    public NewCountry(NewCountryView view) {
        this.v = view;

        v.txtContinent.setEnabled(false);
        v.txtContinent.setText("");

        setup();
    }

    private void setup() {
        v.btnSave.addActionListener(this);
        v.btnCancel.addActionListener(this);
        v.rbAddContinent.addActionListener(this);
        v.cbCountries.addActionListener(this);
        v.btnAddContinent.addActionListener(this);

        UIUtils.fillContinentComboBox(v.cbCountries, continentDAO.getContinents());
        enabledComponents();
        addKeyListeners();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();

        if (ae == v.btnCancel) {
            UIUtils.resetFields(v.getParent());
        }

        if (ae == v.cbCountries) {
            String selectedValue = (String) v.cbCountries.getSelectedItem();
            v.txtContinent.setText(selectedValue);
        }

        if (ae == v.rbAddContinent) {
            enabledComponents();
        }

        if (ae == v.btnAddContinent) {
            addNewContinent();
        }

        if (ae == v.btnSave) {
            addNewCountry();

        }
    }

    private void enabledComponents() {

        if (v.rbAddContinent.isSelected()) {
            v.txtNewContinent.setEnabled(true);
            v.btnAddContinent.setEnabled(true);

            v.cbCountries.setSelectedIndex(0);
            v.btnSave.setEnabled(false);
            v.txtCountry.setEnabled(false);
            v.txtResidents.setEnabled(false);
            v.txtSurface.setEnabled(false);

            v.txtCountry.setText("");
            v.txtResidents.setText("");
            v.txtSurface.setText("");
            v.txtContinent.setText("");

        } else {

            v.txtNewContinent.setEnabled(false);
            v.btnAddContinent.setEnabled(false);

            v.btnSave.setEnabled(true);
            v.txtCountry.setEnabled(true);
            v.txtResidents.setEnabled(true);
            v.txtSurface.setEnabled(true);
        }
    }

    private void addNewContinent() {
        if (v.txtNewContinent.getText().isEmpty()) {
            Messages.setPlainMessage(".:ERROR:.", "Rellene el campo con el continente que desea registrar.");
        } else if (!continentDAO.existContient(v.txtNewContinent.getText())) {
            Messages.setPlainMessage(".:ERROR:.", "El continente introducido no existe.");

        } else if (!continentDAO.isContinentRegistered(v.txtNewContinent.getText())) {
            Messages.setPlainMessage(".:ERROR:.", "El Continente ya está registrado.");
        } else {
            continentDAO.insertNewContinent(v.txtNewContinent.getText());
            v.txtNewContinent.setText("");
        }
        UIUtils.fillContinentComboBox(v.cbCountries, continentDAO.getContinents());

    }

    private void addNewCountry() {
        if (v.cbCountries.getSelectedIndex() == 0) {
            Messages.setPlainMessage(".:ERROR:.", "Seleccione un continente.");
        } else if (!UIUtils.hasDataInFields(v.dataPanel)) {
            Messages.setPlainMessage(".:ERROR:.", "Rellene los campos vacios.");
        } else if (!countryDAO.isCountryRegistered(v.txtCountry.getText())) {
            Messages.setPlainMessage(".:ERROR:.", "El país ya está registrado.");
        } else {
            countryDAO.insertNewCountry(v.txtCountry.getText(), v.txtResidents.getText(),
                    v.cbCountries, v.txtSurface.getText());
            UIUtils.resetFields(v.dataPanel);
            v.cbCountries.setSelectedIndex(0);
            v.txtContinent.setText("");

        }
    }

    private void addKeyListeners() {
        KeyListener listener = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                UIUtils.setTextFields(e, v.txtNewContinent);
                UIUtils.setTextFields(e, v.txtCountry);
                UIUtils.setDigitTextFields(e, v.txtResidents);
                UIUtils.setDigitTextFields(e, v.txtSurface);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                JTextField txt = (JTextField) e.getSource();
                txt.setText(txt.getText().toUpperCase());
            }
        };
        v.txtNewContinent.addKeyListener(listener);
        v.txtCountry.addKeyListener(listener);
        v.txtResidents.addKeyListener(listener);
        v.txtSurface.addKeyListener(listener);

    }

}
