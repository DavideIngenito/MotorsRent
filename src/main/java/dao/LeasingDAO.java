package dao;

import model.Leasing;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeasingDAO {

    private Connection connection;

    public LeasingDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Leasing l) throws SQLException {
        String sql = "INSERT INTO LEASING (idUtente, idAuto, durataMesi, anticipo, kmAnnui, dataLeasing, stato) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, l.getIdUtente());
        ps.setInt(2, l.getIdAuto());
        ps.setInt(3, l.getDurataMesi());
        ps.setDouble(4, l.getAnticipo());
        ps.setInt(5, l.getKmAnnui());
        ps.setString(6, String.valueOf(l.getDataLeasing()));
        ps.setString(7, l.getStato());

        ps.executeUpdate();
    }
    public List<Leasing> getByUser(int idUtente) throws SQLException {
        String sql = "SELECT * FROM LEASING WHERE idUtente = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, idUtente);

        ResultSet rs = ps.executeQuery();
        List<Leasing> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new Leasing(
                    rs.getInt("idLeasing"),
                    rs.getInt("idUtente"),
                    rs.getInt("idAuto"),
                    rs.getInt("durataMesi"),
                    rs.getInt("anticipo"),
                    rs.getInt("kmAnnui"),
                    rs.getTimestamp("dataLeasing"),
                    rs.getString("stato")
            ));
        }
        return list;
    }

    public List<Leasing> getAll() throws SQLException {
        String sql = "SELECT * FROM LEASING";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        List<Leasing> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new Leasing(
                    rs.getInt("idLeasing"),
                    rs.getInt("idUtente"),
                    rs.getInt("idAuto"),
                    rs.getInt("durataMesi"),
                    rs.getInt("anticipo"),
                    rs.getInt("kmAnnui"),
                    rs.getTimestamp("dataLeasing"),
                    rs.getString("stato")
            ));
        }
        return list;
    }

    public void updateStato(int idLeasing, String stato) throws SQLException {
        String sql = "UPDATE LEASING SET stato = ? WHERE idLeasing = ?";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, stato);
        ps.setInt(2, idLeasing);
        ps.executeUpdate();
    }
}