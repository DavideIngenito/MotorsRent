package controller;

import dao.DbConnection;
import dao.LeasingDAO;
import dao.PreventivoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Leasing;
import model.Preventivo;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/dashboardCliente")
public class DashboardClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Utente u = (session != null) ? (Utente) session.getAttribute("utente") : null;

        if (u == null || !u.getRuolo().equalsIgnoreCase("CLIENTE")) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Connessione
            Connection conn = DbConnection.getInstance().getConnection();
            PreventivoDAO preventivoDAO = new PreventivoDAO(conn);
            LeasingDAO leasingDAO = new LeasingDAO(conn);

            List<Preventivo> iMieiPreventivi = preventivoDAO.getByUser(u.getIdUtente());
            List<Leasing> iMieiLeasing = leasingDAO.getByUser(u.getIdUtente());

            request.setAttribute("listaPreventivi", iMieiPreventivi);
            request.setAttribute("listaLeasing", iMieiLeasing);

            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboardCliente.jsp"); // O il percorso corretto
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Errore dashboard");
        }
    }
}