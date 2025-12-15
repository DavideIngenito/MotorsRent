package controller;

import dao.DbConnection;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/AdminStatisticheServlet")
public class AdminStatisticheServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Sicurezza: Solo Admin
        HttpSession session = request.getSession(false);
        Utente u = (session != null) ? (Utente) session.getAttribute("utente") : null;
        if (u == null || (!"ADMIN".equalsIgnoreCase(u.getRuolo()) && !"AMMINISTRATORE".equalsIgnoreCase(u.getRuolo()))) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            Connection conn = DbConnection.getInstance().getConnection();

            // 2. Calcolo Statistiche Semplici (Conteggi)
            int numAuto = count(conn, "SELECT COUNT(*) FROM AUTOMOBILE");
            int numAutoDisponibili = count(conn, "SELECT COUNT(*) FROM AUTOMOBILE WHERE disponibilita = 1");
            int numVenditori = count(conn, "SELECT COUNT(*) FROM UTENTE WHERE ruolo = 'VENDITORE'");
            int numClienti = count(conn, "SELECT COUNT(*) FROM UTENTE WHERE ruolo = 'CLIENTE'");
            int numPreventivi = count(conn, "SELECT COUNT(*) FROM PREVENTIVO");
            int numLeasing = count(conn, "SELECT COUNT(*) FROM LEASING");

            // 3. Invio dati alla JSP
            request.setAttribute("numAuto", numAuto);
            request.setAttribute("numAutoDisponibili", numAutoDisponibili);
            request.setAttribute("numVenditori", numVenditori);
            request.setAttribute("numClienti", numClienti);
            request.setAttribute("numPreventivi", numPreventivi);
            request.setAttribute("numLeasing", numLeasing);

            request.getRequestDispatcher("adminStatistiche.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Errore calcolo statistiche");
        }
    }

    // Metodo helper per fare le count veloci
    private int count(Connection conn, String sql) {
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}