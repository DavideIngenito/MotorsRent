package controller;

import dao.DbConnection;
import dao.PreventivoDAO;
// import dao.LeasingDAO; // Decommenta quando hai il LeasingDAO pronto
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
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

        // Verifica null e ruolo (case insensitive per sicurezza)
        if (u == null || !"VENDITORE".equalsIgnoreCase(u.getRuolo())) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            Connection conn = DbConnection.getInstance().getConnection();
            PreventivoDAO preventivoDAO = new PreventivoDAO(conn);
            // LeasingDAO leasingDAO = new LeasingDAO(conn);

            // 2. Recupero Liste
            // CORREZIONE QUI: Usa il metodo che esiste nel tuo DAO attuale!
            List<Preventivo> tuttiPreventivi = preventivoDAO.getAllCompleti();

            // List<Leasing> tuttiLeasing = leasingDAO.getAllCompleti();

            // 3. Calcolo i numeri per la Dashboard
            long preventiviInAttesa = tuttiPreventivi.stream()
                    // Assicurati che nel DB lo stato sia scritto esattamente "NUOVA" o usa equalsIgnoreCase
                    .filter(p -> "NUOVA".equalsIgnoreCase(p.getStato()))
                    .count();

            // long leasingInAttesa = tuttiLeasing.stream().filter(...).count();

            // 4. Invio i dati alla JSP
            request.setAttribute("numPreventiviInAttesa", preventiviInAttesa);
            request.setAttribute("numLeasingInAttesa", 0); // Placeholder

            RequestDispatcher dispatcher = request.getRequestDispatcher("venditoreDashboard.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // È buona norma dare un feedback visivo o loggare l'errore
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore caricamento dashboard.");
        }
    }
}