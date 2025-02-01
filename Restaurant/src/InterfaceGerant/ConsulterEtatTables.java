// ConsulterEtatTables.java
package InterfaceGerant;

import Restaurant.Gerant;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsulterEtatTables extends javax.swing.JPanel {

    private Gerant gerant;

    public ConsulterEtatTables() {
        initComponents();
        gerant = new Gerant(); // Initialisation de l'objet Gerant
        afficherEtatTables(); // Charger et afficher l'état des tables dès l'initialisation
    }

    private void initComponents() {
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonRetour = new javax.swing.JButton();
        jScrollPaneTable = new javax.swing.JScrollPane();
        jTableEtatTables = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(700, 500));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Andalus", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("État des Tables");

        jButtonRetour.setBackground(new java.awt.Color(255, 102, 102));
        jButtonRetour.setText("Retour");
        jButtonRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jButtonRetourActionPerformed(e);
            }
        });

        // Initialisation de la JTable vide
        jTableEtatTables.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID Table", "État"}
        ));
        jScrollPaneTable.setViewportView(jTableEtatTables);

        // Layout du panneau principal
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jButtonRetour, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(156, 156, 156)
                                .addComponent(jLabel1)
                                .addContainerGap(31, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPaneTable, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jButtonRetour, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addComponent(jScrollPaneTable, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        // Layout du panneau principal
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    private void afficherEtatTables() {
        // Appeler la méthode consulterEtatTables de l'objet Gerant pour obtenir un JTable
        JTable table = gerant.consulterEtatTables(); // Récupérer un JTable avec les données

        // Mettre à jour le modèle de la JTable dans l'interface
        jTableEtatTables.setModel(table.getModel());

        // Vérification si les données sont correctement récupérées
        if (table.getRowCount() == 0) {
            System.out.println("Aucune table à afficher.");
        }
    }

    private void jButtonRetourActionPerformed(ActionEvent evt) {
        // Action pour le bouton retour
        JFrame returnFrame = new JFrame("Gestion des Tables");
        returnFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Ajouter le panneau Gestion des Tables
        GestionTables panel = new GestionTables();
        returnFrame.getContentPane().add(panel);

        returnFrame.setSize(700, 500);
        returnFrame.setLocationRelativeTo(null); // Centrer la fenêtre
        returnFrame.setVisible(true);

        // Fermer la fenêtre actuelle
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        currentFrame.dispose();
    }

    // Variables declaration
    private javax.swing.JButton jButtonRetour;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPaneTable;
    private javax.swing.JTable jTableEtatTables;
    // End of variables declaration
}
