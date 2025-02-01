package InterfaceGerant;
import Restaurant.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SupprimerTable extends javax.swing.JPanel {

    private JTextField idTableField;
    private JButton supprimerButton;
    private JButton retourButton;
    private JLabel titreLabel;
    private JLabel idTableLabel;

    public SupprimerTable() {
        initComponents();
    }

    private void initComponents() {
        // Initialisation des composants
        idTableField = new JTextField();
        supprimerButton = new JButton("Supprimer Table");
        retourButton = new JButton("Retour");
        titreLabel = new JLabel("Supprimer une Table");
        idTableLabel = new JLabel("Entrez l'ID de la table :");

        // Personnalisation des composants
        titreLabel.setFont(new java.awt.Font("Andalus", java.awt.Font.BOLD, 30));
        titreLabel.setForeground(new java.awt.Color(102, 102, 102));
        idTableLabel.setFont(new java.awt.Font("Arial", 0, 16));
        supprimerButton.setBackground(new java.awt.Color(153, 204, 255));
        retourButton.setBackground(new java.awt.Color(182, 89, 89));

        // Définir le fond du panneau
        setBackground(new java.awt.Color(0, 153, 153));

        // Layout avec positionnement absolu
        setLayout(null);
        setPreferredSize(new java.awt.Dimension(700, 500));

        // Positionnement des composants
        titreLabel.setBounds(31, 20, 400, 40);
        idTableLabel.setBounds(31, 100, 200, 30);
        idTableField.setBounds(200, 100, 150, 30);
        supprimerButton.setBounds(200, 150, 150, 40);
        retourButton.setBounds(10, 400, 90, 45);

        // Écouteurs d'événements
        supprimerButton.addActionListener(this::supprimerTable);
        retourButton.addActionListener(this::retourButtonActionPerformed);

        // Ajouter les composants
        add(titreLabel);
        add(idTableLabel);
        add(idTableField);
        add(supprimerButton);
        add(retourButton);
    }

    // Méthode pour supprimer la table
    private void supprimerTable(ActionEvent evt) {
        try {
            int idTable = Integer.parseInt(idTableField.getText());
            Gerant gerant = new Gerant();
            boolean result = gerant.retirerTable(idTable);

            String message = result ? "Table supprimée avec succès !" : "Échec de la suppression de la table.";
            JOptionPane.showMessageDialog(this, message);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un ID valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Méthode pour gérer le retour au menu précédent
    private void retourButtonActionPerformed(ActionEvent evt) {
        JFrame returnFrame = new JFrame("Restaurant ~MoHa~");
        returnFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        returnFrame.setResizable(false);

        // Créer une instance du panneau de gestion des tables
        GestionTables gestionTablesPanel = new GestionTables();

        // Ajouter le panneau de gestion des tables au JFrame
        returnFrame.getContentPane().add(gestionTablesPanel);

        // Définir la taille du JFrame
        returnFrame.setSize(700, 500);
        returnFrame.setLocationRelativeTo(null);
        returnFrame.setVisible(true);

        // Fermer la fenêtre actuelle
        ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
    }
}

