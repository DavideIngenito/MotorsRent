package dao;

import model.Automobile;
import model.Preventivo;
import model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreventivoDAO {

    private Connection connection;

    public PreventivoDAO(Connection connection) {
        this.connection = connection;
    }

    // 1. INSERIMENTO (Lato Cliente)
    public void insert(Preventivo p) throws SQLException {
        String sql = "INSERT INTO PREVENTIVO (idUtente, idAuto, dataRichiesta, note, stato) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, p.getIdUtente());
            ps.setInt(2, p.getIdAuto());
            ps.setTimestamp(3, p.getDataRichiesta());
            ps.setString(4, p.getNote());
            ps.setString(5, p.getStato());
            ps.executeUpdate();
        }
    }

    // 2. LISTA COMPLETA (Dashboard Admin/Venditore)
    public List<Preventivo> getAllCompleti() throws SQLException {
        List<Preventivo> list = new ArrayList<>();
        // Aggiunti campi mancanti: a.immagine, a.anno, u.telefono
        String sql = "SELECT p.*, u.nome, u.cognome, u.email, u.telefono, a.marca, a.modello, a.prezzo, a.immagine, a.anno " +
                "FROM PREVENTIVO p " +
                "JOIN UTENTE u ON p.idUtente = u.idUtente " +
                "JOIN AUTOMOBILE a ON p.idAuto = a.idAuto " +
                "ORDER BY p.dataRichiesta DESC";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapRowToPreventivoCompleto(rs));
            }
        }
        return list;
    }

    // 3. LISTA UTENTE (Dashboard Cliente)
    public List<Preventivo> getByUser(int idUtente) throws SQLException {
        // Aggiunti campi mancanti
        String sql = "SELECT p.*, u.nome, u.cognome, u.email, u.telefono, a.marca, a.modello, a.prezzo, a.immagine, a.anno " +
                "FROM PREVENTIVO p " +
                "JOIN UTENTE u ON p.idUtente = u.idUtente " +
                "JOIN AUTOMOBILE a ON p.idAuto = a.idAuto " +
                "WHERE p.idUtente = ? " +
                "ORDER BY p.dataRichiesta DESC";

        List<Preventivo> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idUtente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToPreventivoCompleto(rs));
                }
            }
        }
        return list;
    }

    // 4. DETTAGLIO SINGOLO (Per la Servlet DettaglioRichiesta)
    // Rinominato in getById per compatibilità con la Servlet
    public Preventivo getById(int id) throws SQLException {
        // Aggiunti campi mancanti
        String sql = "SELECT p.*, u.nome, u.cognome, u.email, u.telefono, a.marca, a.modello, a.prezzo, a.immagine, a.anno " +
                "FROM PREVENTIVO p " +
                "JOIN UTENTE u ON p.idUtente = u.idUtente " +
                "JOIN AUTOMOBILE a ON p.idAuto = a.idAuto " +
                "WHERE p.idPreventivo = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToPreventivoCompleto(rs);
                }
            }
        }
        return null;
    }

    // 5. UPDATE STATO
    public void updateStato(int idPreventivo, String nuovoStato) throws SQLException {
        String sql = "UPDATE PREVENTIVO SET stato = ? WHERE idPreventivo = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nuovoStato);
            ps.setInt(2, idPreventivo);
            ps.executeUpdate();
        }
    }

    // HELPER MAPPING (Popola Utente e Auto dentro Preventivo)
    private Preventivo mapRowToPreventivoCompleto(ResultSet rs) throws SQLException {
        Preventivo p = new Preventivo();
        p.setIdPreventivo(rs.getInt("idPreventivo"));
        p.setIdUtente(rs.getInt("idUtente"));
        p.setIdAuto(rs.getInt("idAuto"));
        p.setDataRichiesta(rs.getTimestamp("dataRichiesta"));
        p.setNote(rs.getString("note"));
        p.setStato(rs.getString("stato"));

        // Popola Utente
        Utente u = new Utente();
        u.setIdUtente(rs.getInt("idUtente"));
        u.setNome(rs.getString("nome"));
        u.setCognome(rs.getString("cognome"));
        u.setEmail(rs.getString("email"));
        u.setTelefono(rs.getString("telefono")); // Aggiunto
        p.setCliente(u);

        // Popola Auto
        Automobile a = new Automobile();
        a.setIdAuto(rs.getInt("idAuto"));
        a.setMarca(rs.getString("marca"));
        a.setModello(rs.getString("modello"));
        a.setPrezzo(rs.getDouble("prezzo"));
        a.setImmagine(rs.getString("immagine")); // Fondamentale per il dettaglio
        a.setAnno(rs.getInt("anno"));            // Fondamentale per il dettaglio
        p.setAuto(a);

        return p;
    }
}