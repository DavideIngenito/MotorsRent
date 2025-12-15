package controller;

import dao.DbConnection;
import dao.LeasingDAO;     // Togli il commento qui
import dao.PreventivoDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Leasing;      // Aggiungi questo import
import model.Preventivo;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/dashboardVenditore")
public class DashboardVenditoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Controllo Sicurezza (Solo VENDITORE può entrare)
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");

        if (u == null || !"VENDITORE".equalsIgnoreCase(u.getRuolo())) {
            response.sendRedirect("login.jsp");
            return;
        }
        try {
            Connection conn = DbConnection.getInstance().getConnection();
            PreventivoDAO preventivoDAO = new PreventivoDAO(conn);
            LeasingDAO leasingDAO = new LeasingDAO(conn); // Ora attiviamo il DAO

            // 2. Recupero Liste Complete
            List<Preventivo> tuttiPreventivi = preventivoDAO.getAllCompleti();
            List<Leasing> tuttiLeasing = leasingDAO.getAllCompleti(); // Carichiamo i leasing

            // 3. Calcolo i numeri per la Dashboard
            long preventiviInAttesa = tuttiPreventivi.stream()
                    .filter(p -> "NUOVA".equalsIgnoreCase(p.getStato()))
                    .count();

            // Calcoliamo i leasing in attesa (Stato: "IN VALUTAZIONE")
            long leasingInAttesa = tuttiLeasing.stream()
                    .filter(l -> "IN VALUTAZIONE".equalsIgnoreCase(l.getStato()))
                    .count();

            // 4. Invio i dati alla JSP
            request.setAttribute("numPreventiviInAttesa", preventiviInAttesa);
            request.setAttribute("numLeasingInAttesa", leasingInAttesa); // Mettiamo il numero vero!

            RequestDispatcher dispatcher = request.getRequestDispatcher("venditoreDashboard.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore caricamento dashboard.");
        }
    }
}