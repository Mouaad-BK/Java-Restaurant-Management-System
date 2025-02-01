package InterfaceGerant;
import Restaurant.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AfficherMenu extends javax.swing.JPanel {

    public AfficherMenu() {
        initComponents();
        afficherMenuTable(); // Charger et afficher le menu dès l'initialisation
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonRetour = new javax.swing.JButton();
        jScrollPaneTable = new javax.swing.JScrollPane();
        jTableMenu = new javax.swing.JTable(); // Initialisation de la table

        setPreferredSize(new java.awt.Dimension(700, 500));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Andalus", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Menu");

        jButtonRetour.setBackground(new java.awt.Color(255, 102, 102));
        jButtonRetour.setText("Retour");
        jButtonRetour.addActionListener(evt -> jButtonRetourActionPerformed(evt));

        // Configuration de la JTable (vide initialement, rempli par afficherMenuTable())
        jTableMenu.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"N° Plat", "Nom", "Prix (DH)", "Type"}
        ));
        jScrollPaneTable.setViewportView(jTableMenu);

        // Layout du panneau principal
        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addComponent(jButtonRetour, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(156, 156, 156)
                                                .addComponent(jLabel1))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(jScrollPaneTable, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

    // Charger et afficher le menu dans la JTable
    private void afficherMenuTable() {
        Gerant gerant = new Gerant(); // Instanciation de la classe Gerant
        JTable tableMenu = gerant.affichererMenu(); // Récupération de la JTable

        // Récupérer le modèle de table et l'appliquer à jTableMenu
        jTableMenu.setModel(tableMenu.getModel());
    }

    private void jButtonRetourActionPerformed(java.awt.event.ActionEvent evt) {
        JFrame Returnfram = new JFrame("Restaurant ~MoHa~"); // Titre changé ici
        Returnfram.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Allow the frame to be closed
        Returnfram.setResizable(false);

        // Créer une instance du panneau GestionMenu
        GestionMenu Panel = new GestionMenu();

        // Ajouter le panneau à la fenêtre JFrame
        Returnfram.getContentPane().add(Panel);

        // Définir la taille de la fenêtre
        Returnfram.setSize(700, 500);

        // Empêcher la fenêtre d'être redimensionnée
        Returnfram.setResizable(false); // Empêcher la minimisation et maximisation

        // Centrer la fenêtre sur l'écran
        Returnfram.setLocationRelativeTo(null);

        // Rendre la fenêtre visible
        Returnfram.setVisible(true);

        // Fermer la fenêtre actuelle
        JFrame serveurFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        serveurFrame.dispose();
    }

    // Variables déclaration
    private javax.swing.JButton jButtonRetour;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPaneTable;
    private javax.swing.JTable jTableMenu;
    // Fin de la déclaration des variables
}

