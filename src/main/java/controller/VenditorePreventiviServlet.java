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

        // 1. CONTROLLO SICUREZZA
        // Verifichiamo che l'utente sia loggato e sia un VENDITORE.
        // Se non lo è, lo buttiamo fuori verso il login.
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");

        if (u == null || !u.getRuolo().equalsIgnoreCase("VENDITORE")) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // 2. RECUPERO DATI DAL DAO
            // Otteniamo la connessione dal Singleton
            Connection conn = DbConnection.getInstance().getConnection();
            PreventivoDAO dao = new PreventivoDAO(conn);

            // Chiamiamo il metodo "Speciale" che fa le JOIN (Clienti + Auto)
            // Se chiamassimo getAll() semplice, la JSP esploderebbe.
            List<Preventivo> lista = dao.getAllCompleti();

            // 3. INVIO DATI ALLA VIEW (JSP)
            // L'attributo DEVE chiamarsi "listaPreventivi" perché è quello che
            // la JSP cerca nel <c:forEach items="${listaPreventivi}">
            request.setAttribute("listaPreventivi", lista);

            // Forward alla pagina di visualizzazione
            RequestDispatcher dispatcher = request.getRequestDispatcher("venditorePreventivi.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            // In caso di errore DB, mostriamo un messaggio (o pagina di errore 500)
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Impossibile caricare la lista preventivi.");
        }
    }
}