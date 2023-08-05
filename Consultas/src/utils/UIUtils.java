package utils;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import model.Continent;
import model.Country;

/**
 *
 * @author Alex
 */
public class UIUtils {
    
    public static int selectRow;

    public static void changePanel(JPanel panelContainer, JPanel newPanel) {
        panelContainer.removeAll();
        panelContainer.add(newPanel);
        panelContainer.repaint();
        panelContainer.revalidate();
    }

    public static void resetFields(Container container) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            } else if (component instanceof JComboBox) {
                JComboBox box = (JComboBox) component;
                box.setSelectedIndex(0);
            }else if(component instanceof JRadioButton){
                JRadioButton rb = (JRadioButton) component;
                rb.setSelected(false);
            } else if (component instanceof Container) {
                resetFields((Container) component);
            }
        }

    }

    public static boolean hasDataInFields(Container container) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                String text = ((JTextField) component).getText();
                if (!text.isEmpty()) {
                    return true;
                }
            } else if (component instanceof Container) {
                if (hasDataInFields((Container) component)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void fillContinentComboBox(JComboBox box, List<Continent> getList) {
        List<Continent> list = getList;

        box.removeAllItems();
        box.addItem("Seleccione elemento");

        for (int i = 0; i < list.size(); i++) {
            box.addItem(list.get(i).getNombre_continent());
        }
    }
        
     public static void setDigitTextFields(KeyEvent e, JTextField field) {
        char c = e.getKeyChar();
        JTextField txt = (JTextField) e.getSource();
        if (txt == field) {
            if (!Character.isDigit(c) && c!= '.') {
                e.consume();
            }
        }
    }
     public static void setTextFields(KeyEvent e, JTextField field) {
        char c = e.getKeyChar();
        JTextField txt = (JTextField) e.getSource();
        if (txt == field) {
            if (!Character.isLetter(c)) {
                e.consume();
            }
        }
    }

}
