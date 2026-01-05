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

        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");

        if (u == null || !"VENDITORE".equalsIgnoreCase(u.getRuolo())) {
            response.sendRedirect("login.jsp");
            return;
        }
        try {
            Connection conn = DbConnection.getInstance().getConnection();
            PreventivoDAO preventivoDAO = new PreventivoDAO(conn);
            LeasingDAO leasingDAO = new LeasingDAO(conn);


            List<Preventivo> tuttiPreventivi = preventivoDAO.getAllCompleti();
            List<Leasing> tuttiLeasing = leasingDAO.getAllCompleti();


            long preventiviInAttesa = tuttiPreventivi.stream()
                    .filter(p -> "NUOVA".equalsIgnoreCase(p.getStato()))
                    .count();


            long leasingInAttesa = tuttiLeasing.stream()
                    .filter(l -> "IN VALUTAZIONE".equalsIgnoreCase(l.getStato()))
                    .count();


            request.setAttribute("numPreventiviInAttesa", preventiviInAttesa);
            request.setAttribute("numLeasingInAttesa", leasingInAttesa);

            RequestDispatcher dispatcher = request.getRequestDispatcher("venditoreDashboard.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore caricamento dashboard.");
        }
    }
}