package controller;

import dao.DbConnection;
import dao.UtenteDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // --------------------------------------------------------
        // VALIDAZIONE INPUT (Soddisfa TC_2.1, TC_2.2, TC_2.3)
        // --------------------------------------------------------

        // 1. Validazione Formato Email
        // Regex: controlla la presenza di @ e del dominio (es. g.nappo89libero.it fallisce)
        if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            request.setAttribute("errore", "L’accesso non va a buon fine perché l’e-mail non è in un formato valido.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // 2. Validazione Formato Password
        // Regex: Almeno 8 caratteri, almeno una lettera e un numero.
        // TC_2.1_2 (0099m9 -> fallisce), TC_2.2_2 (giovanni4 -> fallisce)
        if (password == null || !password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*?&.]{8,}$")) {
            request.setAttribute("errore", "L’accesso non va a buon fine perché la password non è in un formato valido.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // --------------------------------------------------------
        // LOGICA DI BUSINESS (Accesso al DB)
        // --------------------------------------------------------

        try {
            Connection conn = DbConnection.getInstance().getConnection();
            UtenteDAO utenteDAO = new UtenteDAO(conn);
            Utente utente = utenteDAO.checkLogin(email, password);

            if (utente != null) {
                // Login Riuscito (TC_2.1_3, TC_2.2_3, TC_2.3_3)
                HttpSession session = request.getSession();
                session.setAttribute("utente", utente);

                String ruolo = utente.getRuolo().toUpperCase();

                switch (ruolo) {
                    case "CLIENTE":
                        response.sendRedirect("home");
                        break;
                    case "VENDITORE":
                        response.sendRedirect("home");
                        break;
                    case "AMMINISTRATORE":
                        response.sendRedirect("dashboardAdmin");
                        break;
                    default:
                        response.sendRedirect("index.jsp");
                }
            } else {
                // Login Fallito (Credenziali errate ma formato valido)
                request.setAttribute("errore", "Credenziali non valide");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore DB: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}