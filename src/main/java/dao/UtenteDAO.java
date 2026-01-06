package dao;

import model.Utente;
import model.UtenteFactory; // Import necessario
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {

    private Connection connection;

    public UtenteDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Utente u) throws SQLException {
        String sql = "INSERT INTO UTENTE (nome, cognome, email, password, telefono, ruolo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getCognome());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPassword());
            ps.setString(5, u.getTelefono());
            ps.setString(6, u.getRuolo());
            ps.executeUpdate();
        }
    }

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

    public List<Utente> findVenditori() throws SQLException {
        List<Utente> venditori = new ArrayList<>();
        String sql = "SELECT * FROM UTENTE WHERE ruolo = 'VENDITORE'";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                venditori.add(mapRowToUtente(rs));
            }
        }
        return venditori;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM UTENTE WHERE idUtente = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

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

    public boolean updateProfilo(Utente u) throws SQLException {
        String sql = "UPDATE UTENTE SET nome = ?, cognome = ?, email = ?, telefono = ?, password = ? WHERE idUtente = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getCognome());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getTelefono());
            ps.setString(5, u.getPassword());
            ps.setInt(6, u.getIdUtente());
            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    // --- INTEGRAZIONE FACTORY METHOD QUI ---
    private Utente mapRowToUtente(ResultSet rs) throws SQLException {
        // Delega la creazione alla Factory invece di usare new Utente(...)
        return UtenteFactory.createUtente(
                rs.getInt("idUtente"),
                rs.getString("nome"),
                rs.getString("cognome"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("telefono"),
                rs.getString("ruolo")
        );
    }
}