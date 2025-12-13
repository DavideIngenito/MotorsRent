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

    // Inserimento Richiesta (Lato Cliente)
    public void insert(Leasing l) throws SQLException {
        String sql = "INSERT INTO LEASING (idUtente, idAuto, durataMesi, anticipo, kmAnnui, dataLeasing, stato) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, l.getIdUtente());
            ps.setInt(2, l.getIdAuto());
            ps.setInt(3, l.getDurataMesi());
            ps.setDouble(4, l.getAnticipo());
            ps.setInt(5, l.getKmAnnui());
            ps.setTimestamp(6, l.getDataLeasing()); // Usa setTimestamp, non setString!
            ps.setString(7, l.getStato());
            ps.executeUpdate();
        }
    }

    // Lista per il Venditore (Dashboard) con JOIN
    public List<Leasing> getAllCompleti() throws SQLException {
        List<Leasing> list = new ArrayList<>();

        String sql = "SELECT l.*, u.nome, u.cognome, u.email, a.marca, a.modello, a.prezzo " +
                "FROM LEASING l " +
                "JOIN UTENTE u ON l.idUtente = u.idUtente " +
                "JOIN AUTO a ON l.idAuto = a.idAuto " +
                "ORDER BY l.dataLeasing DESC";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapRowToLeasingCompleto(rs));
            }
        }
        return list;
    }

    // Dettaglio singolo per Venditore
    public Leasing getByIdCompleto(int id) throws SQLException {
        String sql = "SELECT l.*, u.nome, u.cognome, u.email, a.marca, a.modello, a.prezzo " +
                "FROM LEASING l " +
                "JOIN UTENTE u ON l.idUtente = u.idUtente " +
                "JOIN AUTO a ON l.idAuto = a.idAuto " +
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

    // Lista per il Cliente (Area Personale)
    public List<Leasing> getByUser(int idUtente) throws SQLException {
        String sql = "SELECT l.*, u.nome, u.cognome, u.email, a.marca, a.modello, a.prezzo " +
                "FROM LEASING l " +
                "JOIN UTENTE u ON l.idUtente = u.idUtente " +
                "JOIN AUTO a ON l.idAuto = a.idAuto " +
                "WHERE l.idUtente = ? ORDER BY l.dataLeasing DESC";

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

    public void updateStato(int idLeasing, String stato) throws SQLException {
        String sql = "UPDATE LEASING SET stato = ? WHERE idLeasing = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, stato);
            ps.setInt(2, idLeasing);
            ps.executeUpdate();
        }
    }

    // Helper per mapping (DRY)
    private Leasing mapRowToLeasingCompleto(ResultSet rs) throws SQLException {
        Leasing l = new Leasing();
        l.setIdLeasing(rs.getInt("idLeasing"));
        l.setIdUtente(rs.getInt("idUtente"));
        l.setIdAuto(rs.getInt("idAuto"));
        l.setDurataMesi(rs.getInt("durataMesi"));
        l.setAnticipo(rs.getDouble("anticipo")); // Occhio: Double, non Int
        l.setKmAnnui(rs.getInt("kmAnnui"));
        l.setDataLeasing(rs.getTimestamp("dataLeasing"));
        l.setStato(rs.getString("stato"));

        // Popola Cliente
        Utente u = new Utente();
        u.setNome(rs.getString("nome"));
        u.setCognome(rs.getString("cognome"));
        u.setEmail(rs.getString("email"));
        l.setCliente(u);

        // Popola Auto
        Automobile a = new Automobile();
        a.setMarca(rs.getString("marca"));
        a.setModello(rs.getString("modello"));
        a.setPrezzo(rs.getDouble("prezzo"));
        l.setAuto(a);

        return l;
    }
}