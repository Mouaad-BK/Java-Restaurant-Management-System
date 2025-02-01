package InterfaceGerant;
import Restaurant.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AjouterTable extends javax.swing.JPanel {

    private JComboBox<String> etatComboBox;
    private JButton ajouterButton;
    private JButton retourButton;
    private JLabel titreLabel;
    private JLabel etatLabel;

    public AjouterTable() {
        initComponents();
    }

    private void initComponents() {
        // Initialisation des composants
        etatComboBox = new JComboBox<>(new String[]{"Libre", "Occupée", "Réservée"});
        ajouterButton = new JButton("Ajouter Table");
        retourButton = new JButton("Retour");
        titreLabel = new JLabel("Ajouter une Table");
        etatLabel = new JLabel("Choisissez l'état:");

        // Personnalisation des composants
        titreLabel.setFont(new java.awt.Font("Andalus", java.awt.Font.BOLD, 30));
        titreLabel.setForeground(new java.awt.Color(102, 102, 102));
        etatLabel.setFont(new java.awt.Font("Arial", 0, 16));
        ajouterButton.setBackground(new java.awt.Color(153, 204, 255));
        retourButton.setBackground(new java.awt.Color(182, 89, 89));

        // Définir le fond du panneau
        setBackground(new java.awt.Color(0, 153, 153)); // Fond turquoise

        // Layout avec positionnement absolu
        setLayout(null);
        setPreferredSize(new java.awt.Dimension(700, 500));

        // Positionnement des composants
        titreLabel.setBounds(31, 20, 400, 40);
        etatLabel.setBounds(31, 100, 200, 30);
        etatComboBox.setBounds(200, 100, 150, 30);
        ajouterButton.setBounds(200, 150, 150, 40);
        retourButton.setBounds(10, 400, 90, 45);

        // Écouteurs d'événements
        ajouterButton.addActionListener(this::ajouterTable);
        retourButton.addActionListener(this::retourButtonActionPerformed);

        // Ajouter les composants
        add(titreLabel);
        add(etatLabel);
        add(etatComboBox);
        add(ajouterButton);
        add(retourButton);
    }

    // Méthode pour ajouter la table
    private void ajouterTable(ActionEvent evt) {
        String etat = (String) etatComboBox.getSelectedItem();
        Gerant gerant = new Gerant();

        boolean result = gerant.ajouterTable(etat);
        String message = result ? "Table ajoutée avec succès !" : "Échec de l'ajout de la table.";
        JOptionPane.showMessageDialog(this, message);
    }

    // Méthode pour gérer le retour au menu précédent
    private void retourButtonActionPerformed(ActionEvent evt) {
        JFrame returnFrame = new JFrame("Restaurant ~MoHa~");
        returnFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GestionTables gestionTablesPanel = new GestionTables();
        returnFrame.getContentPane().add(gestionTablesPanel);
        returnFrame.setSize(700, 500);
        returnFrame.setLocationRelativeTo(null);
        returnFrame.setVisible(true);
        returnFrame.setResizable(false);
        ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
    }
}

