package Restaurant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connecter {


    private static final String URL = "jdbc:mysql://localhost:3307/restaurant";  // Remplacez testdb par votre base de données
    private static final String USER = "root";  // Utilisateur MySQL par défaut dans XAMPP : root
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
            return null;
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}

