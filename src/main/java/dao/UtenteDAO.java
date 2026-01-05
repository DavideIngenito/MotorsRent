package dao;

import model.Utente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {

    private Connection connection;

    // Costruttore che riceve il Singleton

    /**
     *@param connection Connessione al database
     */
    public UtenteDAO(Connection connection) {
        this.connection = connection;
    }

    /**
      * @param u Utente da inserire
      * @throws SQLException Se si verifica un errore durante l'inserimento
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
     * @param email Email dell'utente
     * @param password Password dell'utente
     * @return Utente corrispondente se login corretto, altrimenti null
     * @throws SQLException Se si verifica un errore durante l'accesso al database
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
     * @return Lista di utenti con ruolo "VENDITORE"
     * @throws SQLException Se si verifica un errore durante l'accesso al database
     */
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

    /**
     * @param id ID dell'utente da eliminare
     * @throws SQLException Se si verifica un errore durante l'eliminazione
     */
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM UTENTE WHERE idUtente = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /**
     * @return Lista di tutti gli utenti
     * @throws SQLException Se si verifica un errore durante l'accesso al database
     */
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

    /**
     * @param u Utente con dati aggiornati
     * @return true se l'aggiornamento ha avuto successo, false altrimenti
     * @throws SQLException Se si verifica un errore durante l'aggiornamento
     */
    public boolean updateProfilo(Utente u) throws SQLException {
        // Aggiorniamo anche la password
        String sql = "UPDATE UTENTE SET nome = ?, cognome = ?, email = ?, telefono = ?, password = ? WHERE idUtente = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getCognome());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getTelefono());
            ps.setString(5, u.getPassword()); // Importante per il cambio password
            ps.setInt(6, u.getIdUtente());

            int rows = ps.executeUpdate();
            return rows > 0;
        }
    }

    /**
     * @param rs ResultSet corrente
     * @return Utente corrispondente alla riga
     * @throws SQLException Se si verifica un errore nell'accesso ai dati
     */
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
}