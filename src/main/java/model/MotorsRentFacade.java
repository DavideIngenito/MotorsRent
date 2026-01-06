package model;

import dao.AutomobileDAO;
import dao.DbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


public class MotorsRentFacade {

    private static MotorsRentFacade instance;

    // Costruttore privato
    private MotorsRentFacade() {}

    public static synchronized MotorsRentFacade getInstance() {
        if (instance == null) {
            instance = new MotorsRentFacade();
        }
        return instance;
    }


    public List<Automobile> getCatalogoAuto() {
        try {
            Connection conn = DbConnection.getInstance().getConnection();
            AutomobileDAO dao = new AutomobileDAO(conn);
            return dao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    public Automobile getAutoById(int id) {
        try {
            Connection conn = DbConnection.getInstance().getConnection();
            AutomobileDAO dao = new AutomobileDAO(conn);
            return dao.getById(id); // Chiama il metodo del DAO
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public boolean aggiungiAuto(Automobile auto) {
        try {
            Connection conn = DbConnection.getInstance().getConnection();
            AutomobileDAO dao = new AutomobileDAO(conn);
            dao.insert(auto);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean aggiornaAuto(Automobile auto) {
        try {
            Connection conn = DbConnection.getInstance().getConnection();
            AutomobileDAO dao = new AutomobileDAO(conn);
            dao.update(auto);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean eliminaAuto(int id) {
        try {
            Connection conn = DbConnection.getInstance().getConnection();
            AutomobileDAO dao = new AutomobileDAO(conn);
            dao.delete(id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}