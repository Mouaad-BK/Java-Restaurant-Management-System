package InterfaceServeur;

import Restaurant.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VerifierTable extends JPanel {

    private JLabel lblTitle;
    private JLabel lblIdTable;
    private JLabel lblEtatTable;
    private JTextField txtIdTable;
    private JButton btnVerifier;
    private JButton btnRetour;

    public VerifierTable() {
        initComponents();
        setBackground(new Color(0, 153, 153)); // Appliquer la couleur de fond
    }

    private void initComponents() {
        // Initialisation des composants
        lblTitle = new JLabel("Vérifier l'État d'une Table");
        lblIdTable = new JLabel("ID de la Table :");
        lblEtatTable = new JLabel();
        txtIdTable = new JTextField();
        btnVerifier = new JButton("Vérifier");
        btnRetour = new JButton("Retour");

        // Personnalisation des composants
        lblTitle.setFont(new Font("Andalus", Font.BOLD, 30));
        lblTitle.setForeground(new Color(102, 102, 102)); // Couleur du titre
        lblIdTable.setFont(new Font("Arial", Font.PLAIN, 16));
        lblEtatTable.setFont(new Font("Arial", Font.PLAIN, 16));
        btnVerifier.setBackground(new Color(153, 204, 255));
        btnRetour.setBackground(new Color(182, 89, 89));
        btnRetour.setFont(new Font("Andalus", Font.PLAIN, 15));

        // Définir le layout et la taille du panneau
        setPreferredSize(new Dimension(700, 500));
        setLayout(null); // Utilisation d'un layout absolu

        // Définir les positions des composants
        lblTitle.setBounds(31, 20, 400, 40); // Titre en haut
        lblIdTable.setBounds(31, 100, 200, 30);
        txtIdTable.setBounds(200, 100, 150, 30);
        btnVerifier.setBounds(200, 150, 150, 40);
        lblEtatTable.setBounds(31, 220, 400, 30);
        btnRetour.setBounds(10, 400, 90, 45); // Bouton de retour en bas à gauche

        // Ajouter des écouteurs d'événements
        btnVerifier.addActionListener(evt -> verifierTable());
        btnRetour.addActionListener(evt -> retourButtonActionPerformed());

        // Ajouter les composants au panneau
        add(lblTitle);
        add(lblIdTable);
        add(txtIdTable);
        add(btnVerifier);
        add(lblEtatTable);
        add(btnRetour);
    }

    private void verifierTable() {
        try {
            int idTable = Integer.parseInt(txtIdTable.getText().trim());

            if (idTable <= 0) {
                lblEtatTable.setText("ID invalide. Veuillez entrer un numéro valide.");
                lblEtatTable.setForeground(Color.RED);
                return;
            }

            // Créer une instance de Serveur et appeler la méthode de vérification
            Serveur serveur = new Serveur(); // Assurez-vous que Serveur est correctement instancié
            boolean estLibre = serveur.verifierDisponibiliteTable(idTable); // Appeler la méthode du Serveur

            // Afficher le résultat
            if (estLibre) {
                lblEtatTable.setText("La table " + idTable + " est libre.");
                lblEtatTable.setForeground(new Color(34, 139, 34)); // Vert pour état libre
            } else {
                lblEtatTable.setText("La table " + idTable + " est réservée.");
                lblEtatTable.setForeground(Color.RED); // Rouge pour état occupé

                // Afficher une boîte de dialogue pour libérer la table
                int response = JOptionPane.showConfirmDialog(
                        this,
                        "La table " + idTable + " est réservée. Voulez-vous la libérer ?",
                        "Table occupée",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (response == JOptionPane.YES_OPTION) {
                    boolean liberee = serveur.libererTable(idTable);
                    if (liberee) {
                        lblEtatTable.setText("La table " + idTable + " a été libérée.");
                        lblEtatTable.setForeground(new Color(34, 139, 34)); // Vert pour état libre
                    } else {
                        lblEtatTable.setText("Erreur : Impossible de libérer la table.");
                        lblEtatTable.setForeground(Color.RED);
                    }
                }
            }
        } catch (NumberFormatException e) {
            lblEtatTable.setText("Veuillez entrer un ID valide.");
            lblEtatTable.setForeground(Color.RED);
        }
    }

    private void retourButtonActionPerformed() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (parentFrame != null) {
            parentFrame.getContentPane().removeAll();
            parentFrame.getContentPane().add(new GestionReservation()); // Remplacer par le panneau précédent
            parentFrame.revalidate();
            parentFrame.repaint();
        }
    }
}
