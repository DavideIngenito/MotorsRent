package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/motorsrent";
    private static final String USER = "root";
    private static final String PASSWORD = "Lucabomber10";

    private static Connection connection = null;

    // Costruttore privato → Singleton
    private DbConnection() {}

    // Restituisce una connessione attiva
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Chiude la connessione
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

