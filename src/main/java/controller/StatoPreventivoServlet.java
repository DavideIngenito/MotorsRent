package controller;

import dao.DbConnection;
import dao.PreventivoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Preventivo;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/statoPreventivi")
public class StatoPreventivoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            Connection conn = DbConnection.getInstance().getConnection();
            PreventivoDAO preventivoDAO = new PreventivoDAO(conn);

            List<Preventivo> lista = preventivoDAO.getByUser(utente.getIdUtente());

            request.setAttribute("preventivi", lista);

            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboardCliente.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Errore nel recupero dei preventivi.");
        }
    }
}