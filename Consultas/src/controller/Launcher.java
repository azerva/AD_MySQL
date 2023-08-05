package controller;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.DataBase;
import utils.UIUtils;
import view.ConsultsPanel;
import view.DeletePanel;
import view.HomePanel;
import view.LauncherView;
import view.NewCountryView;

/**
 * @author Alex
 */
public class Launcher implements ActionListener {

    private final LauncherView v;
    private DataBase db = new DataBase();

    public Launcher(LauncherView view) {
        this.v = view;

        v.setIconImage(getIconImage());
        
        db.connector();
        
        UIUtils.changePanel(v.cardPanel, new HomePanel());

        setup();
    }

    private Image getIconImage() {
        Image image = Toolkit.getDefaultToolkit().getImage(ClassLoader
                .getSystemResource("resources/world.png"));
        return image;
    }

    private void setup() {
        v.btnExit.addActionListener(this);
        v.btnAddCountry.addActionListener(this);
        v.btnDelete.addActionListener(this);
        v.btnConsults.addActionListener(this);
        v.btnHome.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ae = e.getSource();

        if (ae == v.btnExit) {
            System.exit(0);
        }
        if (ae == v.btnHome) {
            UIUtils.changePanel(v.cardPanel, new HomePanel());
        }
        if (ae == v.btnAddCountry) {
            UIUtils.changePanel(v.cardPanel, new NewCountryView());
        }
        if (ae == v.btnConsults) {
            UIUtils.changePanel(v.cardPanel, new ConsultsPanel());
        }
        if (ae == v.btnDelete) {
            UIUtils.changePanel(v.cardPanel, new DeletePanel());
        }

    }

}
