package Restaurant;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Gerant {

    private int idGerant;
    private String nomGerant;
    private String prenomGerant;
    private Menu menu;

    public Gerant() {
    }

    public int getIdGerant() {
        return idGerant;
    }
    public void setIdGerant(int idGerant) {
        this.idGerant = idGerant;
    }

    public String getNomGerant() {
        return nomGerant;
    }
    public void setNomGerant(String nomGerant) {
        this.nomGerant = nomGerant;
    }

    public String getPrenomGerant() {
        return prenomGerant;
    }
    public void setPrenomGerant(String prenomGerant) {
        this.prenomGerant = prenomGerant;
    }

    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    // Méthode pour ajouter une table
    public boolean ajouterTable(String etat) {
        boolean ajoutReussi = false;

        Connection conn = Connecter.getConnection();

        if (conn != null) {
            String sql = "INSERT INTO tables (etat) VALUES (?)";
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, etat);

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    ajoutReussi = true;
                }

                stmt.close();
            } catch (SQLException e) {
                System.err.println("Erreur lors de l'ajout de la table : " + e.getMessage());
            } finally {
                Connecter.closeConnection(conn);
            }
        }

        return ajoutReussi;
    }

    // Méthode pour retirer une table
    public boolean retirerTable(int idTable) {
        boolean suppressionReussie = false;

        Connection conn = Connecter.getConnection();

        if (conn != null) {
            String sql = "DELETE FROM tables WHERE idTable = ?";
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idTable);

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    suppressionReussie = true;
                }

                stmt.close();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la suppression de la table : " + e.getMessage());
            } finally {
                Connecter.closeConnection(conn);
            }
        }

        return suppressionReussie;
    }

    public JTable consulterEtatTables() {
        // Connexion à la base de données
        Connection conn = Connecter.getConnection();
        String query = "SELECT idTable, etat FROM tables"; // Exemple de requête SQL

        // Créer un modèle de table vide
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Table");
        model.addColumn("État");

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                // Ajouter les lignes au modèle
                Object[] row = new Object[2];
                row[0] = rs.getInt("idTable");
                row[1] = rs.getString("etat");
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception si nécessaire
        }

        // Créer une JTable avec le modèle et la retourner
        return new JTable(model); // Retourner un JTable avec le modèle rempli
    }


    public JTable affichererMenu() {
        List<Plat> plats = new ArrayList<>();
        Connection conn = Connecter.getConnection();

        if (conn != null) {
            String sql = "SELECT idPlat, nomPlat, prixPlat, typePlat FROM plat"; // Récupérer tous les plats
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                // Parcours des résultats et création des objets Plat
                while (rs.next()) {
                    int idPlat = rs.getInt("idPlat");
                    String nomPlat = rs.getString("nomPlat");
                    float prixPlat = rs.getFloat("prixPlat");
                    String typePlat = rs.getString("typePlat");

                    plats.add(new Plat(idPlat, nomPlat, prixPlat, typePlat)); // Ajout des plats à la liste
                }

                rs.close();
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la consultation du menu : " + e.getMessage());
            } finally {
                Connecter.closeConnection(conn);
            }
        }

        // Création du tableau de données pour la JTable
        Object[][] data = new Object[plats.size()][4];
        for (int i = 0; i < plats.size(); i++) {
            data[i][0] = plats.get(i).getIdPlat();
            data[i][1] = plats.get(i).getNomPlat();
            data[i][2] = plats.get(i).getPrix();
            data[i][3] = plats.get(i).getTypePlat();
        }

        // Noms des colonnes
        String[] columnNames = {"N° Plat", "Nom", "Prix (DH)", "Type"};

        // Création du modèle pour la JTable
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        // Ajout d'un JScrollPane pour permettre le défilement
        JScrollPane scrollPane = new JScrollPane(table);

        // Retour de la JTable
        return table;
    }

    public double calculerChiffreAffaire(LocalDate date) {
        double chiffreAffaire = 0.0;  // Initialiser la somme

        // Connexion à la base de données
        Connection conn = Connecter.getConnection();

        if (conn != null) {
            try {
                // Convertir LocalDate en Timestamp pour la comparaison
                Timestamp timestampDate = Timestamp.valueOf(date.atStartOfDay());

                // SQL pour calculer la somme des paiements pour une date donnée
                String sql = "SELECT SUM(prix) FROM paiment WHERE DATE(datePaiment) = ?";

                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setTimestamp(1, timestampDate);  // Passer la date en paramètre

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    // Récupérer la somme
                    chiffreAffaire = rs.getDouble(1);  // La somme des prix
                }

                rs.close();
                stmt.close();

            } catch (SQLException e) {
                System.err.println("Erreur lors du calcul du chiffre d'affaires : " + e.getMessage());
            } finally {
                Connecter.closeConnection(conn);
            }
        }

        return chiffreAffaire;  // Retourner le chiffre d'affaires total pour la date donnée
    }



    public JTable afficherPlatsLesPlusCommandes() {
        JTable table = null;

        // Connexion à la base de données
        Connection conn = Connecter.getConnection();

        if (conn != null) {
            try {
                // Étape 1 : Récupérer les IDs des plats dans la table statistique
                String sqlStatistique = "SELECT platsCommandes FROM statistique";
                PreparedStatement stmtStatistique = conn.prepareStatement(sqlStatistique);
                ResultSet rsStatistique = stmtStatistique.executeQuery();

                // Map pour compter les occurrences des IDs de plats
                Map<Integer, Integer> platCount = new HashMap<>();

                while (rsStatistique.next()) {
                    int idPlat = rsStatistique.getInt("platsCommandes");
                    platCount.put(idPlat, platCount.getOrDefault(idPlat, 0) + 1);
                }

                rsStatistique.close();
                stmtStatistique.close();

                // Étape 2 : Trier les plats par leur fréquence (valeur dans la map)
                List<Map.Entry<Integer, Integer>> sortedPlats = new ArrayList<>(platCount.entrySet());
                sortedPlats.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue())); // Tri décroissant

                // Prendre les 3 premiers plats les plus populaires
                List<Integer> topPlats = new ArrayList<>();
                for (int i = 0; i < Math.min(5, sortedPlats.size()); i++) {
                    topPlats.add(sortedPlats.get(i).getKey());
                }

                if (topPlats.isEmpty()) {
                    System.out.println("Aucun plat trouvé.");
                    return new JTable(); // Retourne une table vide si aucun plat trouvé
                }

                // Étape 3 : Récupérer les détails des plats correspondants
                StringBuilder placeholders = new StringBuilder("?");
                for (int i = 1; i < topPlats.size(); i++) {
                    placeholders.append(", ?");
                }

                String sqlPlat = "SELECT * FROM plat WHERE idPlat IN (" + placeholders + ")";
                PreparedStatement stmtPlat = conn.prepareStatement(sqlPlat);

                for (int i = 0; i < topPlats.size(); i++) {
                    stmtPlat.setInt(i + 1, topPlats.get(i));
                }

                ResultSet rsPlat = stmtPlat.executeQuery();

                // Construire le modèle de la table
                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.addColumn("ID");
                tableModel.addColumn("Nom");
                tableModel.addColumn("Prix");

                while (rsPlat.next()) {
                    int idPlat = rsPlat.getInt("idPlat");
                    String nomPlat = rsPlat.getString("nomPlat");
                    double prix = rsPlat.getDouble("prixPlat");

                    tableModel.addRow(new Object[]{idPlat, nomPlat, prix});
                }

                rsPlat.close();
                stmtPlat.close();

                // Créer un JTable avec le modèle
                table = new JTable(tableModel);

            } catch (SQLException e) {
                System.err.println("Erreur lors de la récupération des plats les plus commandés : " + e.getMessage());
            } finally {
                Connecter.closeConnection(conn);
            }
        }

        return table;
    }

}
