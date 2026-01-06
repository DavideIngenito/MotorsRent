package dao;

import model.Automobile;
import model.Leasing;
import model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeasingDAO {

    private Connection connection;

    /**
     *
     * @param connection Connessione al database
     */
    public LeasingDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     *
     *@param l Leasing da inserire
      * @throws SQLException Se si verifica un errore durante l'inserimento
     */
    public void insert(Leasing l) throws SQLException {

        String sql = "INSERT INTO LEASING (idUtente, idAuto, durataMesi, anticipo, kmAnnui, dataRichiesta, stato, note) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, l.getIdUtente());
            ps.setInt(2, l.getIdAuto());
            ps.setInt(3, l.getDurataMesi());
            ps.setDouble(4, l.getAnticipo());
            ps.setInt(5, l.getKmAnnui());
            ps.setTimestamp(6, l.getDataRichiesta());
            ps.setString(7, l.getStato());
            ps.setString(8, l.getNote()); // <-- ECCO IL PEZZO MANCANTE
            ps.executeUpdate();
        }
    }

    /**
     *
     * @return Lista di leasing completi
     * @throws SQLException Se si verifica un errore durante l'accesso al database
     */
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

    /**
     *
     * @param id ID del leasing
     * @return Leasing completo, oppure null se non esiste
     * @throws SQLException Se si verifica un errore durante l'accesso al database
     */
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

    /**
     *
     * @param idUtente ID dell'utente
     * @return Lista di leasing dell'utente
     * @throws SQLException Se si verifica un errore durante l'accesso al database
     */
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

    /**
     *
     * @param id ID del leasing
     * @param stato Nuovo stato del leasing
     * @param rata Rata mensile proposta dal venditore
     * @param messaggio Messaggio del venditore
     * @throws SQLException Se si verifica un errore durante l'aggiornamento
     */
    public void gestisciRisposta(int id, String stato, double rata, String messaggio) throws SQLException {
        String sql = "UPDATE LEASING SET stato = ?, rataMensile = ?, rispostaVenditore = ? WHERE idLeasing = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, stato);
            ps.setDouble(2, rata);
            ps.setString(3, messaggio);
            ps.setInt(4, id);
            ps.executeUpdate();
        }
    }

    /**
     *
     * @param rs ResultSet corrente
     * @return Leasing completo
     * @throws SQLException Se si verifica un errore nell'accesso ai dati
     */
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

        // Lettura Note Cliente
        try { l.setNote(rs.getString("note")); } catch (SQLException e) { l.setNote(""); }

        // Lettura Risposta Venditore
        try { l.setRataMensile(rs.getDouble("rataMensile")); } catch (SQLException e) { l.setRataMensile(0.0); }
        try { l.setMessaggioVenditore(rs.getString("rispostaVenditore")); } catch (SQLException e) { l.setMessaggioVenditore(""); }

        // Utente
        Utente u = new Utente();
        u.setIdUtente(rs.getInt("idUtente"));
        u.setNome(rs.getString("nome"));
        u.setCognome(rs.getString("cognome"));
        u.setEmail(rs.getString("email"));
        u.setTelefono(rs.getString("telefono"));
        l.setUtente(u);

        // Auto
        Automobile a = new Automobile();
        a.setIdAuto(rs.getInt("idAuto"));
        a.setMarca(rs.getString("marca"));
        a.setModello(rs.getString("modello"));
        a.setPrezzo(rs.getDouble("prezzo"));
        a.setImmagine(rs.getString("immagine"));
        a.setAnno(rs.getInt("anno"));
        l.setAuto(a);

        return l;
    }
}