package dao;

import model.Automobile;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutomobileDAO {

    private Connection connection;

    // Costruttore
    public AutomobileDAO(Connection connection) {
        this.connection = connection;
    }

    // --- OPERAZIONI CRUD STANDARD ---

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

    // --- RICERCA AVANZATA (FILTRI) ---

    /**
     * Metodo per filtrare le auto.
     * anni: Lista di interi (es. [2023, 2024]) per filtrare con "IN".
     */
    public List<Automobile> ricercaAvanzata(String marca, Double prezzoMax, List<Integer> anni, Integer kmMax, String stato, Boolean disponibilita) throws SQLException {
        List<Automobile> list = new ArrayList<>();

        // Costruiamo la query dinamicamente.
        // WHERE 1=1 serve per poter aggiungere tutti gli "AND" successivi senza problemi.
        StringBuilder sql = new StringBuilder("SELECT * FROM AUTOMOBILE WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // 1. Filtro Marca
        if (marca != null && !marca.trim().isEmpty()) {
            sql.append(" AND marca LIKE ?");
            params.add("%" + marca + "%");
        }

        // 2. Filtro Prezzo
        if (prezzoMax != null) {
            sql.append(" AND prezzo <= ?");
            params.add(prezzoMax);
        }

        // 3. Filtro Anni Multipli (IN Clause)
        if (anni != null && !anni.isEmpty()) {
            sql.append(" AND anno IN (");
            for (int i = 0; i < anni.size(); i++) {
                sql.append("?");
                if (i < anni.size() - 1) {
                    sql.append(","); // Virgola tra i valori
                }
                params.add(anni.get(i));
            }
            sql.append(")");
        }

        // 4. Filtro Chilometri
        if (kmMax != null) {
            sql.append(" AND chilometraggio <= ?");
            params.add(kmMax);
        }

        // 5. Filtro Stato
        if (stato != null && !stato.trim().isEmpty()) {
            sql.append(" AND stato = ?");
            params.add(stato);
        }

        // 6. Filtro Disponibilità
        if (disponibilita != null && disponibilita) {
            sql.append(" AND disponibilita = ?");
            params.add(true);
        }

        // Esecuzione
        try (PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            // Imposta i parametri
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

    // --- HELPER MAPPING ---
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