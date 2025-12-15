package controller;

import dao.AutomobileDAO;
import dao.DbConnection;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Automobile;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/AdminAutoController")
public class AdminAutoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. SICUREZZA: Controllo Login e Ruolo
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");

        // NOTA: Qui uso "AMMINISTRATORE" perché nel tuo SQL hai scritto:
        // ENUM('OSPITE','CLIENTE','VENDITORE','AMMINISTRATORE')
        if (u == null || !u.getRuolo().equalsIgnoreCase("AMMINISTRATORE")) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 2. GESTIONE AZIONI
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            Connection conn = DbConnection.getInstance().getConnection();
            AutomobileDAO autoDAO = new AutomobileDAO(conn);

            switch (action) {
                case "addForm": // Mostra il form vuoto
                    showAddForm(request, response);
                    break;
                case "add": // Esegue l'inserimento nel DB
                    insertAuto(request, response, autoDAO);
                    break;
                case "delete": // Elimina auto
                    deleteAuto(request, response, autoDAO);
                    break;
                case "editForm": // Carica i dati per la modifica
                    showEditForm(request, response, autoDAO);
                    break;
                case "update": // Esegue l'aggiornamento nel DB
                    updateAuto(request, response, autoDAO);
                    break;
                case "list": // Mostra la tabella (Default)
                default:
                    listAuto(request, response, autoDAO);
                    break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.sendError(500, "Errore Database: " + ex.getMessage());
        }
    }

    // --- METODI PRIVATI (WORKER) ---

    private void listAuto(HttpServletRequest request, HttpServletResponse response, AutomobileDAO dao)
            throws SQLException, ServletException, IOException {
        List<Automobile> listAuto = dao.getAll();
        request.setAttribute("listaAuto", listAuto);
        // Path corretto: file nella root webapp
        request.getRequestDispatcher("adminGestioneAuto.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Path corretto
        request.getRequestDispatcher("adminAggiungiAuto.jsp").forward(request, response);
    }

    private void insertAuto(HttpServletRequest request, HttpServletResponse response, AutomobileDAO dao)
            throws SQLException, IOException {

        Automobile a = new Automobile();

        // 1. Dati base
        a.setMarca(request.getParameter("marca"));
        a.setModello(request.getParameter("modello"));
        a.setAnno(Integer.parseInt(request.getParameter("anno")));
        a.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
        a.setChilometraggio(Integer.parseInt(request.getParameter("chilometraggio")));
        a.setDescrizione(request.getParameter("descrizione"));
        a.setImmagine(request.getParameter("immagine"));

        // 2. STATO (Condizione fisica: "Nuova" o "Usata")
        a.setStato(request.getParameter("stato"));

        // 3. DISPONIBILITA' (Logica: true/false)
        // Se la select invia "1", diventa true. Altrimenti false.
        boolean disp = "1".equals(request.getParameter("disponibilita"));
        a.setDisponibilita(disp);

        dao.insert(a);
        response.sendRedirect("AdminAutoController?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response, AutomobileDAO dao)
            throws SQLException, ServletException, IOException {

        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            Automobile auto = dao.getById(id);
            request.setAttribute("auto", auto);
            // Path corretto
            request.getRequestDispatcher("adminModificaAuto.jsp").forward(request, response);
        } else {
            response.sendRedirect("AdminAutoController?action=list");
        }
    }

    private void updateAuto(HttpServletRequest request, HttpServletResponse response, AutomobileDAO dao)
            throws SQLException, IOException {

        Automobile a = new Automobile();

        // ID necessario per l'UPDATE
        a.setIdAuto(Integer.parseInt(request.getParameter("idAuto")));

        // Dati base
        a.setMarca(request.getParameter("marca"));
        a.setModello(request.getParameter("modello"));
        a.setAnno(Integer.parseInt(request.getParameter("anno")));
        a.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
        a.setChilometraggio(Integer.parseInt(request.getParameter("chilometraggio")));
        a.setDescrizione(request.getParameter("descrizione"));
        a.setImmagine(request.getParameter("immagine"));

        // STATO (Nuova/Usata) mantenuto separato
        a.setStato(request.getParameter("stato"));

        // DISPONIBILITA' (True/False) mantenuta separata
        boolean disp = "1".equals(request.getParameter("disponibilita"));
        a.setDisponibilita(disp);

        dao.update(a);
        response.sendRedirect("AdminAutoController?action=list");
    }

    private void deleteAuto(HttpServletRequest request, HttpServletResponse response, AutomobileDAO dao)
            throws SQLException, IOException {

        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            dao.delete(id);
        }
        response.sendRedirect("AdminAutoController?action=list");
    }
}