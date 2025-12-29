package controller;

import dao.DbConnection;
import dao.LeasingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Leasing;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;

@WebServlet("/richiestaLeasing")
public class RichiestaLeasingServlet extends HttpServlet {

    /**
     * GESTISCE LA RICHIESTA GET (Click sul link "Simula Leasing")
     * Mostra la pagina del form.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Controllo Login
        HttpSession session = request.getSession(false);
        Utente utente = (session != null) ? (Utente) session.getAttribute("utente") : null;

        if (utente == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 2. Controllo ID Auto (opzionale ma consigliato)
        String idAuto = request.getParameter("idAuto");
        if (idAuto == null || idAuto.isEmpty()) {
            response.sendRedirect("catalogo");
            return;
        }

        // 3. Mostra la JSP (il form)
        // I parametri URL (come idAuto) vengono passati automaticamente alla JSP
        request.getRequestDispatcher("richiestaLeasing.jsp").forward(request, response);
    }

    /**
     * GESTISCE LA RICHIESTA POST (Click su "Invia Richiesta Leasing")
     * Salva i dati nel DB.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Utente utente = (session != null) ? (Utente) session.getAttribute("utente") : null;

        if (utente == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Controllo idAuto anche qui
            String idAutoParam = request.getParameter("idAuto");
            if (idAutoParam == null || idAutoParam.isEmpty()) {
                response.sendRedirect("catalogo");
                return;
            }

            Leasing l = new Leasing();
            l.setIdUtente(utente.getIdUtente());
            l.setIdAuto(Integer.parseInt(idAutoParam));
            l.setDurataMesi(Integer.parseInt(request.getParameter("durata")));
            l.setAnticipo(Double.parseDouble(request.getParameter("anticipo")));
            l.setKmAnnui(Integer.parseInt(request.getParameter("kmAnnui")));
            l.setDataRichiesta(new Timestamp(System.currentTimeMillis()));

            // Cattura Note e Stato
            l.setNote(request.getParameter("note"));
            l.setStato("IN VALUTAZIONE");

            Connection conn = DbConnection.getInstance().getConnection();
            LeasingDAO leasingDAO = new LeasingDAO(conn);
            leasingDAO.insert(l);

            response.sendRedirect("dashboardCliente");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Errore durante la creazione della richiesta di leasing.");
        }
    }
}