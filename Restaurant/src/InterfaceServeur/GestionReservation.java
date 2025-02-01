package InterfaceServeur;
import Restaurant.*;

import javax.swing.*;

public class GestionReservation extends javax.swing.JPanel {


    public GestionReservation() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(700, 500));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Andalus", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Gestion des Réservation");

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setFont(new java.awt.Font("Sitka Small", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 102));
        jButton1.setText("Gestion Commande");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(153, 153, 153));
        jButton4.setFont(new java.awt.Font("Sitka Small", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 102, 102));
        jButton4.setText("Gestion Commande");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(153, 153, 153));
        jButton5.setFont(new java.awt.Font("Sitka Small", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 102, 102));
        jButton5.setText("Gestion Commande");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\DELL LATITUDE\\OneDrive\\Bureau\\Projet-UML\\images\\image1.png")); // NOI18N

        jButton2.setBackground(new java.awt.Color(153, 153, 153));
        jButton2.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 102, 102));
        jButton2.setText("Réserver Table");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(153, 153, 153));
        jButton6.setFont(new java.awt.Font("Rockwell Condensed", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 102, 102));
        jButton6.setText("Vérifier Etat Table");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 102, 102));
        jButton8.setText("ROUTEUR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4)
                                .addGap(69, 69, 69))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(280, 280, 280)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3172, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton1)
                                        .addComponent(jButton5))
                                .addGap(56, 56, 56))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(86, 86, 86)
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                                                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(3110, 3110, 3110))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(71, 71, 71)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(62, 62, 62)
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(96, 96, 96)
                                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(645, 645, 645)
                                                .addComponent(jLabel2)
                                                .addGap(326, 326, 326)
                                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(262, 262, 262)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(32, 32, 32)
                                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        JFrame verifiefram = new JFrame("Restaurant ~MoHa~");
        verifiefram.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Allow the frame to be closed
        verifiefram.setResizable(false);

        // Create an instance of the GestionCommandes panel
        VerifierTable Panel = new VerifierTable();

        verifiefram.getContentPane().add(Panel);

        // Set the size of the JFrame
        verifiefram.setSize(700, 500);

        // Center the frame on the screen
        verifiefram.setLocationRelativeTo(null);

        // Set the JFrame to be visible
        verifiefram.setVisible(true);


        JFrame Reservations = (JFrame) SwingUtilities.getWindowAncestor(this);
        Reservations.dispose();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        JFrame tablefram = new JFrame("Restaurant ~MoHa~");
        tablefram.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Allow the frame to be closed
        tablefram.setResizable(false);

        // Create an instance of the GestionCommandes panel
        ReseverTable Panel = new ReseverTable();

        // Add the GestionCommandes panel to the JFrame
        tablefram.getContentPane().add(Panel);

        // Set the size of the JFrame
        tablefram.setSize(700, 500);

        // Center the frame on the screen
        tablefram.setLocationRelativeTo(null);

        // Set the JFrame to be visible
        tablefram.setVisible(true);


        JFrame serveurFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        serveurFrame.dispose();
    }

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
        JFrame Returnfram = new JFrame("Restaurant ~MoHa~");
        Returnfram.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Allow the frame to be closed
        Returnfram.setResizable(false);

        // Create an instance of the GestionCommandes panel
        ServeurGUI Panel = new ServeurGUI();

        // Add the GestionCommandes panel to the JFrame
        Returnfram.getContentPane().add(Panel);

        // Set the size of the JFrame
        Returnfram.setSize(700, 500);

        // Center the frame on the screen
        Returnfram.setLocationRelativeTo(null);

        // Set the JFrame to be visible
        Returnfram.setVisible(true);


        JFrame serveurFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        serveurFrame.dispose();
    }


    // Variables declaration - do not modify
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration
}
