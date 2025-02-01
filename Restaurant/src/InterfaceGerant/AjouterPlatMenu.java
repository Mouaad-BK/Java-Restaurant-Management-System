package InterfaceGerant;
import Restaurant.*;

import javax.swing.*;

public class AjouterPlatMenu extends javax.swing.JPanel {

    private JTextField nomPlatField;
    private JTextField prixField;
    private JComboBox<String> typeComboBox;
    private JButton addButton;
    private JButton retourButton;
    private JLabel nomPlatLabel;
    private JLabel prixLabel;
    private JLabel typeLabel;
    private JLabel titleLabel;

    public AjouterPlatMenu() {
        initComponents();
        setBackground(new java.awt.Color(0, 153, 153)); // Couleur de fond
    }

    private void initComponents() {
        // Initialisation des composants
        nomPlatField = new JTextField();
        prixField = new JTextField();
        typeComboBox = new JComboBox<>(new String[]{"Entrée", "Plat principal", "Dessert", "Boisson"});
        addButton = new JButton("Ajouter Plat");
        retourButton = new JButton("Retour");
        nomPlatLabel = new JLabel("Nom du Plat :");
        prixLabel = new JLabel("Prix du Plat :");
        typeLabel = new JLabel("Type de Plat :");
        titleLabel = new JLabel("Ajouter un Plat au Menu");

        // Personnalisation des composants
        titleLabel.setFont(new java.awt.Font("Andalus", java.awt.Font.BOLD, 30));
        titleLabel.setForeground(new java.awt.Color(102, 102, 102)); // Couleur du titre
        nomPlatLabel.setFont(new java.awt.Font("Arial", 0, 16));
        prixLabel.setFont(new java.awt.Font("Arial", 0, 16));
        typeLabel.setFont(new java.awt.Font("Arial", 0, 16));
        addButton.setBackground(new java.awt.Color(153, 204, 255));
        retourButton.setBackground(new java.awt.Color(182, 89, 89)); // Couleur du bouton de retour
        retourButton.setFont(new java.awt.Font("Andalus", 0, 15));

        // Définir le layout et la taille du panneau
        setPreferredSize(new java.awt.Dimension(700, 500));
        setLayout(null); // Utilisation d'un layout absolu

        // Définir les positions des composants
        titleLabel.setBounds(31, 20, 400, 40); // Position du titre
        nomPlatLabel.setBounds(31, 100, 200, 30);
        nomPlatField.setBounds(200, 100, 150, 30);
        prixLabel.setBounds(31, 150, 200, 30);
        prixField.setBounds(200, 150, 150, 30);
        typeLabel.setBounds(31, 200, 200, 30);
        typeComboBox.setBounds(200, 200, 150, 30);
        addButton.setBounds(200, 250, 150, 40);
        retourButton.setBounds(10, 400, 90, 45); // Bouton de retour en bas à gauche

        // Ajouter un écouteur d'événements au bouton d'ajout
        addButton.addActionListener(evt -> ajouterPlatAction());

        // Ajouter un écouteur d'événements au bouton de retour
        retourButton.addActionListener(evt -> retourButtonActionPerformed(evt));

        // Ajouter les composants au panneau
        add(titleLabel); // Ajouter le titre
        add(nomPlatLabel);
        add(nomPlatField);
        add(prixLabel);
        add(prixField);
        add(typeLabel);
        add(typeComboBox);
        add(addButton);
        add(retourButton);
    }

    private void ajouterPlatAction() {
        String nomPlat = nomPlatField.getText().trim();
        String prixText = prixField.getText().trim();
        String type = (String) typeComboBox.getSelectedItem();

        if (nomPlat.isEmpty() || prixText.isEmpty() || type == null) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            float prix = Float.parseFloat(prixText);

            // Appel à la méthode pour ajouter le plat dans la base de données
            Menu menu = new Menu();
            boolean success = menu.ajouterPlat(nomPlat, prix, type);

            if (success) {
                JOptionPane.showMessageDialog(this, "Plat ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                // Réinitialiser les champs
                nomPlatField.setText("");
                prixField.setText("");
                typeComboBox.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du plat.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Le prix doit être un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void retourButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JFrame Returnfram = new JFrame("Restaurant ~MoHa~");
        Returnfram.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Returnfram.setResizable(false);

        // Crée une instance du panneau GestionMenu
        GestionMenu panel = new GestionMenu();

        // Ajouter le panneau au JFrame
        Returnfram.getContentPane().add(panel);

        // Définir la taille et la position du JFrame
        Returnfram.setSize(700, 500);
        Returnfram.setLocationRelativeTo(null);
        Returnfram.setVisible(true);

        // Fermer la fenêtre actuelle
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        currentFrame.dispose();
    }
}

