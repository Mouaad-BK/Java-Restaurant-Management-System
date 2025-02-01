package InterfaceServeur;
import Restaurant.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class CreerCommande extends javax.swing.JPanel {

    private JTextField commandeNumberField;
    private JTextField tableNumberField;
    private JTextField clientNameField;
    private JTextField platNumberField;
    private JButton creerButton;
    private JLabel commandeLabel;
    private JLabel tableLabel;
    private JLabel clientLabel;
    private JLabel platLabel;
    private JLabel titleLabel;
    private JButton retourButton;
    private JTable platTable; // Tableau pour afficher les plats

    public CreerCommande() {
        initComponents();
        setBackground(new java.awt.Color(0, 153, 153)); // Appliquer la couleur de fond ici
    }

    private void initComponents() {
        // Initialisation des composants
        commandeNumberField = new JTextField();
        tableNumberField = new JTextField();
        clientNameField = new JTextField();
        platNumberField = new JTextField();
        creerButton = new JButton("Créer Commande");
        commandeLabel = new JLabel("Numéro de commande :");
        tableLabel = new JLabel("Numéro de table :");
        clientLabel = new JLabel("Nom du client :");
        platLabel = new JLabel("Numéro du plat :");
        titleLabel = new JLabel("Créer une Commande");
        retourButton = new JButton("Retour");  // Initialiser le bouton de retour

        // Personnalisation des composants
        titleLabel.setFont(new java.awt.Font("Andalus", java.awt.Font.BOLD, 30));
        titleLabel.setForeground(new java.awt.Color(102, 102, 102)); // Couleur du titre
        commandeLabel.setFont(new java.awt.Font("Arial", 0, 16));
        tableLabel.setFont(new java.awt.Font("Arial", 0, 16));
        clientLabel.setFont(new java.awt.Font("Arial", 0, 16));
        platLabel.setFont(new java.awt.Font("Arial", 0, 16));
        creerButton.setBackground(new java.awt.Color(153, 204, 255));
        retourButton.setBackground(new java.awt.Color(186, 82, 82)); // Couleur du bouton de retour
        retourButton.setFont(new java.awt.Font("Andalus", 0, 15));

        // Définir le layout et la taille du panneau
        setPreferredSize(new java.awt.Dimension(700, 500));
        setLayout(null);  // Utilisation d'un layout absolu

        // Définir les positions des composants
        titleLabel.setBounds(31, 20, 400, 40);  // Position du titre en haut
        commandeLabel.setBounds(31, 100, 200, 30);
        commandeNumberField.setBounds(200, 100, 150, 30);
        tableLabel.setBounds(31, 150, 200, 30);
        tableNumberField.setBounds(200, 150, 150, 30);
        clientLabel.setBounds(31, 200, 200, 30);
        clientNameField.setBounds(200, 200, 150, 30);
        platLabel.setBounds(31, 250, 200, 30);
        platNumberField.setBounds(200, 250, 150, 30);
        creerButton.setBounds(200, 300, 150, 40);
        retourButton.setBounds(10, 400, 90, 45);  // Positionner le bouton de retour en haut à gauche

        // Création du tableau pour afficher les plats
        platTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(platTable);
        scrollPane.setBounds(400, 100, 250, 200);  // Position du tableau à droite
        add(scrollPane);

        // Ajouter un écouteur d'événements au bouton de création
        creerButton.addActionListener(evt -> creerCommande());

        // Ajouter un écouteur d'événements au bouton de retour
        retourButton.addActionListener(evt -> retourButtonActionPerformed(evt));

        // Ajouter les composants au panneau
        add(titleLabel);  // Ajouter le titre
        add(commandeLabel);
        add(commandeNumberField);
        add(tableLabel);
        add(tableNumberField);
        add(clientLabel);
        add(clientNameField);
        add(platLabel);
        add(platNumberField);
        add(creerButton);
        add(retourButton);  // Ajouter le bouton de retour

        // Charger les plats dans le tableau
        loadPlats();
    }

    private void loadPlats() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Numéro", "Nom du Plat"}, 0);
        try (Connection conn = Connecter.getConnection()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Erreur de connexion à la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;  // Si la connexion est null, arrêter la méthode
            }

            String query = "SELECT idPlat, nomPlat FROM plat";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int idPlat = rs.getInt("idPlat");
                String nomPlat = rs.getString("nomPlat");
                model.addRow(new Object[]{idPlat, nomPlat});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur de chargement des plats.", "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();  // Afficher les détails de l'erreur dans la console
        }
        platTable.setModel(model);
    }

    private void creerCommande() {
        try {
            // Récupérer les valeurs des champs
            int numeroCommande = Integer.parseInt(commandeNumberField.getText());
            int numeroTable = Integer.parseInt(tableNumberField.getText());
            String nomClient = clientNameField.getText();
            int numeroPlat = Integer.parseInt(platNumberField.getText());

            // Appel à la méthode `creerCommande` pour créer la commande
            Serveur serveur = new Serveur(); // Créer une instance de Serveur (si nécessaire)
            boolean result = serveur.creerCommande(numeroCommande, numeroTable, nomClient, numeroPlat);

            if (result) {
                JOptionPane.showMessageDialog(this, "Commande " + numeroCommande + " créée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de la création de la commande.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer des numéros valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void retourButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JFrame Returnfram = new JFrame("Restaurant ~MoHa~");
        Returnfram.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Allow the frame to be closed
        Returnfram.setResizable(false);

        // Create an instance of the GestionCommandes panel
        GestionCommandes Panel = new GestionCommandes();

        // Add the GestionCommandes panel to the JFrame
        Returnfram.getContentPane().add(Panel);

        // Set the size of the JFrame
        Returnfram.setSize(700, 500);

        // Center the frame on the screen
        Returnfram.setLocationRelativeTo(null);

        // Set the JFrame to be visible
        Returnfram.setVisible(true);


        JFrame Reservations = (JFrame) SwingUtilities.getWindowAncestor(this);
        Reservations.dispose();
    }
}
