package controller;

import dao.DbConnection;
import dao.PreventivoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Preventivo;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;

@WebServlet("/richiestaPreventivo")
public class RichiestaPreventivoServlet extends HttpServlet {

    /**
     * GESTISCE LA RICHIESTA GET (Click sul link "Richiedi Preventivo")
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

        // 2. Controllo ID Auto
        String idAuto = request.getParameter("idAuto");
        if (idAuto == null || idAuto.isEmpty()) {
            response.sendRedirect("catalogo");
            return;
        }

        // 3. Mostra la JSP (il form)
        request.getRequestDispatcher("richiestaPreventivo.jsp").forward(request, response);
    }

    /**
     * GESTISCE LA RICHIESTA POST (Click su "Invia Richiesta")
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
            // Controllo parametro
            String idAutoParam = request.getParameter("idAuto");
            if (idAutoParam == null || idAutoParam.isEmpty()) {
                response.sendRedirect("catalogo");
                return;
            }

            Preventivo p = new Preventivo();
            p.setIdUtente(utente.getIdUtente());
            p.setIdAuto(Integer.parseInt(idAutoParam));
            p.setDataRichiesta(new Timestamp(System.currentTimeMillis()));
            p.setNote(request.getParameter("note"));

            // IMPOSTA STATO INIZIALE
            p.setStato("IN VALUTAZIONE");

            Connection conn = DbConnection.getInstance().getConnection();
            PreventivoDAO preventivoDAO = new PreventivoDAO(conn);
            preventivoDAO.insert(p);

            response.sendRedirect("dashboardCliente");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Errore durante la creazione del preventivo.");
        }
    }
}