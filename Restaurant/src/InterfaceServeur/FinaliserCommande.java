package InterfaceServeur;
import Restaurant.*;

import javax.swing.*;

public class FinaliserCommande extends javax.swing.JPanel {

    private JTextField commandeNumberField;
    private JLabel promptLabel;
    private JLabel jLabel1;
    private JButton jButton8;
    private JButton retourButton;  // Nouveau bouton de retour

    public FinaliserCommande() {
        initComponents();
        setBackground(new java.awt.Color(0, 153, 153)); // Appliquer la couleur de fond ici
    }

    private void initComponents() {
        // Initialisation des composants
        commandeNumberField = new JTextField();
        promptLabel = new JLabel("Entrez le numéro de la commande :");
        jLabel1 = new JLabel("Finaliser une Commande");
        jButton8 = new JButton("Finaliser Commande");
        retourButton = new JButton("Retour");  // Initialiser le bouton de retour

        // Définir le layout et la taille du panneau
        setPreferredSize(new java.awt.Dimension(700, 500));
        setLayout(null);  // Utilisation d'un layout absolu

        // Personnalisation des composants
        commandeNumberField.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        promptLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        jLabel1.setFont(new java.awt.Font("Andalus", java.awt.Font.BOLD, 30));
        jLabel1.setForeground(new java.awt.Color(102, 102, 102)); // Couleur du titre
        jButton8.setBackground(new java.awt.Color(153, 204, 255));
        retourButton.setBackground(new java.awt.Color(200, 77, 77)); // Couleur du bouton de retour
        retourButton.setFont(new java.awt.Font("Andalus", 0, 15));

        // Définir les positions des composants
        jLabel1.setBounds(31, 20, 335, 40);  // Position du titre en haut
        promptLabel.setBounds(31, 100, 300, 30);
        commandeNumberField.setBounds(310, 100, 200, 30);
        jButton8.setBounds(325, 150, 160, 40);  // Positionner le bouton à droite et plus petit
        retourButton.setBounds(10, 400, 90, 45);  // Positionner le bouton de retour en haut à gauche

        // Ajouter un écouteur d'événements au bouton de finalisation
        jButton8.addActionListener(evt -> jButton8ActionPerformed(evt));

        // Ajouter un écouteur d'événements au bouton de retour
        retourButton.addActionListener(evt -> retourButtonActionPerformed(evt));

        // Ajouter les composants au panneau
        add(jLabel1);  // Ajouter le titre
        add(promptLabel);
        add(commandeNumberField);
        add(jButton8);
        add(retourButton);  // Ajouter le bouton de retour
    }

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int idCommande = Integer.parseInt(commandeNumberField.getText());

            Commande commande = new Commande();
            boolean result = commande.finaliserCommande(idCommande);

            if (result) {
                JOptionPane.showMessageDialog(this, "Commande finalisée avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Échec de la finalisation de la commande.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un numéro de commande valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
