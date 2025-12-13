package dao;


import model.Automobile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutomobileDAO {

    private Connection connection;

    public AutomobileDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Automobile a) throws SQLException {
        String sql = "INSERT INTO AUTO (modello, marca, anno, prezzo, chilometraggio, stato, descrizione, immagine, disponibilita) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(sql);
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

    public Automobile getById(int id) throws SQLException {
        String sql = "SELECT FROM AUTO WHERE idAuto = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Automobile(
                    rs.getInt("idAuto"),
                    rs.getString("modello"),
                    rs.getString("marca"),
                    rs.getInt("anno"),
                    rs.getDouble("prezzo"),
                    rs.getInt("chilometraggio"),
                    rs.getString("stato"),
                    rs.getString("descrizione"),
                    rs.getString("immagine"),
                    rs.getBoolean("disponibilita")
            );
        }
        return null;
    }
    public List<Automobile> getAll() throws SQLException {
        List<Automobile> list = new ArrayList<>();
        String sql = "SELECT * FROM AUTO";

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            list.add(new Automobile(
                    rs.getInt("idAuto"),
                    rs.getString("modello"),
                    rs.getString("marca"),
                    rs.getInt("anno"),
                    rs.getDouble("prezzo"),
                    rs.getInt("chilometraggio"),
                    rs.getString("stato"),
                    rs.getString("descrizione"),
                    rs.getString("immagine"),
                    rs.getBoolean("disponibilita")
            ));
        }
        return list;
    }

    public void update(Automobile a) throws SQLException {
        String sql = "UPDATE AUTO SET modello=?, marca=?, anno=?, prezzo=?, chilometraggio=?, stato=?, descrizione=?, immagine=?, disponibilita=? WHERE idAuto=?";

        PreparedStatement ps = connection.prepareStatement(sql);
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

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM AUTO WHERE idAuto = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
