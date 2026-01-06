package controller;

import dao.DbConnection;
import dao.PreventivoDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Preventivo;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/venditorePreventivi")
public class VenditorePreventiviServlet extends HttpServlet {

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
            PreventivoDAO dao = new PreventivoDAO(conn);

            List<Preventivo> lista = dao.getAllCompleti();

            request.setAttribute("listaPreventivi", lista);

            RequestDispatcher dispatcher = request.getRequestDispatcher("venditorePreventivi.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Impossibile caricare la lista preventivi.");
        }
    }
}