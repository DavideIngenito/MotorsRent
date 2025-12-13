package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;

import java.io.IOException;

@WebServlet("/admin/dashboard")
public class DashboardAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. SICUREZZA: Controllo se l'utente è loggato ed è ADMIN
        HttpSession session = request.getSession(false);
        Utente u = (session != null) ? (Utente) session.getAttribute("utente") : null;

        if (u == null || !u.getRuolo().equalsIgnoreCase("ADMIN")) {
            // Se non è admin, lo spedisco al login
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // 2. (Opzionale da 30 e Lode) Qui potresti caricare statistiche
        // es. int numUtenti = utenteDAO.countAll();
        // request.setAttribute("numUtenti", numUtenti);

        // 3. Forward alla JSP (Verifica il percorso!)
        // Se adminDashboard.jsp è dentro webapp/jsp/admin/
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/admin/adminDashboard.jsp");
        rd.forward(request, response);
    }
}