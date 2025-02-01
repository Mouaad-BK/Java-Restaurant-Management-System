package Restaurant;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class Commande {

    private int idCommande;
    private int idTable;
    private String nomClient;
    private LocalDateTime dateCommande;
    private String statut;
    private Paiment paiment ;
    private List<Plat> listePlats;

    public Commande() {
    }

    public int getIdCommande() {
        return idCommande;
    }
    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdTable() {
        return idTable;
    }
    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public String getNomClient() {
        return nomClient;
    }
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }
    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Paiment getPaiment() {
        return paiment;
    }
    public void setPaiment(Paiment paiment) {
        this.paiment = paiment;
    }

    public List<Plat> getListePlats() {
        return listePlats;
    }
    public void setListePlats(List<Plat> listePlats) {
        this.listePlats = listePlats;
    }

    public boolean finaliserCommande(int idCommande) {
        boolean finalisationReussie = false;

        // Connexion à la base de données
        Connection conn = Connecter.getConnection();

        if (conn != null) {
            String sql = "UPDATE commande SET statut = ? WHERE idCommande = ?";  // Requête SQL pour mettre à jour le statut de la commande

            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, "Terminée");  // Mettre le statut à "Terminée"
                stmt.setInt(2, idCommande);     // Passer l'id de la commande à mettre à jour

                int rowsAffected = stmt.executeUpdate();

                // Si des lignes ont été affectées, la commande a été mise à jour
                if (rowsAffected > 0) {
                    finalisationReussie = true;
                }

                stmt.close();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la finalisation de la commande : " + e.getMessage());
            } finally {
                Connecter.closeConnection(conn);  // Fermer la connexion
            }
        }

        return finalisationReussie;  // Retourne true si la commande a été finalisée, sinon false
    }

    public boolean ajouterPlatCommande(int idCommande, int idPlat) {
        boolean commandeAjoutee = false;

        // Connexion à la base de données
        Connection conn = Connecter.getConnection();

        if (conn != null) {
            try {
                // Vérifier si l'ID de commande existe déjà dans la table 'commande'
                String sqlSelect = "SELECT nomClient FROM commande WHERE idCommande = ?";
                PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect);
                stmtSelect.setInt(1, idCommande);  // Utiliser l'idCommande pour vérifier son existence
                ResultSet rs = stmtSelect.executeQuery();

                if (rs.next()) {
                    // Récupérer le nom du client de la commande existante
                    String nomClient = rs.getString("nomClient");

                    // SQL pour créer une nouvelle commande avec les mêmes informations mais une nouvelle date et un statut "En cours"
                    String sqlInsert = "INSERT INTO commande (idCommande, idTable, nomClient, idPlat, dateCommande, statut) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert);

                    // Passer les valeurs dans la requête
                    stmtInsert.setInt(1, idCommande);     // ID de la commande (même que celle existante)
                    stmtInsert.setInt(2, 1);              // ID de la table (par défaut à 1, à adapter selon votre logique)
                    stmtInsert.setString(3, nomClient);   // Nom du client récupéré de la commande existante
                    stmtInsert.setInt(4, idPlat);         // ID du plat à ajouter
                    stmtInsert.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));  // Date de la commande = date actuelle
                    stmtInsert.setString(6, "En cours");  // Statut par défaut "En cours"

                    // Exécution de la requête d'insertion
                    int rowsAffected = stmtInsert.executeUpdate();

                    if (rowsAffected > 0) {
                        commandeAjoutee = true;  // Si l'insertion est réussie
                    }

                    stmtInsert.close();

                    // Après l'ajout de la commande, on insère également l'idPlat dans la table 'statistiques'
                    if (commandeAjoutee) {
                        // Insertion de l'idPlat dans la colonne 'platsCommandes' dans la table 'statistiques'
                        String sqlInsertStat = "INSERT INTO statistique (platsCommandes) VALUES (?)";
                        PreparedStatement stmtInsertStat = conn.prepareStatement(sqlInsertStat);

                        // Ajouter l'idPlat à la colonne platsCommandes
                        stmtInsertStat.setString(1, String.valueOf(idPlat));  // Ajouter le plat (le plat est un seul id, donc on le passe en chaîne de caractères)
                        stmtInsertStat.executeUpdate();
                        stmtInsertStat.close();
                    }
                }

                rs.close();
                stmtSelect.close();

            } catch (SQLException e) {
                System.err.println("Erreur lors de l'ajout du plat à la commande : " + e.getMessage());
            } finally {
                Connecter.closeConnection(conn);
            }
        }

        return commandeAjoutee;  // Retourne true si l'ajout est réussi, sinon false
    }




    public boolean retirerPlatCommande(int idCommande, int idPlat) {
        boolean platRetire = false;

        // Connexion à la base de données
        Connection conn = Connecter.getConnection();

        if (conn != null) {
            try {
                // Requête SQL pour supprimer une ligne spécifique
                String sql = "DELETE FROM commande WHERE idCommande = ? AND idPlat = ? LIMIT 1";

                PreparedStatement stmt = conn.prepareStatement(sql);

                // Passer les valeurs dans la requête
                stmt.setInt(1, idCommande);  // ID de la commande
                stmt.setInt(2, idPlat);     // ID du plat

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    platRetire = true;  // Si une ligne a été supprimée
                }

                stmt.close();

            } catch (SQLException e) {
                System.err.println("Erreur lors de la suppression du plat de la commande : " + e.getMessage());
            } finally {
                Connecter.closeConnection(conn);
            }
        }

        return platRetire;
    }


}



