package InterfaceGerant;
import Restaurant.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupprimerPlatMenu extends javax.swing.JPanel {

    private JTextField platIdField;
    private JButton supprimerButton;
    private JLabel platIdLabel;
    private JLabel titleLabel;
    private JButton retourButton; // Bouton retour

    public SupprimerPlatMenu() {
        initComponents();
        setBackground(new java.awt.Color(0, 153, 153)); // Couleur de fond
    }

    private void initComponents() {
        // Initialisation des composants
        platIdField = new JTextField();
        supprimerButton = new JButton("Supprimer Plat");
        platIdLabel = new JLabel("Numero du Plat :");
        titleLabel = new JLabel("Supprimer un Plat du Menu");
        retourButton = new JButton("Retour"); // Bouton retour

        // Personnalisation des composants
        titleLabel.setFont(new java.awt.Font("Andalus", java.awt.Font.BOLD, 30));
        titleLabel.setForeground(new java.awt.Color(102, 102, 102)); // Couleur du titre

        platIdLabel.setFont(new java.awt.Font("Arial", 0, 16));

        supprimerButton.setBackground(new java.awt.Color(153, 204, 255));
        retourButton.setBackground(new java.awt.Color(177, 84, 84)); // Couleur du bouton retour
        retourButton.setFont(new java.awt.Font("Andalus", 0, 15));

        // Définir le layout et la taille du panneau
        setPreferredSize(new java.awt.Dimension(700, 500));
        setLayout(null); // Layout absolu

        // Définir les positions des composants
        titleLabel.setBounds(31, 20, 400, 40); // Titre
        platIdLabel.setBounds(31, 100, 150, 30);
        platIdField.setBounds(200, 100, 150, 30);
        supprimerButton.setBounds(200, 150, 150, 40);
        retourButton.setBounds(10, 400, 90, 45); // Bouton retour

        // Ajouter des écouteurs d'événements
        supprimerButton.addActionListener(evt -> supprimerPlatAction());
        retourButton.addActionListener(evt -> retourButtonActionPerformed(evt));

        // Ajouter les composants au panneau
        add(titleLabel);
        add(platIdLabel);
        add(platIdField);
        add(supprimerButton);
        add(retourButton);
    }

    private void supprimerPlatAction() {
        try {
            int platId = Integer.parseInt(platIdField.getText());

            // Suppression du plat dans la base de données
            boolean resultat = retirerPlat(platId);

            if (resultat) {
                JOptionPane.showMessageDialog(this, "Plat avec ID " + platId + " supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erreur : échec de la suppression.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un ID valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void retourButtonActionPerformed(ActionEvent evt) {
        JFrame returnFrame = new JFrame("Restaurant ~MoHa~");
        returnFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fermer correctement la fenêtre
        returnFrame.setResizable(false);

        // Création de l'instance GestionMenu
        GestionMenu gestionMenuPanel = new GestionMenu();

        // Ajout du panneau GestionMenu au JFrame
        returnFrame.getContentPane().add(gestionMenuPanel);

        // Configuration du JFrame
        returnFrame.setSize(700, 500);
        returnFrame.setLocationRelativeTo(null);
        returnFrame.setVisible(true);

        // Fermeture de la fenêtre actuelle
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        currentFrame.dispose();
    }

    private boolean retirerPlat(int platId) {
        boolean suppressionReussie = false;
        Connection conn = Connecter.getConnection();
        if (conn != null) {
            String sql = "DELETE FROM plat WHERE idPlat = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, platId);
                suppressionReussie = stmt.executeUpdate() > 0;
            } catch (SQLException e) {
                System.err.println("Erreur lors de la suppression du plat : " + e.getMessage());
            } finally {
                Connecter.closeConnection(conn);
            }
        }
        return suppressionReussie;
    }
}

