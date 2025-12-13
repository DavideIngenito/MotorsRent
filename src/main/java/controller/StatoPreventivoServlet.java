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

        // 1. RECUPERO UTENTE DALLA SESSIONE (Oggetto intero, non solo ID!)
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // 2. ISTANZIAZIONE DAO (Singleton)
            Connection conn = DbConnection.getInstance().getConnection();
            PreventivoDAO preventivoDAO = new PreventivoDAO(conn);

            // 3. CHIAMATA AL METODO (Instance method, non statico!)
            // Nota: Assicurati che PreventivoDAO abbia il metodo getByUser(int id)
            List<Preventivo> lista = preventivoDAO.getByUser(utente.getIdUtente());

            request.setAttribute("preventivi", lista);

            // Verifica il nome della JSP. Nelle tue liste precedenti si chiamava "statoPreventivi.jsp"
            // o "dashboardCliente.jsp"? Assicurati che il percorso sia giusto.
            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboardCliente.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Errore nel recupero dei preventivi.");
        }
    }
}