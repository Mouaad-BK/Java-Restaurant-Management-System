package InterfaceServeur;
import Restaurant.*;

import javax.swing.*;
import java.awt.Color;
import javax.swing.SwingUtilities;

public class GenereRecu extends javax.swing.JPanel {

    private JTextField commandeNumberField;
    private JButton genererRecuButton;
    private JButton retourButton; // Nouveau bouton de retour
    private JLabel commandeLabel;
    private JLabel titreLabel;

    public GenereRecu() {
        initComponents();
    }

    private void initComponents() {
        commandeNumberField = new JTextField();
        genererRecuButton = new JButton("Générer Reçu");
        retourButton = new JButton("Retour"); // Initialisation du bouton retour
        commandeLabel = new JLabel("Numéro de commande :");
        titreLabel = new JLabel("Générer un Reçu");

        // Personnalisation des composants
        titreLabel.setFont(new java.awt.Font("Andalus", 1, 30)); // Police du titre
        titreLabel.setForeground(new java.awt.Color(102, 102, 102)); // Couleur du titre
        commandeLabel.setFont(new java.awt.Font("Arial", 0, 16)); // Police pour le label "Numéro de commande"
        genererRecuButton.setBackground(new java.awt.Color(153, 204, 255)); // Couleur du bouton
        retourButton.setBackground(new java.awt.Color(255, 102, 102)); // Couleur du bouton retour

        // Définir le layout et la taille du panneau
        setPreferredSize(new java.awt.Dimension(700, 500));
        setLayout(null); // Utilisation d'un layout absolu pour un contrôle précis

        // Enlever la couleur de fond du panneau
        setBackground(new Color(19, 177, 177, 255));

        // Définir les positions des composants
        titreLabel.setBounds(31, 20, 600, 40);
        commandeLabel.setBounds(31, 100, 200, 30);
        commandeNumberField.setBounds(200, 100, 150, 30);
        genererRecuButton.setBounds(200, 150, 200, 40);
        retourButton.setBounds(40, 390, 130, 50); // Position du bouton retour

        // Ajouter un écouteur d'événements au bouton Générer Reçu
        genererRecuButton.addActionListener(evt -> genererRecuAction());

        // Ajouter un écouteur d'événements au bouton Retour
        retourButton.addActionListener(evt -> retourAction());

        // Ajouter les composants au panneau
        add(titreLabel);
        add(commandeLabel);
        add(commandeNumberField);
        add(genererRecuButton);
        add(retourButton); // Ajouter le bouton retour au panneau
    }

    private void genererRecuAction() {
        try {
            int numeroCommande = Integer.parseInt(commandeNumberField.getText());

            // Appel à la méthode genererRecu de la classe Serveur
            Serveur serveur = new Serveur();
            serveur.genererRecu(numeroCommande); // Appel de la méthode pour générer le reçu

            // Afficher un message de succès dans une boîte de dialogue
            JOptionPane.showMessageDialog(this, "Le reçu a été généré avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            // Si le numéro de commande n'est pas valide
            JOptionPane.showMessageDialog(this, "Veuillez entrer un numéro de commande valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Méthode pour gérer le bouton Retour
    private void retourAction() {
        // Créer une nouvelle fenêtre JFrame pour la classe GestionPaiement
        JFrame gestionPaiementFrame = new JFrame("Restaurant ~MoHa~");
        gestionPaiementFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gestionPaiementFrame.setResizable(false);

        // Ajouter une instance de la classe GestionPaiement au JFrame
        gestionPaiementFrame.getContentPane().add(new GestionPaiment()); // Assurez-vous que la classe GestionPaiement existe

        // Configurer la taille et l'emplacement de la fenêtre
        gestionPaiementFrame.setSize(700, 500);
        gestionPaiementFrame.setLocationRelativeTo(null);
        gestionPaiementFrame.setVisible(true);

        // Fermer la fenêtre actuelle
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        currentFrame.dispose();
    }
}
