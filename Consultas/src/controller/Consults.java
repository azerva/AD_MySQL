package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import model.ContinentDAO;
import model.Country;
import model.CountryDAO;
import model.DataBase;
import utils.Messages;
import utils.ModelTable;
import utils.UIUtils;
import view.ConsultsPanel;

/**
 *
 * @author Alex
 */
public class Consults implements ActionListener {

    private final ConsultsPanel v;
    private DataBase db = new DataBase();
    private CountryDAO countryDAO = new CountryDAO();
    private ContinentDAO continentDAO = new ContinentDAO();

    private String[] consultList = {
        "Lista de paises por continente.",
        "Cálculo de habitantes del continente según los paises registrados.",
        "Cálculo de superficie del continenete según los paises registrados."
    };

    public Consults(ConsultsPanel view) {
        this.v = view;

        setup();
    }

    private void setup() {
        v.btnConsult.addActionListener(this);

        fillComboBoxes();
        initTable();

        UIUtils.fillContinentComboBox(v.cbContinents, continentDAO.getContinents());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();

        if (ae == v.btnConsult) {
            if (v.cbContinents.getSelectedIndex() == 0) {
                Messages.setInfoMessage("::ERROR::", "Seleccione un continente para realizar la consulta.");
            } else {
                setConsult();
            }
        }

    }

    private void fillComboBoxes() {
        List<String> consults = new ArrayList();
        for (int i = 0; i < consultList.length; i++) {
            consults.add(consultList[i]);
        }

        v.cbConsults.removeAllItems();
        v.cbConsults.addItem("Seleccione elemento");

        for (String string : consults) {
            v.cbConsults.addItem(string);
        }

    }

    private void setConsult() {
        switch (v.cbConsults.getSelectedIndex()) {
            case 0:
                Messages.setInfoMessage("::ERROR::", "Seleccione el tipo de consulta que desea realizar.");
                break;
            case 1:
                ModelTable.consultTable(v.table, header(), getCountriesByContinent());
                break;
            case 2:
                ModelTable.consultTable(v.table, header(), getCountriesByContinent());
                countryDAO.getTotalPopulationByContient(v.cbContinents, v.lblQuestion, v.lblAnswer);
                break;
            case 3:
                ModelTable.consultTable(v.table, header(), getCountriesByContinent());
                countryDAO.getTotalSurfaceByContient(v.cbContinents, v.lblQuestion, v.lblAnswer);
                break;
            default:
                break;
        }
    }

    private void initTable() {
        ModelTable.consultTable(v.table, header(), null);
    }

    private Object[] header() {
        return new Object[]{"Nº Continente", "País", "Nº residentes", "Superficie km2"};
    }

    private Object[][] getCountriesByContinent() {

        List<Country> results = countryDAO.getCountriesByContinent(v.cbContinents, v.lblQuestion, v.lblAnswer);

        int numDocs = results.size();
        Object[][] rows = new Object[numDocs][4];

        int index = 0;
        for (int i = 0; i < results.size(); i++) {
            rows[index][0] = results.get(i).getNum_continent();
            rows[index][1] = results.get(i).getCountry();
            rows[index][2] = results.get(i).getNum_residents();
            rows[index][3] = results.get(i).getSurface_km2();
            index++;
        }
        return rows;
    }

}
