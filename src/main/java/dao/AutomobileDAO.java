package dao;

import model.Automobile;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutomobileDAO {

    private Connection connection;

    // Costruttore: riceve la connessione dal Singleton o dal chiamante
    public AutomobileDAO(Connection connection) {
        this.connection = connection;
    }

    // --------------------------------------------------------
    // OPERAZIONI CRUD BASE (Create, Read, Update, Delete)
    // --------------------------------------------------------

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

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM AUTOMOBILE WHERE idAuto = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // --------------------------------------------------------
    // RICERCA E FILTRI
    // --------------------------------------------------------

    /**
     * Ricerca semplice (Legacy) per Marca e Prezzo Massimo.
     */
    public List<Automobile> cercaAuto(String marca, double prezzoMax) throws SQLException {
        List<Automobile> list = new ArrayList<>();
        String sql = "SELECT * FROM AUTOMOBILE WHERE prezzo <= ?";

        if (marca != null && !marca.isEmpty()) {
            sql += " AND marca LIKE ?";
        }

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, prezzoMax);
            if (marca != null && !marca.isEmpty()) {
                ps.setString(2, "%" + marca + "%");
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRowToAutomobile(rs));
                }
            }
        }
        return list;
    }

    /**
     * NUOVO METODO: Ricerca Avanzata con tutti i filtri del catalogo.
     * Gestisce Marca, Prezzo, Anno, Km, Stato e Disponibilità.
     * I parametri null vengono ignorati.
     */
    public List<Automobile> ricercaAvanzata(String marca, Double prezzoMax, Integer anno, Integer kmMax, String stato, Boolean disponibilita) throws SQLException {
        List<Automobile> list = new ArrayList<>();

        // Costruiamo la query dinamicamente
        // "WHERE 1=1" è un trucco SQL per poter aggiungere tutti gli altri filtri con "AND ..." senza preoccuparsi se è il primo o no.
        StringBuilder sql = new StringBuilder("SELECT * FROM AUTOMOBILE WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // Filtro Marca (Like)
        if (marca != null && !marca.trim().isEmpty()) {
            sql.append(" AND marca LIKE ?");
            params.add("%" + marca + "%");
        }

        // Filtro Prezzo Max
        if (prezzoMax != null) {
            sql.append(" AND prezzo <= ?");
            params.add(prezzoMax);
        }

        // Filtro Anno Esatto
        if (anno != null) {
            sql.append(" AND anno = ?");
            params.add(anno);
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

        // Esecuzione Query
        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            // Imposta i parametri nell'ordine corretto
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

    // --------------------------------------------------------
    // HELPER METHODS
    // --------------------------------------------------------

    private Automobile mapRowToAutomobile(ResultSet rs) throws SQLException {
        // Attenzione: l'ordine dei parametri deve corrispondere al costruttore del tuo model Automobile
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