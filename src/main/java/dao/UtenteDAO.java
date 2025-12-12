package dao;

import model.Utente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {

    private Connection connection;

    public UtenteDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Utente u) throws SQLException {
        String sql = "INSERT INTO UTENTE (nome, cognome, email, password, telefono, ruolo) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, u.getNome());
        ps.setString(2, u.getCognome());
        ps.setString(3, u.getEmail());
        ps.setString(4, u.getPassword());
        ps.setString(5, u.getTelefono());
        ps.setString(6, u.getRuolo());
        ps.executeUpdate();
    }
    public Utente getByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM UTENTE WHERE email = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Utente(
                    rs.getInt("idUtente"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("telefono"),
                    rs.getString("ruolo")
            );
        }
        return null;
    }

    public List<Utente> getAll() throws SQLException {
        String sql = "SELECT * FROM UTENTE";
        Statement st = connection.createStatement();

        ResultSet rs = st.executeQuery(sql);
        List<Utente> utenti = new ArrayList<>();

        while (rs.next()) {
            utenti.add(new Utente(
                    rs.getInt("idUtente"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("telefono"),
                    rs.getString("ruolo")
            ));
        }
        return utenti;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM UTENTE WHERE idUtente = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}