package dao;

import model.Automobile;
import model.Preventivo;
import model.Utente; // Importante

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreventivoDAO {

    private Connection connection;

    public PreventivoDAO(Connection connection) {
        this.connection = connection;
    }

    // Metodo per inserire (Richiesta Cliente)
    public void insert(Preventivo p) throws SQLException {
        String sql = "INSERT INTO PREVENTIVO (idUtente, idAuto, dataPreventivo, note, stato) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, p.getIdUtente());
            ps.setInt(2, p.getIdAuto());
            ps.setTimestamp(3, p.getDataPreventivo());
            ps.setString(4, p.getNote());
            ps.setString(5, p.getStato());
            ps.executeUpdate();
        }
    }

    // METODO AGGIORNATO PER LA DASHBOARD CLIENTE
    public List<Preventivo> getByUser(int idUtente) throws SQLException {
        // JOIN per prendere anche i dati dell'auto!
        String sql = "SELECT p.*, a.marca, a.modello, a.prezzo " +
                "FROM PREVENTIVO p " +
                "JOIN AUTO a ON p.idAuto = a.idAuto " +
                "WHERE p.idUtente = ? " +
                "ORDER BY p.dataPreventivo DESC";

        List<Preventivo> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idUtente);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Preventivo p = new Preventivo();
                    p.setIdPreventivo(rs.getInt("idPreventivo"));
                    p.setIdUtente(rs.getInt("idUtente"));
                    p.setIdAuto(rs.getInt("idAuto"));
                    p.setDataPreventivo(rs.getTimestamp("dataPreventivo"));
                    p.setNote(rs.getString("note"));
                    p.setStato(rs.getString("stato"));

                    // Creo l'oggetto Auto e lo riempio con i dati della JOIN
                    Automobile a = new Automobile();
                    a.setMarca(rs.getString("marca"));
                    a.setModello(rs.getString("modello"));
                    a.setPrezzo(rs.getDouble("prezzo"));

                    // Inserisco l'auto nel preventivo
                    p.setAuto(a);

                    list.add(p);
                }
            }
        }
        return list;
    }

    // Altri metodi (getAllCompleti, etc.) rimangono uguali se li hai già messi
}