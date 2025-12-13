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

@WebServlet("/registrazione")
public class RegistrazioneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("registrazione.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Recupero parametri dal form
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono"); // Ora lo prendiamo!
        String password = request.getParameter("password");

        // 2. Creazione oggetto Model
        Utente u = new Utente();
        u.setNome(nome);
        u.setCognome(cognome);
        u.setEmail(email);
        u.setTelefono(telefono);
        u.setPassword(password); // Nota: All'esame chiedono spesso l'hash, qui è in chiaro
        u.setRuolo("CLIENTE");   // Ruolo di default

        try {
            // 3. Connessione e DAO
            Connection conn = DbConnection.getInstance().getConnection();
            UtenteDAO utenteDAO = new UtenteDAO(conn);

            // 4. Inserimento (metodo 'insert' come nel DAO inviato prima)
            utenteDAO.insert(u);

            // 5. Successo -> Login
            response.sendRedirect("login.jsp?registrazione=ok");

        } catch (SQLException e) {
            e.printStackTrace();
            String messaggioErrore = "Errore durante la registrazione.";

            // Gestione specifica per email duplicata (codice errore MySQL 1062)
            if (e.getErrorCode() == 1062 || e.getMessage().contains("Duplicate")) {
                messaggioErrore = "Questa email è già registrata.";
            }

            request.setAttribute("errore", messaggioErrore);
            request.getRequestDispatcher("registrazione.jsp").forward(request, response);
        }
    }
}