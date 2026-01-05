package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    // L'unica istanza condivisa
    private static DbConnection instance = null;

    private Connection connection = null;

    private static final String URL = "jdbc:mysql://localhost:3306/motorsrent";
    private static final String USER = "root";
    private static final String PASSWORD = "Lucabomber10";

    private DbConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Errore connessione DB: " + e.getMessage());
        }
    }

    // Pattern Singleton

    /**
     * @return L'istanza condivisa di DbConnection
     */
    public static DbConnection getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new DbConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }
}