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



        if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            request.setAttribute("errore", "L’accesso non va a buon fine perché l’e-mail non è in un formato valido.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }


        if (password == null || !password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*?&.]{8,}$")) {
            request.setAttribute("errore", "L’accesso non va a buon fine perché la password non è in un formato valido.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        try {
            Connection conn = DbConnection.getInstance().getConnection();
            UtenteDAO utenteDAO = new UtenteDAO(conn);
            Utente utente = utenteDAO.checkLogin(email, password);

            if (utente != null) {
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