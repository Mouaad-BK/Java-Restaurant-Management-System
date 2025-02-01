package InterfaceGerant;
import Restaurant.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModifierPlatMenu extends javax.swing.JPanel {

    private JTextField idPlatField;
    private JTextField nomPlatField;
    private JTextField prixPlatField;
    private JComboBox<String> typePlatComboBox; // Remplacé par JComboBox
    private JButton modifierButton;

    public ModifierPlatMenu() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonRetour = new javax.swing.JButton();

        // Champs pour les informations du plat
        JLabel idPlatLabel = new JLabel("Numero de Plat :");
        idPlatField = new JTextField();

        JLabel nomPlatLabel = new JLabel("Nom Plat :");
        nomPlatField = new JTextField();

        JLabel prixPlatLabel = new JLabel("Prix Plat :");
        prixPlatField = new JTextField();

        JLabel typePlatLabel = new JLabel("Type Plat :");

        // JComboBox pour choisir le type de plat
        String[] types = { "Boisson", "Entrée", "Plat principal", "Dessert" };
        typePlatComboBox = new JComboBox<>(types); // Remplacer le JTextField par un JComboBox

        // Bouton pour modifier
        modifierButton = new JButton("Modifier Plat");

        setPreferredSize(new java.awt.Dimension(700, 500));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153)); // Fond du panneau

        jLabel1.setFont(new java.awt.Font("Andalus", 1, 30));
        jLabel1.setForeground(new java.awt.Color(102, 102, 102)); // Couleur du titre
        jLabel1.setText("Modifier Plat");

        // Bouton Retour avec couleur rouge clair
        jButtonRetour.setBackground(new java.awt.Color(255, 102, 102));
        jButtonRetour.setText("Retour");
        jButtonRetour.addActionListener(evt -> retourButtonActionPerformed(evt));

        // Définir les positions et tailles des composants
        jPanel3.setLayout(null);
        jLabel1.setBounds(200, 20, 300, 40);
        idPlatLabel.setBounds(50, 100, 100, 30);
        idPlatField.setBounds(150, 100, 150, 30);
        nomPlatLabel.setBounds(50, 150, 100, 30);
        nomPlatField.setBounds(150, 150, 150, 30);
        prixPlatLabel.setBounds(50, 200, 100, 30);
        prixPlatField.setBounds(150, 200, 150, 30);
        typePlatLabel.setBounds(50, 250, 100, 30);
        typePlatComboBox.setBounds(150, 250, 150, 30); // Positionner le JComboBox
        modifierButton.setBounds(150, 300, 150, 40);
        jButtonRetour.setBounds(10, 400, 90, 40);

        // Couleur du bouton modifie : grise avec texte vert foncé
        modifierButton.setForeground(Color.black);
        modifierButton.setBackground(new java.awt.Color(153, 204, 255));

        // Ajouter les composants au panneau
        jPanel3.add(jLabel1);
        jPanel3.add(idPlatLabel);
        jPanel3.add(idPlatField);
        jPanel3.add(nomPlatLabel);
        jPanel3.add(nomPlatField);
        jPanel3.add(prixPlatLabel);
        jPanel3.add(prixPlatField);
        jPanel3.add(typePlatLabel);
        jPanel3.add(typePlatComboBox); // Ajouter le JComboBox
        jPanel3.add(modifierButton);
        jPanel3.add(jButtonRetour);

        // Ajouter l'action au bouton Modifier
        modifierButton.addActionListener(this::modifierButtonActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    private void modifierButtonActionPerformed(ActionEvent evt) {
        try {
            int idPlat = Integer.parseInt(idPlatField.getText());
            String nouveauNom = nomPlatField.getText();
            float nouveauPrix = Float.parseFloat(prixPlatField.getText());
            String nouveauType = (String) typePlatComboBox.getSelectedItem(); // Récupérer l'élément sélectionné dans le JComboBox

            boolean result = modifierPlat(idPlat, nouveauNom, nouveauPrix, nouveauType);

            if (result) {
                JOptionPane.showMessageDialog(this, "Plat modifié avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Échec de la modification.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void retourButtonActionPerformed(ActionEvent evt) {
        JFrame ReturnFrame = new JFrame("Restaurant ~MoHa~");
        ReturnFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ReturnFrame.setResizable(false);

        GestionMenu Panel = new GestionMenu();
        ReturnFrame.getContentPane().add(Panel);
        ReturnFrame.setSize(700, 500);
        ReturnFrame.setLocationRelativeTo(null);
        ReturnFrame.setVisible(true);

        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        currentFrame.dispose();
    }

    public boolean modifierPlat(int idPlat, String nouveauNom, float nouveauPrix, String nouveauType) {
        boolean modificationReussie = false;

        Connection conn = Connecter.getConnection();

        if (conn != null) {
            String sql = "UPDATE plat SET nomPlat = ?, prixPlat = ?, typePlat = ? WHERE idPlat = ?";
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, nouveauNom.trim());
                stmt.setFloat(2, nouveauPrix);
                stmt.setString(3, nouveauType.toLowerCase()); // Utiliser la valeur sélectionnée
                stmt.setInt(4, idPlat);

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    modificationReussie = true;
                }
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la modification du plat : " + e.getMessage());
            } finally {
                Connecter.closeConnection(conn);
            }
        }

        return modificationReussie;
    }

    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jButtonRetour;
}

