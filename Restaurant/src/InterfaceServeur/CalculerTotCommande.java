package InterfaceServeur;

import Restaurant.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class CalculerTotCommande extends javax.swing.JPanel {

    private JTextField commandeNumberField;
    private JButton calculerButton;
    private JButton retourButton;
    private JLabel commandeLabel;
    private JLabel titreLabel;
    private JLabel totalLabel;
    private JTable commandeTable; // Tableau pour afficher les commandes

    public CalculerTotCommande() {
        initComponents();
        loadCommandes(); // Charger les commandes dès l'initialisation
    }

    private void initComponents() {
        commandeNumberField = new JTextField();
        calculerButton = new JButton("Calculer Total");
        retourButton = new JButton("Retour");
        commandeLabel = new JLabel("Numéro de commande :");
        titreLabel = new JLabel("Calculer le Total de la Commande");
        totalLabel = new JLabel("Total : ");
        commandeTable = new JTable(); // Initialisation du tableau des commandes

        // Personnalisation des composants
        titreLabel.setFont(new Font("Andalus", Font.BOLD, 30));
        titreLabel.setForeground(new Color(102, 102, 102));
        commandeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        totalLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        calculerButton.setBackground(new Color(153, 204, 255));
        retourButton.setBackground(new Color(255, 102, 102));

        // Définir le layout et la taille du panneau
        setPreferredSize(new Dimension(700, 500));
        setLayout(null);
        setBackground(new Color(19, 177, 177, 255));

        // Définir les positions des composants
        titreLabel.setBounds(31, 20, 600, 40);
        commandeLabel.setBounds(31, 100, 200, 30);
        commandeNumberField.setBounds(200, 100, 150, 30);
        calculerButton.setBounds(200, 150, 150, 40);
        totalLabel.setBounds(200, 200, 200, 30);
        retourButton.setBounds(40, 390, 130, 50);

        // Position et ajout du tableau des commandes
        JScrollPane scrollPane = new JScrollPane(commandeTable);
        scrollPane.setBounds(400, 100, 250, 300);
        add(scrollPane);

        // Ajouter des écouteurs d'événements
        calculerButton.addActionListener(evt -> calculerTotalCommande());
        retourButton.addActionListener(evt -> retourGestionPaiement());

        // Ajouter les composants au panneau
        add(titreLabel);
        add(commandeLabel);
        add(commandeNumberField);
        add(calculerButton);
        add(totalLabel);
        add(retourButton);
    }

    private void loadCommandes() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Commande", "Nom Client", "Statut"}, 0);

        try (Connection conn = Connecter.getConnection()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Erreur de connexion à la base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "SELECT idCommande, nomClient, statut FROM Commande";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int idCommande = rs.getInt("idCommande");
                String nomClient = rs.getString("nomClient");
                String statut = rs.getString("statut");
                model.addRow(new Object[]{idCommande, nomClient, statut});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des commandes.", "Erreur", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        commandeTable.setModel(model);
    }

    private void calculerTotalCommande() {
        try {
            int numeroCommande = Integer.parseInt(commandeNumberField.getText());

            Serveur serveur = new Serveur();
            double total = serveur.calculerSommeCommande(numeroCommande);

            totalLabel.setText("Total : " + total + " DH");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un numéro de commande valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void retourGestionPaiement() {
        JFrame gestionPaiementFrame = new JFrame("Restaurant ~MoHa~");
        gestionPaiementFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gestionPaiementFrame.setResizable(false);

        gestionPaiementFrame.getContentPane().add(new GestionPaiment());
        gestionPaiementFrame.setSize(700, 500);
        gestionPaiementFrame.setLocationRelativeTo(null);
        gestionPaiementFrame.setVisible(true);

        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        currentFrame.dispose();
    }
}
