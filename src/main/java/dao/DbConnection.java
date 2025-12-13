package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    // 1. L'unica istanza condivisa (Singleton)
    private static DbConnection instance = null;

    // 2. La connessione al Database (attributo dell'istanza)
    private Connection connection = null;

    // Credenziali
    private static final String URL = "jdbc:mysql://localhost:3306/motorsrent";
    private static final String USER = "root";
    private static final String PASSWORD = "Lucabomber10";

    // 3. Costruttore Privato: Carica il driver e apre la connessione
    private DbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Errore connessione DB: " + e.getMessage());
        }
    }

    // 4. Metodo Statico per ottenere l'istanza (Pattern Singleton)
    public static DbConnection getInstance() {
        try {
            // Se l'istanza non esiste, oppure la connessione interna è chiusa/nulla...
            if (instance == null || instance.getConnection().isClosed()) {
                // ...creiamo una nuova istanza (che a sua volta apre la connessione nel costruttore)
                instance = new DbConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instance;
    }

    // 5. Metodo pubblico per recuperare l'oggetto Connection
    public Connection getConnection() {
        return this.connection;
    }
}