package utils;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Alex
 */
public class TableRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        if (value instanceof JButton) {
            JButton btn = (JButton) value;
            return btn;
        }
        
        setHorizontalAlignment(SwingConstants.CENTER);

        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

    }
}
