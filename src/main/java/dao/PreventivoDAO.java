package dao;

import model.Automobile;
import model.Preventivo;
import model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreventivoDAO {

    private Connection connection;

    // Costruttore: riceve la connessione dal Singleton
    public PreventivoDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * 1. INSERIMENTO (Lato Cliente)
     * Salva una nuova richiesta nel database.
     */
    public void insert(Preventivo p) throws SQLException {
        String sql = "INSERT INTO PREVENTIVO (idUtente, idAuto, dataPreventivo, note, stato) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, p.getIdUtente());
            ps.setInt(2, p.getIdAuto());
            ps.setTimestamp(3, p.getDataPreventivo());
            ps.setString(4, p.getNote());
            ps.setString(5, p.getStato()); // Es. "NUOVA"
            ps.executeUpdate();
        }
    }

    /**
     * 2. LISTA COMPLETA PER VENDITORE (Dashboard & Lista)
     * Recupera TUTTI i preventivi facendo una JOIN per avere i nomi di Clienti e Auto.
     * Risolve l'errore "cannot resolve method getAllCompleti".
     */
    public List<Preventivo> getAllCompleti() throws SQLException {
        List<Preventivo> list = new ArrayList<>();

        // JOIN fondamentale per mostrare "Mario Rossi" e "Fiat Panda" invece di ID numerici
        String sql = "SELECT p.*, u.nome, u.cognome, u.email, a.marca, a.modello, a.prezzo " +
                "FROM PREVENTIVO p " +
                "JOIN UTENTE u ON p.idUtente = u.idUtente " +
                "JOIN AUTO a ON p.idAuto = a.idAuto " +
                "ORDER BY p.dataPreventivo DESC";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapRowToPreventivoCompleto(rs));
            }
        }
        return list;
    }

    /**
     * 3. LISTA PER UTENTE SINGOLO (Dashboard Cliente)
     * Recupera solo i preventivi di uno specifico utente (con dettagli auto).
     */
    public List<Preventivo> getByUser(int idUtente) throws SQLException {
        String sql = "SELECT p.*, u.nome, u.cognome, u.email, a.marca, a.modello, a.prezzo " +
                "FROM PREVENTIVO p " +
                "JOIN UTENTE u ON p.idUtente = u.idUtente " +
                "JOIN AUTO a ON p.idAuto = a.idAuto " +
                "WHERE p.idUtente = ? " +
                "ORDER BY p.dataPreventivo DESC";

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
     * 4. DETTAGLIO SINGOLO (Gestione Venditore)
     * Recupera un singolo preventivo completo dato il suo ID.
     */
    public Preventivo getByIdCompleto(int id) throws SQLException {
        String sql = "SELECT p.*, u.nome, u.cognome, u.email, a.marca, a.modello, a.prezzo " +
                "FROM PREVENTIVO p " +
                "JOIN UTENTE u ON p.idUtente = u.idUtente " +
                "JOIN AUTO a ON p.idAuto = a.idAuto " +
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
     * 5. AGGIORNAMENTO STATO (Azione Venditore)
     * Cambia lo stato (es. da "NUOVA" a "INVIATO").
     */
    public void updateStato(int idPreventivo, String nuovoStato) throws SQLException {
        String sql = "UPDATE PREVENTIVO SET stato = ? WHERE idPreventivo = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nuovoStato);
            ps.setInt(2, idPreventivo);
            ps.executeUpdate();
        }
    }

    // --- METODI PRIVATI DI SUPPORTO (HELPER) ---

    /**
     * Mappa una riga del database in un oggetto Preventivo popolando anche
     * gli oggetti annidati Utente (cliente) e Automobile (auto).
     */
    private Preventivo mapRowToPreventivoCompleto(ResultSet rs) throws SQLException {
        Preventivo p = new Preventivo();

        // Dati diretti del preventivo
        p.setIdPreventivo(rs.getInt("idPreventivo"));
        p.setIdUtente(rs.getInt("idUtente"));
        p.setIdAuto(rs.getInt("idAuto"));
        p.setDataPreventivo(rs.getTimestamp("dataPreventivo"));
        p.setNote(rs.getString("note"));
        p.setStato(rs.getString("stato"));

        // Dati Utente (da JOIN)
        Utente u = new Utente();
        u.setIdUtente(rs.getInt("idUtente"));
        u.setNome(rs.getString("nome"));
        u.setCognome(rs.getString("cognome"));
        u.setEmail(rs.getString("email"));
        p.setCliente(u); // Popola l'oggetto annidato

        // Dati Auto (da JOIN)
        Automobile a = new Automobile();
        a.setIdAuto(rs.getInt("idAuto"));
        a.setMarca(rs.getString("marca"));
        a.setModello(rs.getString("modello"));
        a.setPrezzo(rs.getDouble("prezzo"));
        p.setAuto(a); // Popola l'oggetto annidato

        return p;
    }
}