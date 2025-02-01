package InterfaceServeur;

import Restaurant.*;

import javax.swing.*;
import java.awt.Color;

public class EnregistrePaiment extends javax.swing.JPanel {

    private JTextField commandeNumberField;
    private JButton enregistrerButton;
    private JButton retourButton;
    private JLabel commandeLabel;
    private JLabel titreLabel;
    private JComboBox<String> methodeComboBox; // Nouveau JComboBox pour la méthode de paiement

    public EnregistrePaiment() {
        initComponents();
    }

    private void initComponents() {
        commandeNumberField = new JTextField();
        enregistrerButton = new JButton("Enregistrer Paiement");
        retourButton = new JButton("Retour");
        commandeLabel = new JLabel("Numéro de commande :");
        titreLabel = new JLabel("Enregistrer un Paiement");

        // Initialisation du JComboBox pour la méthode de paiement
        methodeComboBox = new JComboBox<>(new String[] { "Espèces", "Carte Bancaire" });
        methodeComboBox.setBounds(200, 150, 150, 30);  // Position du JComboBox

        // Personnalisation des composants
        titreLabel.setFont(new java.awt.Font("Andalus", 1, 30)); // Police du titre
        titreLabel.setForeground(new java.awt.Color(102, 102, 102)); // Couleur du titre
        commandeLabel.setFont(new java.awt.Font("Arial", 0, 16)); // Police pour le label "Numéro de commande"
        enregistrerButton.setBackground(new java.awt.Color(153, 204, 255)); // Couleur du bouton
        retourButton.setBackground(new java.awt.Color(255, 102, 102)); // Couleur du bouton Retour

        // Définir le layout et la taille du panneau
        setPreferredSize(new java.awt.Dimension(700, 500));
        setLayout(null); // Utilisation d'un layout absolu pour un contrôle précis

        // Enlever la couleur de fond du panneau
        setBackground(new Color(19, 177, 177, 255));

        // Définir les positions des composants
        titreLabel.setBounds(31, 20, 600, 40);
        commandeLabel.setBounds(31, 100, 200, 30);
        commandeNumberField.setBounds(200, 100, 150, 30);
        enregistrerButton.setBounds(200, 200, 200, 40);  // Modifier la position du bouton Enregistrer
        retourButton.setBounds(40, 390, 130, 50);

        // Ajouter un écouteur d'événements au bouton Enregistrer
        enregistrerButton.addActionListener(evt -> enregistrerPaiementAction());

        // Ajouter un écouteur d'événements au bouton Retour
        retourButton.addActionListener(evt -> retourGestionPaiement());

        // Ajouter les composants au panneau
        add(titreLabel);
        add(commandeLabel);
        add(commandeNumberField);
        add(methodeComboBox); // Ajouter le JComboBox au panneau
        add(enregistrerButton);
        add(retourButton);
    }

    private void enregistrerPaiementAction() {
        try {
            int numeroCommande = Integer.parseInt(commandeNumberField.getText());
            String methode = (String) methodeComboBox.getSelectedItem(); // Récupérer la méthode sélectionnée

            // Appel à la méthode enregistrerPaiement de la classe Serveur
            Serveur serveur = new Serveur();
            boolean paiementReussi = serveur.enregistrerPaiement(numeroCommande, methode);  // Passer la méthode en paramètre

            // Afficher le message dans une boîte de dialogue
            if (paiementReussi) {
                JOptionPane.showMessageDialog(this, "Paiement enregistré avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Échec de l'enregistrement du paiement.", "Échec", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un numéro de commande valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void retourGestionPaiement() {
        // Créer une nouvelle fenêtre JFrame pour GestionPaiement
        JFrame gestionPaiementFrame = new JFrame("Restaurant ~MoHa~");
        gestionPaiementFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gestionPaiementFrame.setResizable(false);

        // Ajouter une instance de GestionPaiement au JFrame
        gestionPaiementFrame.getContentPane().add(new GestionPaiment());

        // Configurer la taille et l'emplacement de la fenêtre
        gestionPaiementFrame.setSize(700, 500);
        gestionPaiementFrame.setLocationRelativeTo(null);
        gestionPaiementFrame.setVisible(true);

        // Fermer la fenêtre actuelle
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        currentFrame.dispose();
    }
}
