package dao;

import model.Preventivo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreventivoDAO {

    private Connection connection;

    public PreventivoDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Preventivo p) throws SQLException {
        String sql = "INSERT INTO PREVENTIVO (idUtente, idAuto, dataPreventivo, note, stato) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, p.getIdUtente());
        ps.setInt(2, p.getIdAuto());
        ps.setString(3, p.getDataPreventivo());
        ps.setString(4, p.getNote());
        ps.setString(5, p.getStato());
        ps.executeUpdate();
    }
    public List<Preventivo> getByUser(int idUtente) throws SQLException {
        String sql = "SELECT * FROM PREVENTIVO WHERE idUtente = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, idUtente);

        ResultSet rs = ps.executeQuery();
        List<Preventivo> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new Preventivo(
                    rs.getInt("idPreventivo"),
                    rs.getInt("idUtente"),
                    rs.getInt("idAuto"),
                    rs.getString("dataPreventivo"),
                    rs.getString("note"),
                    rs.getString("stato")
            ));
        }
        return list;
    }

    public List<Preventivo> getAll() throws SQLException {
        String sql = "SELECT * FROM PREVENTIVO";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        List<Preventivo> list = new ArrayList<>();

        while (rs.next()) {
            list.add(new Preventivo(
                    rs.getInt("idPreventivo"),
                    rs.getInt("idUtente"),
                    rs.getInt("idAuto"),
                    rs.getString("dataPreventivo"),
                    rs.getString("note"),
                    rs.getString("stato")
            ));
        }
        return list;
    }

    public void updateStato(int idPreventivo, String stato) throws SQLException {
        String sql = "UPDATE PREVENTIVO SET stato = ? WHERE idPreventivo = ?";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, stato);
        ps.setInt(2, idPreventivo);
        ps.executeUpdate();
    }
}