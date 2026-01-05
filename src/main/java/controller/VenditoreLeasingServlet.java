package controller;

import dao.DbConnection;
import dao.LeasingDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Leasing;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

// Questa URL deve corrispondere a quella usata nei link della Dashboard!
@WebServlet("/venditoreLeasing")
public class VenditoreLeasingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");

        if (u == null || !u.getRuolo().equalsIgnoreCase("VENDITORE")) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            Connection conn = DbConnection.getInstance().getConnection();
            LeasingDAO leasingDAO = new LeasingDAO(conn);

            List<Leasing> lista = leasingDAO.getAllCompleti();

            request.setAttribute("listaLeasing", lista);

            RequestDispatcher dispatcher = request.getRequestDispatcher("venditoreLeasing.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Impossibile caricare la lista leasing.");
        }
    }
}