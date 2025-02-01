package InterfaceGerant;

import Restaurant.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConsulterChiffreAffaire extends JPanel {

    private JTextField dateTextField;
    private JButton consultButton;
    private JLabel resultLabel;
    private JButton retourButton; // Bouton de retour

    public ConsulterChiffreAffaire() {
        initComponents();
    }

    private void initComponents() {
        // Configure this panel as the main container with background color
        setPreferredSize(new Dimension(700, 500));
        setBackground(new Color(0, 153, 153));
        setLayout(null); // Désactiver le layout manager pour utiliser setBounds

        // Label for title
        JLabel titleLabel = new JLabel("Consulter Chiffre d'affaire Quotidien");
        titleLabel.setFont(new Font("Andalus", Font.BOLD, 30));
        titleLabel.setForeground(new Color(102, 102, 102));
        titleLabel.setBounds(100, 30, 500, 40); // Position et taille
        add(titleLabel);

        // Input field for date (year-month-day)
        JLabel dateLabel = new JLabel("Entrez la date (AAAA-MM-JJ):");
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        dateLabel.setBounds(100, 100, 250, 30); // Position et taille
        add(dateLabel);

        dateTextField = new JTextField(10);
        dateTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        dateTextField.setBounds(350, 100, 150, 30); // Position et taille
        add(dateTextField);

        // Button to trigger the calculation
        consultButton = new JButton("Ok");
        consultButton.setForeground(Color.BLACK);
        consultButton.setBackground(new Color(153, 204, 255));
        consultButton.setBounds(390, 150, 90, 40); // Position et taille
        add(consultButton);

        // Label to display result
        resultLabel = new JLabel("Chiffre d'affaire : ");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        resultLabel.setForeground(Color.RED);
        resultLabel.setBounds(100, 220, 400, 30); // Position et taille
        add(resultLabel);

        // Button for returning to StatistiqueGUI
        retourButton = new JButton("Retour");
        retourButton.setForeground(Color.BLACK);
        retourButton.setBackground(new Color(255, 102, 102));
        retourButton.setBounds(10, 400, 100, 40); // Position et taille
        add(retourButton);

        // Action listener for the consult button
        consultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String dateStr = dateTextField.getText();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(dateStr, formatter);

                    // Call the method to calculate chiffre d'affaire
                    Gerant gerant = new Gerant();
                    double chiffreAffaire = gerant.calculerChiffreAffaire(date);

                    // Display result
                    resultLabel.setText("Chiffre d'affaire: " + chiffreAffaire + " DH");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Format de date invalide. Utilisez AAAA-MM-JJ.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action listener for the retour button
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retourner à la page StatistiqueGUI
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(ConsulterChiffreAffaire.this);
                parentFrame.dispose(); // Fermer l'actuel JFrame

                // Création de la fenêtre de retour
                JFrame retourFrame = new JFrame("Statistiques");
                retourFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                retourFrame.getContentPane().add(new StatistiqueGUI());
                retourFrame.setSize(700, 500);
                retourFrame.setLocationRelativeTo(null); // Centrer la fenêtre
                retourFrame.setResizable(false); // Désactiver la maximisation de la fenêtre
                retourFrame.setVisible(true);
            }
        });
    }
}
