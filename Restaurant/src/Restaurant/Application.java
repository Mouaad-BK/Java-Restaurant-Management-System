package Restaurant;

import InterfaceServeur.*;
import InterfaceGerant.*;

import javax.swing.*;
import java.awt.*;

public class Application extends javax.swing.JFrame {

    public Application() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Restaurant ~MoHa~");

        // Ajouter l'icône à la fenêtre (Logo)
        ImageIcon icon = new ImageIcon("C:\\Users\\DELL LATITUDE\\OneDrive\\Bureau\\Projet-UML\\images\\icon.png"); // Chemin de l'icône
        Image image = icon.getImage();
        setIconImage(image);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        // Affichage de l'image dans le panneau gauche
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\DELL LATITUDE\\OneDrive\\Bureau\\Projet-UML\\images\\image2.png")); // Remplacez par le chemin correct

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5))
                                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel5)
                                .addGap(45, 45, 45)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(159, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, -10, 420, 510);

        // Ajout d'un texte supplémentaire sous forme de label
        jLabel3.setFont(new java.awt.Font("Georgia", 2, 25)); // Style italic
        jLabel3.setText("Restaurant ~MoHa~");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(70, 310, 350, 43); // Positionner le texte dans la fenêtre principale
        jLabel3.setForeground(Color.LIGHT_GRAY);

        // Ajout du jLabel4 dans jPanel2
        jLabel4.setFont(new java.awt.Font("Georgia", 2, 10)); // Style italic
        jLabel4.setText("© 2024 - Restaurant ~MoHa~");
        jPanel2.add(jLabel4); // Ajout de jLabel4 à jPanel2
        jLabel4.setBounds(260, 480, 350, 43); // Positionner le texte dans la fenêtre principale
        jLabel4.setForeground(Color.white);

        jLabel2.setFont(new java.awt.Font("Impact", 3, 32));  // Utiliser une police standard
        jLabel2.setText("Choisir votre Statut");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(460, 25, 300, 60);

        jButton5.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jButton5.setText("SERVEUR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5);
        jButton5.setBounds(620, 200, 100, 50);

        jButton6.setFont(new java.awt.Font("Tahoma", 3, 14));
        jButton6.setText("GÉRANT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6);
        jButton6.setBounds(470, 200, 100, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        JFrame serverFrame = new JFrame("Restaurant ~MoHa~");
        serverFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        serverFrame.setSize(700, 500);
        serverFrame.setResizable(false);

        ServeurGUI serverPanel = new ServeurGUI();
        serverFrame.getContentPane().add(serverPanel);

        serverFrame.setVisible(true);
        serverFrame.setLocationRelativeTo(null);

        this.dispose();
    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        JFrame gerantFrame = new JFrame("Restaurant ~MoHa~");
        gerantFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gerantFrame.setSize(700, 500);
        gerantFrame.setResizable(false);

        GerantGUI Panel = new GerantGUI();
        gerantFrame.getContentPane().add(Panel);

        gerantFrame.setVisible(true);
        gerantFrame.setLocationRelativeTo(null);

        this.dispose();
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Application().setVisible(true);
            }
        });
    }

    // Variables declaration
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // Fin des variables
}
