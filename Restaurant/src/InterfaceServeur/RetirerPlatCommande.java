package InterfaceServeur;
import Restaurant.*;

import javax.swing.*;

public class RetirerPlatCommande extends javax.swing.JPanel {

    private JTextField commandeNumberField;
    private JTextField platNumberField;
    private JButton retirerButton;
    private JLabel commandeLabel;
    private JLabel platLabel;
    private JLabel titleLabel;
    private JButton retourButton;  // Nouveau bouton de retour

    public RetirerPlatCommande() {
        initComponents();
        setBackground(new java.awt.Color(0, 153, 153)); // Appliquer la couleur de fond ici
    }

    private void initComponents() {
        // Initialisation des composants
        commandeNumberField = new JTextField();
        platNumberField = new JTextField();
        retirerButton = new JButton("Retirer Plat");
        commandeLabel = new JLabel("Numéro de commande :");
        platLabel = new JLabel("Numéro du plat :");
        titleLabel = new JLabel("Retirer Plat d'une Commande");
        retourButton = new JButton("Retour");  // Initialiser le bouton de retour

        // Personnalisation des composants
        titleLabel.setFont(new java.awt.Font("Andalus", java.awt.Font.BOLD, 30));
        titleLabel.setForeground(new java.awt.Color(102, 102, 102)); // Couleur du titre
        commandeLabel.setFont(new java.awt.Font("Arial", 0, 16));
        platLabel.setFont(new java.awt.Font("Arial", 0, 16));
        retirerButton.setBackground(new java.awt.Color(153, 204, 255));
        retourButton.setBackground(new java.awt.Color(177, 84, 84)); // Couleur du bouton de retour


        // Définir le layout et la taille du panneau
        setPreferredSize(new java.awt.Dimension(700, 500));
        setLayout(null);  // Utilisation d'un layout absolu

        // Définir les positions des composants
        titleLabel.setBounds(31, 20, 400, 40);  // Position du titre en haut
        commandeLabel.setBounds(31, 100, 200, 30);
        commandeNumberField.setBounds(200, 100, 150, 30);
        platLabel.setBounds(31, 150, 200, 30);
        platNumberField.setBounds(200, 150, 150, 30);
        retirerButton.setBounds(200, 200, 150, 40);
        retourButton.setBounds(10, 400, 90, 45);  // Positionner le bouton de retour en haut à gauche
        retourButton.setFont(new java.awt.Font("Andalus", 0, 15));
        // Ajouter un écouteur d'événements au bouton de retrait
        retirerButton.addActionListener(evt -> retirerPlatDeCommande());

        // Ajouter un écouteur d'événements au bouton de retour
        retourButton.addActionListener(evt -> retourButtonActionPerformed(evt));

        // Ajouter les composants au panneau
        add(titleLabel);  // Ajouter le titre
        add(commandeLabel);
        add(commandeNumberField);
        add(platLabel);
        add(platNumberField);
        add(retirerButton);
        add(retourButton);  // Ajouter le bouton de retour
    }

    private void retirerPlatDeCommande() {
        try {
            int numeroCommande = Integer.parseInt(commandeNumberField.getText());
            int numeroPlat = Integer.parseInt(platNumberField.getText());

            // Appel à la méthode de la classe Commande pour retirer le plat
            Commande commande = new Commande();
            boolean result = commande.retirerPlatCommande(numeroCommande, numeroPlat);  // Utiliser directement la méthode de la classe Commande

            if (result) {
                JOptionPane.showMessageDialog(this, "Plat " + numeroPlat + " retiré de la commande " + numeroCommande + " !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors du retrait du plat.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
