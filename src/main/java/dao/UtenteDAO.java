package dao;

import model.Utente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {

    private Connection connection;

    // Costruttore che riceve il Singleton
    public UtenteDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Inserisce un nuovo utente (Usato per Registrazione e creazione Venditori)
     */
    public void insert(Utente u) throws SQLException {
        String sql = "INSERT INTO UTENTE (nome, cognome, email, password, telefono, ruolo) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getCognome());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPassword());
            ps.setString(5, u.getTelefono());
            ps.setString(6, u.getRuolo()); // "CLIENTE", "VENDITORE", "ADMIN"
            ps.executeUpdate();
        }
    }

    /**
     * Verifica email e password (Usato per il Login)
     */
    public Utente checkLogin(String email, String password) throws SQLException {
        String sql = "SELECT * FROM UTENTE WHERE email = ? AND password = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUtente(rs);
                }
            }
        }
        return null;
    }

    /**
     * Trova tutti gli utenti che hanno ruolo "VENDITORE"
     * (QUESTO È IL METODO CHE TI MANCAVA!)
     */
    public List<Utente> findVenditori() throws SQLException {
        List<Utente> venditori = new ArrayList<>();
        // Assicurati che nel DB il ruolo sia scritto come 'VENDITORE' (maiuscolo/minuscolo)
        String sql = "SELECT * FROM UTENTE WHERE ruolo = 'VENDITORE'";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                venditori.add(mapRowToUtente(rs));
            }
        }
        return venditori;
    }

    /**
     * Elimina un utente per ID (Usato dall'Admin)
     */
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM UTENTE WHERE idUtente = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // --- Helper privato per mappare i risultati ---
    private Utente mapRowToUtente(ResultSet rs) throws SQLException {
        return new Utente(
                rs.getInt("idUtente"),
                rs.getString("nome"),
                rs.getString("cognome"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("telefono"),
                rs.getString("ruolo")
        );
    }

    // (Opzionale) Metodo getAll generico se ti serve altrove
    public List<Utente> getAll() throws SQLException {
        List<Utente> utenti = new ArrayList<>();
        String sql = "SELECT * FROM UTENTE";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                utenti.add(mapRowToUtente(rs));
            }
        }
        return utenti;
    }
}