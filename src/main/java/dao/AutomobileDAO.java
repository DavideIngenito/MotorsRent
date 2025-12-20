package dao;

import model.Automobile;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutomobileDAO {

    private Connection connection;

    // Costruttore: riceve la connessione dal Singleton
    public AutomobileDAO(Connection connection) {
        this.connection = connection;
    }

    // =================================================================================
    //  SEZIONE 1: OPERAZIONI CRUD STANDARD (Create, Read, Update, Delete)
    // =================================================================================

    /**
     * Inserisce una nuova auto nel database.
     */
    public void insert(Automobile a) throws SQLException {
        String sql = "INSERT INTO AUTOMOBILE (modello, marca, anno, prezzo, chilometraggio, stato, descrizione, immagine, disponibilita) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, a.getModello());
            ps.setString(2, a.getMarca());
            ps.setInt(3, a.getAnno());
            ps.setDouble(4, a.getPrezzo());
            ps.setInt(5, a.getChilometraggio());
            ps.setString(6, a.getStato());
            ps.setString(7, a.getDescrizione());
            ps.setString(8, a.getImmagine());
            ps.setBoolean(9, a.getDisponibilita());
            ps.executeUpdate();
        }
    }

    /**
     * Recupera un'auto tramite il suo ID.
     */
    public Automobile getById(int id) throws SQLException {
        String sql = "SELECT * FROM AUTOMOBILE WHERE idAuto = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToAutomobile(rs);
                }
            }
        }
        return null;
    }

    /**
     * Recupera tutte le auto presenti nel database (senza filtri).
     */
    public List<Automobile> getAll() throws SQLException {
        List<Automobile> list = new ArrayList<>();
        String sql = "SELECT * FROM AUTOMOBILE";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapRowToAutomobile(rs));
            }
        }
        return list;
    }

    /**
     * Aggiorna i dati di un'auto esistente.
     */
    public void update(Automobile a) throws SQLException {
        String sql = "UPDATE AUTOMOBILE SET modello=?, marca=?, anno=?, prezzo=?, chilometraggio=?, stato=?, descrizione=?, immagine=?, disponibilita=? WHERE idAuto=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, a.getModello());
            ps.setString(2, a.getMarca());
            ps.setInt(3, a.getAnno());
            ps.setDouble(4, a.getPrezzo());
            ps.setInt(5, a.getChilometraggio());
            ps.setString(6, a.getStato());
            ps.setString(7, a.getDescrizione());
            ps.setString(8, a.getImmagine());
            ps.setBoolean(9, a.getDisponibilita());
            ps.setInt(10, a.getIdAuto());

            ps.executeUpdate();
        }
    }

    /**
     * Elimina un'auto dal database tramite ID.
     */
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM AUTOMOBILE WHERE idAuto = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // =================================================================================
    //  SEZIONE 2: RICERCA AVANZATA E FILTRI
    // =================================================================================

    /**
     * Metodo flessibile per filtrare le auto nel catalogo.
     * Gestisce filtri multipli (es. più anni selezionati) e opzionali (null se non selezionati).
     * * @param marca        Testo parziale per la marca (LIKE)
     * @param prezzoMax    Prezzo massimo
     * @param anni         Lista di anni da includere (es. 2023 e 2024)
     * @param kmMax        Chilometraggio massimo
     * @param stato        "Nuova" o "Usata"
     * @param disponibilita True per vedere solo quelle disponibili
     */
    public List<Automobile> ricercaAvanzata(String marca, Double prezzoMax, List<Integer> anni, Integer kmMax, String stato, Boolean disponibilita) throws SQLException {
        List<Automobile> list = new ArrayList<>();

        // 1. Costruzione Query Dinamica
        // Usiamo "WHERE 1=1" per poter aggiungere tutti gli "AND" successivi senza preoccuparci se è il primo o no.
        StringBuilder sql = new StringBuilder("SELECT * FROM AUTOMOBILE WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // Filtro Marca
        if (marca != null && !marca.trim().isEmpty()) {
            sql.append(" AND marca LIKE ?");
            params.add("%" + marca + "%");
        }

        // Filtro Prezzo Max
        if (prezzoMax != null) {
            sql.append(" AND prezzo <= ?");
            params.add(prezzoMax);
        }

        // Filtro Anni Multipli (Gestione complessa per la clausola IN)
        if (anni != null && !anni.isEmpty()) {
            sql.append(" AND anno IN (");
            for (int i = 0; i < anni.size(); i++) {
                sql.append("?"); // Aggiunge un punto interrogativo per ogni anno
                if (i < anni.size() - 1) {
                    sql.append(","); // Aggiunge la virgola se non è l'ultimo
                }
                params.add(anni.get(i)); // Salva il valore del parametro
            }
            sql.append(")");
        }

        // Filtro Chilometraggio Max
        if (kmMax != null) {
            sql.append(" AND chilometraggio <= ?");
            params.add(kmMax);
        }

        // Filtro Stato (Nuova/Usata)
        if (stato != null && !stato.trim().isEmpty()) {
            sql.append(" AND stato = ?");
            params.add(stato);
        }

        // Filtro Disponibilità
        if (disponibilita != null && disponibilita) {
            sql.append(" AND disponibilita = ?");
            params.add(true);
        }

        // 2. Esecuzione Query
        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {

            // Impostiamo i parametri nel PreparedStatement nell'ordine in cui li abbiamo aggiunti alla lista
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToAutomobile(rs));
                }
            }
        }
        return list;
    }

    // =================================================================================
    //  SEZIONE 3: METODI DI SUPPORTO (HELPER)
    // =================================================================================

    /**
     * Metodo privato per mappare una riga del ResultSet in un oggetto Automobile.
     * Evita di ripetere questo codice in ogni metodo query.
     */
    private Automobile mapRowToAutomobile(ResultSet rs) throws SQLException {
        return new Automobile(
                rs.getInt("idAuto"),
                rs.getString("marca"),
                rs.getString("modello"),
                rs.getInt("anno"),
                rs.getDouble("prezzo"),
                rs.getInt("chilometraggio"),
                rs.getString("stato"),
                rs.getString("descrizione"),
                rs.getString("immagine"),
                rs.getBoolean("disponibilita")
        );
    }
}