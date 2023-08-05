
package utils;

import javax.swing.JOptionPane;

public class Messages {

    public static void setPlainMessage(String tittle, String message) {
        JOptionPane.showMessageDialog(null, message, tittle, JOptionPane.PLAIN_MESSAGE);

    }
    public static int setWarningMessage(String tittle, String message) {
        return JOptionPane.showConfirmDialog(null, message, tittle,JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

    }
    public static void setInfoMessage(String tittle, String message) {
         JOptionPane.showMessageDialog(null, message, tittle, JOptionPane.INFORMATION_MESSAGE);

    }
    
    public static void alertMessage(String tittle, String message){
        JOptionPane.showMessageDialog(null, message, tittle, JOptionPane.ERROR_MESSAGE);
    }
    
}
