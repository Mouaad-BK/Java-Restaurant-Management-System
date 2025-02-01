package Restaurant;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.util.List;

public class Serveur {
    private int idServeur;
    private String nomServeur;
    private String prenomServeur;
    private String statut;
    private Reservation reservation;
    private List<Table> listTables = new ArrayList<>();
    private List<Commande> commandeList = new ArrayList<>();
    List<Paiment> listePaiments = new ArrayList<>();

    public Serveur() {
    }

    public int getIdServeur() {
        return idServeur;
    }

    public void setIdServeur(int idServeur) {
        this.idServeur = idServeur;
    }

    public String getNomServeur() {
        return nomServeur;
    }

    public void setNomServeur(String nomServeur) {
        this.nomServeur = nomServeur;
    }

    public String getPrenomServeur() {
        return prenomServeur;
    }

    public void setPrenomServeur(String prenomServeur) {
        this.prenomServeur = prenomServeur;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public List<Table> getListTables() {
        return listTables;
    }
    public void setListTables(List<Table> listTables) {
        this.listTables = listTables;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public List<Commande> getCommandeList() {
        return commandeList;
    }
    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }

    public List<Paiment> getListePaiments() {
        return listePaiments;
    }
    public void setListePaiments(List<Paiment> listePaiments) {
        this.listePaiments = listePaiments;
    }

    public boolean verifierDisponibiliteTable(int idTable) {
        boolean estLibre = false;

        // Connexion à la base de données
        Connection conn = Connecter.getConnection();

        if (conn != null) {
            String sql = "SELECT etat FROM tables WHERE idTable = ?";
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idTable);  // Remplacer le ? par l'ID de la table

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String etat = rs.getString("etat").trim();
                    if ("Libre".equals(etat)) {
                        estLibre = true;
                    }
                }

                rs.close();
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la vérification de la disponibilité de la table : " + e.getMessage());
            } finally {
                Connecter.closeConnection(conn);
            }
        }

        return estLibre;
    }



    public boolean reserverTable(int idTable, String nomClient) {
        // Vérifier la disponibilité de la table
        boolean estLibre = verifierDisponibiliteTable(idTable);

        if (estLibre) {
            boolean reservationReussie = false;

            // Connexion à la base de données
            Connection conn = Connecter.getConnection();

            if (conn != null) {
                // 1. Récupérer la date et l'heure actuelles pour la réservation
                LocalDateTime dateReservation = LocalDateTime.now();

                // 2. Créer la réservation
                String reservationSql = "INSERT INTO reservation (idTable, nomClient, dateReservation) VALUES (?, ?, ?)";
                try {
                    PreparedStatement stmt = conn.prepareStatement(reservationSql);
                    stmt.setInt(1, idTable);
                    stmt.setString(2, nomClient);
                    stmt.setObject(3, dateReservation); // Utilisation de setObject pour LocalDateTime

                    int rowsAffected = stmt.executeUpdate();

                    // Si la réservation a réussi, on met à jour l'état de la table
                    if (rowsAffected > 0) {
                        // 3. Mettre l'état de la table à "Réservée"
                        String updateTableSql = "UPDATE tables SET etat = ? WHERE idTable = ?";
                        PreparedStatement updateStmt = conn.prepareStatement(updateTableSql);
                        updateStmt.setString(1, "Réservée");
                        updateStmt.setInt(2, idTable);

                        int rowsUpdated = updateStmt.executeUpdate();
                        if (rowsUpdated > 0) {
                            reservationReussie = true;
                        }

                        updateStmt.close();
                    }

                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Erreur lors de la réservation de la table : " + e.getMessage());
                } finally {
                    Connecter.closeConnection(conn);
                }
            }

            return reservationReussie;
        } else {
            return false;
        }
    }

    public boolean libererTable(int idTable) {
        boolean miseAJour = false;

        // Connexion à la base de données
        Connection conn = Connecter.getConnection();

        if (conn != null) {
            String sql = "UPDATE tables SET etat = 'Libre' WHERE idTable = ?";
            try {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idTable); // Remplacer le ? par l'ID de la table

                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    miseAJour = true; // Mise à jour réussie
                }

                stmt.close();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la mise à jour de la table : " + e.getMessage());
            } finally {
                Connecter.closeConnection(conn);
            }
        }

        return miseAJour;
    }

    public boolean creerCommande(int idCommande, int idTable, String nomClient, int idPlat) {
        boolean commandeReussie = false;

        // Connexion à la base de données
        Connection conn = Connecter.getConnection();

        if (conn != null) {
            // Vérifier si l'idTable existe dans la table des tables
            String checkTableExistenceSql = "SELECT COUNT(*) FROM tables WHERE idTable = ?";

            try {
                // Vérification de l'existence de la table
                PreparedStatement stmtCheckTable = conn.prepareStatement(checkTableExistenceSql);
                stmtCheckTable.setInt(1, idTable);
                ResultSet rsTable = stmtCheckTable.executeQuery();
                if (rsTable.next() && rsTable.getInt(1) == 0) {
                    // Si la table n'existe pas, retourner false
                    return false;
                }

                // Vérifier si l'idPlat existe dans la table des plats
                String checkPlatExistenceSql = "SELECT COUNT(*) FROM plat WHERE idPlat = ?";

                PreparedStatement stmtCheckPlat = conn.prepareStatement(checkPlatExistenceSql);
                stmtCheckPlat.setInt(1, idPlat);
                ResultSet rsPlat = stmtCheckPlat.executeQuery();
                if (rsPlat.next() && rsPlat.getInt(1) == 0) {
                    // Si le plat n'existe pas, retourner false
                    return false;
                }

                // Récupérer l'heure actuelle pour la commande
                LocalDateTime dateCommande = LocalDateTime.now();

                // SQL pour insérer une nouvelle commande avec l'idPlat et idCommande passé en argument
                String sql = "INSERT INTO commande (idCommande, idTable, nomClient, dateCommande, statut, idPlat) VALUES (?, ?, ?, ?, ?, ?)";

                // Préparation de la requête pour insérer la commande
                PreparedStatement stmt = conn.prepareStatement(sql);

                // Passer les valeurs dans la requête
                stmt.setInt(1, idCommande);  // ID de la commande
                stmt.setInt(2, idTable);  // ID de la table
                stmt.setString(3, nomClient);  // Nom du client
                stmt.setTimestamp(4, Timestamp.valueOf(dateCommande));  // Date de la commande
                stmt.setString(5, "En cours");  // Statut de la commande
                stmt.setInt(6, idPlat);  // ID du plat commandé

                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    commandeReussie = true;
                }

                stmt.close();

                // Si la commande a été insérée avec succès, insérer dans la table statistique
                if (commandeReussie) {
                    // Insertion dans la table statistique avec seulement l'idPlat
                    String sqlStatistique = "INSERT INTO statistique (platsCommandes) VALUES (?)";

                    // Utiliser l'idPlat directement
                    PreparedStatement stmtStatistique = conn.prepareStatement(sqlStatistique);
                    stmtStatistique.setInt(1, idPlat);  // L'ID du plat commandé

                    // Exécution de la requête d'insertion dans la table statistique
                    stmtStatistique.executeUpdate();
                    stmtStatistique.close();
                }

            } catch (SQLException e) {
                System.err.println("Erreur lors de la création de la commande ou de l'enregistrement des statistiques : " + e.getMessage());
            } finally {
                Connecter.closeConnection(conn);
            }
        }

        return commandeReussie;
    }

    public double calculerSommeCommande(int idCommande) {
        double somme = 0.0;  // Initialiser la somme

        // Connexion à la base de données
        Connection conn = Connecter.getConnection();

        if (conn != null) {
            try {
                // Étape 1 : Récupérer les ID des plats dans la commande
                String sqlCommande = "SELECT idPlat FROM commande WHERE idCommande = ?";
                PreparedStatement stmtCommande = conn.prepareStatement(sqlCommande);
                stmtCommande.setInt(1, idCommande);

                ResultSet rsCommande = stmtCommande.executeQuery();

                // Étape 2 : Parcourir chaque ligne pour récupérer les prix des plats
                while (rsCommande.next()) {
                    int idPlat = rsCommande.getInt("idPlat");  // Récupérer l'ID du plat

                    // Étape 3 : Trouver le prix du plat correspondant dans la table `plat`
                    String sqlPlat = "SELECT prixPlat FROM plat WHERE idPlat = ?";
                    PreparedStatement stmtPlat = conn.prepareStatement(sqlPlat);
                    stmtPlat.setInt(1, idPlat);

                    ResultSet rsPlat = stmtPlat.executeQuery();

                    if (rsPlat.next()) {
                        double prix = rsPlat.getDouble("prixPlat");  // Récupérer le prix du plat
                        somme += prix;  // Ajouter le prix à la somme totale
                    }

                    rsPlat.close();
                    stmtPlat.close();
                }

                rsCommande.close();
                stmtCommande.close();

            } catch (SQLException e) {
                System.err.println("Erreur lors du calcul de la somme de la commande : " + e.getMessage());
            } finally {
                Connecter.closeConnection(conn);
            }
        }

        return somme;  // Retourner la somme totale
    }

    public boolean enregistrerPaiement(int idCommande, String methode) {
        boolean paiementReussi = false;

        // Connexion à la base de données
        Connection conn = Connecter.getConnection();

        if (conn != null) {
            try {
                // Vérifier si l'ID de la commande existe
                String sqlCheckCommande = "SELECT COUNT(*) FROM commande WHERE idCommande = ?";
                PreparedStatement stmtCheckCommande = conn.prepareStatement(sqlCheckCommande);
                stmtCheckCommande.setInt(1, idCommande);
                ResultSet rs = stmtCheckCommande.executeQuery();

                if (rs.next() && rs.getInt(1) == 0) {
                    // La commande n'existe pas, retourner false
                    return false;
                }

                rs.close();
                stmtCheckCommande.close();

                // Étape 1 : Calculer la somme de la commande
                double somme = calculerSommeCommande(idCommande);

                // Récupérer l'heure actuelle pour la date de paiement
                LocalDateTime datePaiement = LocalDateTime.now();

                // Étape 2 : Enregistrer le paiement dans la table `paiment`
                String sqlPaiement = "INSERT INTO paiment (idCommande, datePaiment, prix, methode) VALUES (?, ?, ?, ?)";
                PreparedStatement stmtPaiement = conn.prepareStatement(sqlPaiement);
                stmtPaiement.setInt(1, idCommande);  // ID de la commande
                stmtPaiement.setTimestamp(2, Timestamp.valueOf(datePaiement));  // Date du paiement (actuelle)
                stmtPaiement.setDouble(3, somme);  // Prix (somme calculée)
                stmtPaiement.setString(4, methode);  // Méthode de paiement (espèces ou carte bancaire)

                int rowsAffected = stmtPaiement.executeUpdate();

                if (rowsAffected > 0) {
                    paiementReussi = true;  // Si l'insertion est réussie
                }

                stmtPaiement.close();

                // Étape 3 : Mettre à jour le statut de la commande dans la table `commande`
                String sqlUpdateCommande = "UPDATE commande SET statut = 'Payée' WHERE idCommande = ?";
                PreparedStatement stmtUpdateCommande = conn.prepareStatement(sqlUpdateCommande);
                stmtUpdateCommande.setInt(1, idCommande);
                stmtUpdateCommande.executeUpdate();
                stmtUpdateCommande.close();

            } catch (SQLException e) {
                System.err.println("Erreur lors de l'enregistrement du paiement : " + e.getMessage());
            } finally {
                Connecter.closeConnection(conn);
            }
        }

        return paiementReussi;
    }


    public void genererRecu(int numeroCommande) {
        // Appeler la méthode calculerSommeCommande pour obtenir le total
        double sommeCommande = calculerSommeCommande(numeroCommande);

        // Définir le chemin du répertoire où les reçus doivent être enregistrés
        String cheminRepertoire = "C:/Users/DELL LATITUDE/OneDrive/Bureau/Projet-UML/Recues";

        // Créer un objet File pour le répertoire
        File repertoire = new File(cheminRepertoire);

        // Vérifier si le répertoire existe, sinon le créer
        if (!repertoire.exists()) {
            boolean created = repertoire.mkdirs();  // Crée le répertoire si il n'existe pas
            if (!created) {
                System.err.println("Erreur lors de la création du répertoire.");
                return;  // Arrêter la méthode si le répertoire ne peut pas être créé
            }
        }

        // Créer le nom du fichier de reçu avec le numéro de commande
        String nomFichier = "RecuCommande_" + numeroCommande + ".txt";

        // Créer le fichier de reçu dans le répertoire spécifié
        File fichierRecu = new File(repertoire, nomFichier);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichierRecu))) {

            // Ajouter la date actuelle
            String dateActuelle = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            writer.write("Date : " + dateActuelle);
            writer.newLine();

            // Ajouter les détails de la commande
            writer.write("Commande N° : " + numeroCommande);
            writer.newLine();
            writer.write("Montant total de la commande : " + sommeCommande + " DH");
            writer.newLine();
            writer.write("------------------------------------------------------");
            writer.newLine();
            writer.write("Merci pour votre commande !");
            writer.newLine();

        } catch (IOException e) {
            System.err.println("Erreur lors de la génération du reçu : " + e.getMessage());
        }
    }


}

