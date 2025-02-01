package InterfaceServeur;
import Restaurant.*;

import javax.swing.*;

public class ReseverTable extends JPanel {

    private JTextField txtIdTable;
    private JTextField txtNomClient;
    private JButton btnReserver;
    private JButton btnRetour;
    private JLabel lblTitle;
    private JLabel lblIdTable;
    private JLabel lblNomClient;

    public ReseverTable() {
        initComponents();
        setBackground(new java.awt.Color(0, 153, 153)); // Appliquer la couleur de fond ici
    }

    private void initComponents() {
        // Initialisation des composants
        lblTitle = new JLabel("Réserver une Table");
        lblIdTable = new JLabel("ID de la Table :");
        lblNomClient = new JLabel("Nom du Client :");
        txtIdTable = new JTextField();
        txtNomClient = new JTextField();
        btnReserver = new JButton("Réserver");
        btnRetour = new JButton("Retour");

        // Personnalisation des composants
        lblTitle.setFont(new java.awt.Font("Andalus", java.awt.Font.BOLD, 30));
        lblTitle.setForeground(new java.awt.Color(102, 102, 102)); // Couleur du titre
        lblIdTable.setFont(new java.awt.Font("Arial", 0, 16));
        lblNomClient.setFont(new java.awt.Font("Arial", 0, 16));
        btnReserver.setBackground(new java.awt.Color(153, 204, 255));
        btnRetour.setBackground(new java.awt.Color(182, 89, 89));
        btnRetour.setFont(new java.awt.Font("Andalus", 0, 15));

        // Définir le layout et la taille du panneau
        setPreferredSize(new java.awt.Dimension(700, 500));
        setLayout(null); // Utilisation d'un layout absolu

        // Définir les positions des composants
        lblTitle.setBounds(31, 20, 400, 40);  // Position du titre en haut
        lblIdTable.setBounds(31, 100, 200, 30);
        txtIdTable.setBounds(200, 100, 150, 30);
        lblNomClient.setBounds(31, 150, 200, 30);
        txtNomClient.setBounds(200, 150, 150, 30);
        btnReserver.setBounds(200, 200, 150, 40);
        btnRetour.setBounds(10, 400, 90, 45);  // Positionner le bouton de retour en bas à gauche

        // Ajouter des écouteurs d'événements
        btnReserver.addActionListener(evt -> reserverTable());
        btnRetour.addActionListener(evt -> retourButtonActionPerformed());

        // Ajouter les composants au panneau
        add(lblTitle);
        add(lblIdTable);
        add(txtIdTable);
        add(lblNomClient);
        add(txtNomClient);
        add(btnReserver);
        add(btnRetour);
    }

    private void reserverTable() {
        try {
            int idTable = Integer.parseInt(txtIdTable.getText());
            String nomClient = txtNomClient.getText();

            if (nomClient.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer un nom de client valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Serveur serveur = new Serveur(); // Simulez une interaction avec la classe Serveur
            boolean success = serveur.reserverTable(idTable, nomClient);

            if (success) {
                JOptionPane.showMessageDialog(this, "La table a été réservée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Échec de la réservation. La table est peut-être déjà réservée.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un ID de table valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
