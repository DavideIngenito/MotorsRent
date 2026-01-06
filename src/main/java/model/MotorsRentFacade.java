package model;

import dao.AutomobileDAO;
import dao.DbConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Facade Pattern
 * Fornisce un'interfaccia unificata per le operazioni sui dati (DAO),
 * nascondendo la complessità di connessione e gestione delle eccezioni.
 */
public class MotorsRentFacade {

    private static MotorsRentFacade instance;

    // Costruttore privato (Singleton)
    private MotorsRentFacade() {}

    public static synchronized MotorsRentFacade getInstance() {
        if (instance == null) {
            instance = new MotorsRentFacade();
        }
        return instance;
    }

    // =========================================================================
    //  GESTIONE AUTOMOBILI
    // =========================================================================

    /**
     * Restituisce l'intera lista di auto.
     */
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

    /**
     * Recupera una singola auto per ID (Necessario per "editForm").
     */
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

    /**
     * Aggiunge una nuova auto.
     */
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

    /**
     * Aggiorna un'auto esistente (Necessario per "update").
     */
    public boolean aggiornaAuto(Automobile auto) {
        try {
            Connection conn = DbConnection.getInstance().getConnection();
            AutomobileDAO dao = new AutomobileDAO(conn);
            dao.update(auto); // Chiama il metodo del DAO
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Elimina un'auto (Necessario per "delete").
     */
    public boolean eliminaAuto(int id) {
        try {
            Connection conn = DbConnection.getInstance().getConnection();
            AutomobileDAO dao = new AutomobileDAO(conn);
            dao.delete(id); // Chiama il metodo del DAO
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}