package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/motorsrent?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";  // <-- metti il tuo username
    private static final String PASSWORD = "password"; // <-- metti la tua password

    private static Connection connection = null;

    // Costruttore privato → Singleton
    private DBConnection() {}

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

