package dao;

import model.Automobile;
import model.Leasing;
import model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeasingDAO {

    private Connection connection;

    public LeasingDAO(Connection connection) {
        this.connection = connection;
    }

    // 1. INSERIMENTO
    public void insert(Leasing l) throws SQLException {
        String sql = "INSERT INTO LEASING (idUtente, idAuto, durataMesi, anticipo, kmAnnui, dataRichiesta, stato) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, l.getIdUtente());
            ps.setInt(2, l.getIdAuto());
            ps.setInt(3, l.getDurataMesi());
            ps.setDouble(4, l.getAnticipo());
            ps.setInt(5, l.getKmAnnui());
            ps.setTimestamp(6, l.getDataRichiesta());
            ps.setString(7, l.getStato());
            ps.executeUpdate();
        }
    }

    // 2. LISTA COMPLETA
    public List<Leasing> getAllCompleti() throws SQLException {
        List<Leasing> list = new ArrayList<>();
        String sql = "SELECT l.*, u.nome, u.cognome, u.email, u.telefono, a.marca, a.modello, a.prezzo, a.immagine, a.anno " +
                "FROM LEASING l " +
                "JOIN UTENTE u ON l.idUtente = u.idUtente " +
                "JOIN AUTOMOBILE a ON l.idAuto = a.idAuto " +
                "ORDER BY l.dataRichiesta DESC";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapRowToLeasingCompleto(rs));
            }
        }
        return list;
    }

    // 3. DETTAGLIO SINGOLO (getById)
    public Leasing getById(int id) throws SQLException {
        String sql = "SELECT l.*, u.nome, u.cognome, u.email, u.telefono, a.marca, a.modello, a.prezzo, a.immagine, a.anno " +
                "FROM LEASING l " +
                "JOIN UTENTE u ON l.idUtente = u.idUtente " +
                "JOIN AUTOMOBILE a ON l.idAuto = a.idAuto " +
                "WHERE l.idLeasing = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToLeasingCompleto(rs);
                }
            }
        }
        return null;
    }

    // 4. LISTA UTENTE
    public List<Leasing> getByUser(int idUtente) throws SQLException {
        String sql = "SELECT l.*, u.nome, u.cognome, u.email, u.telefono, a.marca, a.modello, a.prezzo, a.immagine, a.anno " +
                "FROM LEASING l " +
                "JOIN UTENTE u ON l.idUtente = u.idUtente " +
                "JOIN AUTOMOBILE a ON l.idAuto = a.idAuto " +
                "WHERE l.idUtente = ? ORDER BY l.dataRichiesta DESC";

        List<Leasing> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idUtente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToLeasingCompleto(rs));
                }
            }
        }
        return list;
    }

    // 5. UPDATE STATO
    public void updateStato(int idLeasing, String stato) throws SQLException {
        String sql = "UPDATE LEASING SET stato = ? WHERE idLeasing = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, stato);
            ps.setInt(2, idLeasing);
            ps.executeUpdate();
        }
    }

    // --- HELPER MAPPING (Corretto con setUtente) ---
    private Leasing mapRowToLeasingCompleto(ResultSet rs) throws SQLException {
        Leasing l = new Leasing();
        l.setIdLeasing(rs.getInt("idLeasing"));
        l.setIdUtente(rs.getInt("idUtente"));
        l.setIdAuto(rs.getInt("idAuto"));
        l.setDurataMesi(rs.getInt("durataMesi"));
        l.setAnticipo(rs.getDouble("anticipo"));
        l.setKmAnnui(rs.getInt("kmAnnui"));
        l.setDataRichiesta(rs.getTimestamp("dataRichiesta"));
        l.setStato(rs.getString("stato"));

        // Popola Utente
        Utente u = new Utente();
        u.setIdUtente(rs.getInt("idUtente"));
        u.setNome(rs.getString("nome"));
        u.setCognome(rs.getString("cognome"));
        u.setEmail(rs.getString("email"));
        u.setTelefono(rs.getString("telefono")); // Campo nuovo

        // CORREZIONE FONDAMENTALE:
        l.setUtente(u); // Prima era setCliente(u)

        // Popola Auto
        Automobile a = new Automobile();
        a.setIdAuto(rs.getInt("idAuto"));
        a.setMarca(rs.getString("marca"));
        a.setModello(rs.getString("modello"));
        a.setPrezzo(rs.getDouble("prezzo"));
        a.setImmagine(rs.getString("immagine")); // Campo nuovo
        a.setAnno(rs.getInt("anno"));            // Campo nuovo
        l.setAuto(a);

        return l;
    }
}