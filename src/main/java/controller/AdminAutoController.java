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

        // 1. SICUREZZA: Solo Admin
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");

        // ATTENZIONE: Controlla se nel tuo DB hai scritto "ADMIN" o "AMMINISTRATORE"
        if (u == null || !u.getRuolo().equalsIgnoreCase("ADMIN")) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 2. RICEZIONE AZIONE (default: list)
        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            Connection conn = DbConnection.getInstance().getConnection();
            AutomobileDAO autoDAO = new AutomobileDAO(conn);

            switch (action) {
                case "addForm": // Mostra il form vuoto
                    showAddForm(request, response);
                    break;
                case "add": // Esegue l'inserimento
                    insertAuto(request, response, autoDAO);
                    break;
                case "delete":
                    deleteAuto(request, response, autoDAO);
                    break;
                case "editForm": // Carica i dati per la modifica
                    showEditForm(request, response, autoDAO);
                    break;
                case "update": // Esegue l'aggiornamento
                    updateAuto(request, response, autoDAO);
                    break;
                case "list":
                default:
                    listAuto(request, response, autoDAO);
                    break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.sendError(500, "Errore database: " + ex.getMessage());
        }
    }

    // --- METODI PRIVATI DI SUPPORTO (Gestiscono i Path corretti) ---

    private void listAuto(HttpServletRequest request, HttpServletResponse response, AutomobileDAO dao)
            throws SQLException, ServletException, IOException {
        List<Automobile> listAuto = dao.getAll();
        request.setAttribute("listaAuto", listAuto);

        // CORRETTO: Punta direttamente al file nella cartella principale
        request.getRequestDispatcher("adminGestioneAuto.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // CORRETTO
        request.getRequestDispatcher("adminAggiungiAuto.jsp").forward(request, response);
    }

    private void insertAuto(HttpServletRequest request, HttpServletResponse response, AutomobileDAO dao)
            throws SQLException, IOException {
        Automobile a = new Automobile();
        a.setMarca(request.getParameter("marca"));
        a.setModello(request.getParameter("modello"));
        a.setAnno(Integer.parseInt(request.getParameter("anno")));
        a.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
        a.setChilometraggio(Integer.parseInt(request.getParameter("chilometraggio")));
        a.setDescrizione(request.getParameter("descrizione"));
        a.setImmagine(request.getParameter("immagine"));

        // Gestione disponibilità: se arriva "1" diventa true
        a.setDisponibilita("1".equals(request.getParameter("disponibilita")));
        a.setStato("Disponibile");

        dao.insert(a);
        response.sendRedirect("AdminAutoController?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response, AutomobileDAO dao)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Automobile auto = dao.getById(id);
        request.setAttribute("auto", auto);

        // CORRETTO
        request.getRequestDispatcher("adminModificaAuto.jsp").forward(request, response);
    }

    private void updateAuto(HttpServletRequest request, HttpServletResponse response, AutomobileDAO dao)
            throws SQLException, IOException {
        Automobile a = new Automobile();
        a.setIdAuto(Integer.parseInt(request.getParameter("idAuto")));
        a.setMarca(request.getParameter("marca"));
        a.setModello(request.getParameter("modello"));
        a.setAnno(Integer.parseInt(request.getParameter("anno")));
        a.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
        a.setChilometraggio(Integer.parseInt(request.getParameter("chilometraggio")));
        a.setDescrizione(request.getParameter("descrizione"));
        a.setImmagine(request.getParameter("immagine"));

        boolean disp = "1".equals(request.getParameter("disponibilita"));
        a.setDisponibilita(disp);
        a.setStato(disp ? "Disponibile" : "Non Disponibile");

        dao.update(a);
        response.sendRedirect("AdminAutoController?action=list");
    }

    private void deleteAuto(HttpServletRequest request, HttpServletResponse response, AutomobileDAO dao)
            throws SQLException, IOException {
        String idStr = request.getParameter("id");
        if(idStr != null && !idStr.isEmpty()){
            int id = Integer.parseInt(idStr);
            dao.delete(id);
        }
        response.sendRedirect("AdminAutoController?action=list");
    }
}