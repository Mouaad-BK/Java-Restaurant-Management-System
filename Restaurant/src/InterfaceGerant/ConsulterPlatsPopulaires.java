package InterfaceGerant;

import Restaurant.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class ConsulterPlatsPopulaires extends javax.swing.JPanel {

    private JTable platsTable;
    private JScrollPane scrollPane;

    public ConsulterPlatsPopulaires() {
        initComponents();
        afficherPlatsPopulaires(); // Appel pour afficher les plats populaires
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(700, 500));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Andalus", 1, 30));
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("les 5 Plats Populaires");

        // Bouton Retour
        jButton8.setBackground(new java.awt.Color(255, 102, 102));
        jButton8.setText("ROUTEUR");
        jButton8.addActionListener(evt -> jButton8ActionPerformed(evt));

        // Table pour afficher les plats populaires
        platsTable = new JTable();
        scrollPane = new JScrollPane(platsTable);

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jButton8, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 579, Short.MAX_VALUE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(151, 151, 151)
                                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jButton8, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
    }

    private void afficherPlatsPopulaires() {
        Gerant gerant = new Gerant();
        JTable platsPopulaires = gerant.afficherPlatsLesPlusCommandes();

        if (platsPopulaires.getModel() instanceof DefaultTableModel) {
            platsTable.setModel((DefaultTableModel) platsPopulaires.getModel());
        }
    }

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
        JFrame returnFrame = new JFrame("Routeur Statistique");
        returnFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        StatistiqueGUI panel = new StatistiqueGUI();
        returnFrame.getContentPane().add(panel);
        returnFrame.setSize(700, 500);
        returnFrame.setLocationRelativeTo(null);
        returnFrame.setVisible(true);

        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        currentFrame.dispose();
    }

    // Variables declaration
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration
}
