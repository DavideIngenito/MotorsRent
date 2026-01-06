package dao;

import model.Automobile;
import model.Preventivo;
import model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreventivoDAO {

    private Connection connection;

    /**
     * @param connection Connessione al database
     */
    public PreventivoDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * @param p Preventivo da inserire
     * @throws SQLException Se si verifica un errore durante l'inserimento
     */
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

    /**
     * @return Lista di preventivi completi
     * @throws SQLException Se si verifica un errore durante l'accesso al database
     */
    public List<Preventivo> getAllCompleti() throws SQLException {
        List<Preventivo> list = new ArrayList<>();

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

    /**
     * @param idUtente id dell'utente
     * @return Lista di preventivi dell'utente
     * @throws SQLException Se si verifica un errore durante l'accesso al database
     */
    public List<Preventivo> getByUser(int idUtente) throws SQLException {
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


    /**
     * @param id ID del preventivo
     * @return Preventivo completo, oppure null se non esiste
     * @throws SQLException Se si verifica un errore durante l'accesso al database
     */
    public Preventivo getById(int id) throws SQLException {
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

    /**
     * @param id        ID del preventivo
     * @param stato     Nuovo stato del preventivo
     * @param importo   Prezzo proposto dal venditore
     * @param messaggio Messaggio del venditore
     * @throws SQLException Se si verifica un errore durante l'accesso al database
     */

    public void gestisciRisposta(int id, String stato, double importo, String messaggio) throws SQLException {

        String sql = "UPDATE PREVENTIVO SET stato = ?, prezzoProposto = ?, rispostaVenditore = ? WHERE idPreventivo = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, stato);
            ps.setDouble(2, importo);
            ps.setString(3, messaggio);
            ps.setInt(4, id);
            ps.executeUpdate();
        }
    }


    /**
     * @param rs ResultSet corrente
     * @return Preventivo completo
     * @throws SQLException Se si verifica un errore nell'accesso ai dati
     */

    private Preventivo mapRowToPreventivoCompleto(ResultSet rs) throws SQLException {
        Preventivo p = new Preventivo();
        p.setIdPreventivo(rs.getInt("idPreventivo"));
        p.setIdUtente(rs.getInt("idUtente"));
        p.setIdAuto(rs.getInt("idAuto"));
        p.setDataRichiesta(rs.getTimestamp("dataRichiesta"));
        p.setStato(rs.getString("stato"));
        p.setNote(rs.getString("note"));


        try {
            p.setPrezzoProposto(rs.getDouble("prezzoProposto"));
        } catch (SQLException e) {
            p.setPrezzoProposto(0.0);
        }


        try {
            p.setMessaggioVenditore(rs.getString("rispostaVenditore"));
        } catch (SQLException e) {
            p.setMessaggioVenditore("");
        }

        // Utente e Auto
        Utente u = new Utente();
        u.setIdUtente(rs.getInt("idUtente"));
        u.setNome(rs.getString("nome"));
        u.setCognome(rs.getString("cognome"));
        u.setEmail(rs.getString("email"));
        u.setTelefono(rs.getString("telefono"));
        p.setUtente(u);

        Automobile a = new Automobile();
        a.setIdAuto(rs.getInt("idAuto"));
        a.setMarca(rs.getString("marca"));
        a.setModello(rs.getString("modello"));
        a.setPrezzo(rs.getDouble("prezzo"));
        a.setImmagine(rs.getString("immagine"));
        a.setAnno(rs.getInt("anno"));
        p.setAuto(a);

        return p;
    }
}