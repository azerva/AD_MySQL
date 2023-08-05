package view;

import controller.Launcher;

/**
 *
 * @author Alex
 */
public class LauncherView extends javax.swing.JFrame {

    private final Launcher launcher;
    
    public LauncherView() {
        initComponents();
        setLocationRelativeTo(this);
        launcher = new Launcher(this);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OptionsPanel = new javax.swing.JPanel();
        iconImg = new javax.swing.JLabel();
        btnAddCountry = new javax.swing.JButton();
        btnConsults = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        MainPanel = new javax.swing.JPanel();
        topMainPanel = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        cardPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(224, 247, 250));
        setMinimumSize(new java.awt.Dimension(960, 540));
        setPreferredSize(new java.awt.Dimension(960, 600));
        setResizable(false);

        OptionsPanel.setBackground(new java.awt.Color(224, 247, 250));
        OptionsPanel.setPreferredSize(new java.awt.Dimension(200, 500));
        OptionsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 20));

        iconImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icon_world.png"))); // NOI18N
        iconImg.setPreferredSize(new java.awt.Dimension(300, 175));
        OptionsPanel.add(iconImg);

        btnAddCountry.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnAddCountry.setForeground(new java.awt.Color(0, 0, 0));
        btnAddCountry.setText("New Country");
        btnAddCountry.setBorder(null);
        btnAddCountry.setBorderPainted(false);
        btnAddCountry.setContentAreaFilled(false);
        btnAddCountry.setFocusPainted(false);
        btnAddCountry.setPreferredSize(new java.awt.Dimension(300, 50));
        OptionsPanel.add(btnAddCountry);

        btnConsults.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnConsults.setForeground(new java.awt.Color(0, 0, 0));
        btnConsults.setText("Consults");
        btnConsults.setBorder(null);
        btnConsults.setBorderPainted(false);
        btnConsults.setContentAreaFilled(false);
        btnConsults.setFocusPainted(false);
        btnConsults.setPreferredSize(new java.awt.Dimension(300, 50));
        OptionsPanel.add(btnConsults);

        btnDelete.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(0, 0, 0));
        btnDelete.setText("Delete Country");
        btnDelete.setBorder(null);
        btnDelete.setBorderPainted(false);
        btnDelete.setContentAreaFilled(false);
        btnDelete.setFocusPainted(false);
        btnDelete.setPreferredSize(new java.awt.Dimension(300, 50));
        OptionsPanel.add(btnDelete);

        btnExit.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnExit.setForeground(new java.awt.Color(0, 0, 0));
        btnExit.setText("Exit");
        btnExit.setBorder(null);
        btnExit.setBorderPainted(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setFocusPainted(false);
        btnExit.setPreferredSize(new java.awt.Dimension(300, 50));
        OptionsPanel.add(btnExit);

        getContentPane().add(OptionsPanel, java.awt.BorderLayout.LINE_START);

        MainPanel.setLayout(new java.awt.BorderLayout());

        topMainPanel.setBackground(new java.awt.Color(224, 247, 250));
        topMainPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 5));

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/home.png"))); // NOI18N
        btnHome.setBorder(null);
        btnHome.setBorderPainted(false);
        btnHome.setContentAreaFilled(false);
        topMainPanel.add(btnHome);

        MainPanel.add(topMainPanel, java.awt.BorderLayout.PAGE_START);

        cardPanel.setBackground(new java.awt.Color(255, 255, 255));
        cardPanel.setLayout(new java.awt.CardLayout());
        MainPanel.add(cardPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(MainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel MainPanel;
    public javax.swing.JPanel OptionsPanel;
    public javax.swing.JButton btnAddCountry;
    public javax.swing.JButton btnConsults;
    public javax.swing.JButton btnDelete;
    public javax.swing.JButton btnExit;
    public javax.swing.JButton btnHome;
    public javax.swing.JPanel cardPanel;
    public javax.swing.JLabel iconImg;
    public javax.swing.JPanel topMainPanel;
    // End of variables declaration//GEN-END:variables


}
