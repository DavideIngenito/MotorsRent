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

    // NON istanziare qui il DAO se richiede la connessione,
    // perché la connessione potrebbe essere chiusa o null all'avvio della servlet.
    // Lo facciamo dentro i metodi.

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Semplice reindirizzamento alla JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            // 1. Recupero la connessione dal Singleton (Come da ODD)
            Connection conn = DbConnection.getInstance().getConnection();

            // 2. Istanzio il DAO passando la connessione
            UtenteDAO utenteDAO = new UtenteDAO(conn);

            // 3. Chiamo il metodo (nota: si chiamava checkLogin nel DAO precedente)
            Utente utente = utenteDAO.checkLogin(email, password);

            if (utente != null) {
                // Login Riuscito
                HttpSession session = request.getSession();
                session.setAttribute("utente", utente);

                // Normalizzo il ruolo in maiuscolo per evitare problemi (CLIENTE vs cliente)
                String ruolo = utente.getRuolo().toUpperCase();

                switch (ruolo) {
                    case "CLIENTE":
                        response.sendRedirect("dashboardCliente.jsp");
                        break;
                    case "VENDITORE":
                        response.sendRedirect("dashboardVenditore.jsp");
                        break;
                    case "ADMIN":
                    case "AMMINISTRATORE": // Gestisco entrambi i casi per sicurezza
                        response.sendRedirect("dashboardAdmin.jsp");
                        break;
                    default:
                        response.sendRedirect("home.jsp"); // Fallback
                }
            } else {
                // Login Fallito
                request.setAttribute("errore", "Credenziali non valide");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // In caso di errore DB, non lasciare l'utente appeso
            request.setAttribute("errore", "Errore di connessione al database");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}