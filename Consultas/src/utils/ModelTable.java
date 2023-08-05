package utils;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Alex
 */
public class ModelTable extends DefaultTableModel {

    public static void consultTable(JTable table, Object[] header, Object[][] data) {
        DefaultTableModel dtm = new DefaultTableModel(
                data,
                header
        ) {
            @Override
            public boolean isCellEditable(int row, int column
            ) {
                return false;
            }
        };

        table.setModel(dtm);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setDefaultRenderer(new HeaderTables());
        table.setTableHeader(tableHeader);
        table.setRowHeight(30);

    }

    public static void deleteCountryTable(JTable table, Object[] header, Object[][] data) {
        DefaultTableModel dtm = new DefaultTableModel(
                data,
                header
        ) {
            @Override
            public boolean isCellEditable(int row, int column
            ) {
                return false;
            }
        };

        table.setModel(dtm);

        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        table.getColumnModel().getColumn(4).setPreferredWidth(30);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setDefaultRenderer(new HeaderTables());
        table.setTableHeader(tableHeader);
        table.setRowHeight(30);

    }

}
