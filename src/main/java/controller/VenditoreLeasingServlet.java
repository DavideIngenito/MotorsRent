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

        // 1. SICUREZZA: Solo i venditori possono vedere questa lista
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");

        if (u == null || !u.getRuolo().equalsIgnoreCase("VENDITORE")) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // 2. ISTANZIAZIONE DAO
            Connection conn = DbConnection.getInstance().getConnection();
            LeasingDAO leasingDAO = new LeasingDAO(conn);

            // 3. RECUPERO DATI
            // Usiamo il metodo con le JOIN che abbiamo aggiunto al DAO
            List<Leasing> lista = leasingDAO.getAllCompleti();

            // 4. PASSAGGIO DATI ALLA JSP
            // Il nome "listaLeasing" deve coincidere con quello nel <c:forEach> della JSP
            request.setAttribute("listaLeasing", lista);

            RequestDispatcher dispatcher = request.getRequestDispatcher("venditoreLeasing.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Impossibile caricare la lista leasing.");
        }
    }
}