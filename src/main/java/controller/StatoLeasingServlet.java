package controller;

import dao.DbConnection;
import dao.LeasingDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Leasing;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/statoLeasing")
public class StatoLeasingServlet extends HttpServlet {

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
            LeasingDAO dao = new LeasingDAO(conn);

            // Recupera lista leasing dell'utente
            List<Leasing> lista = dao.getByUser(utente.getIdUtente());

            // Passa l'attributo con il nome che usi nella JSP
            request.setAttribute("leasingList", lista);

            // Assicurati che questa JSP esista e faccia il c:forEach su "leasingList"
            RequestDispatcher dispatcher = request.getRequestDispatcher("statoLeasing.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}