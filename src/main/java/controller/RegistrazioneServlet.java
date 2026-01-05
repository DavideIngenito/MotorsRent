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

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String password = request.getParameter("password");


        if (nome == null || !nome.matches("^[a-zA-Z\\s]+$")) {
            request.setAttribute("errore", "La registrazione non va a buon fine perché il nome non è in un formato valido.");
            request.getRequestDispatcher("registrazione.jsp").forward(request, response);
            return;
        }


        if (cognome == null || !cognome.matches("^[a-zA-Z\\s]+$")) {
            request.setAttribute("errore", "La registrazione non va a buon fine perché il cognome non è in un formato valido.");
            request.getRequestDispatcher("registrazione.jsp").forward(request, response);
            return;
        }


        if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            request.setAttribute("errore", "La registrazione non va a buon fine perché l’e-mail non è in un formato valido.");
            request.getRequestDispatcher("registrazione.jsp").forward(request, response);
            return;
        }


        if (telefono == null || !telefono.matches("^\\d{10}$")) {
            request.setAttribute("errore", "La registrazione non va a buon fine perché il telefono non è in un formato valido.");
            request.getRequestDispatcher("registrazione.jsp").forward(request, response);
            return;
        }


        if (password == null || !password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*?&.]{8,}$")) {
            request.setAttribute("errore", "La registrazione non va a buon fine perché la password non è in un formato valido (Richiesti min. 8 caratteri, lettere e numeri).");
            request.getRequestDispatcher("registrazione.jsp").forward(request, response);
            return;
        }


        Utente u = new Utente();
        u.setNome(nome);
        u.setCognome(cognome);
        u.setEmail(email);
        u.setTelefono(telefono);
        u.setPassword(password);
        u.setRuolo("CLIENTE");

        try {
            Connection conn = DbConnection.getInstance().getConnection();
            UtenteDAO utenteDAO = new UtenteDAO(conn);
            utenteDAO.insert(u);

            response.sendRedirect("login.jsp?registrazione=ok");

        } catch (SQLException e) {
            e.printStackTrace();
            String messaggioErrore = "Errore durante la registrazione.";

            if (e.getErrorCode() == 1062 || e.getMessage().contains("Duplicate")) {
                messaggioErrore = "Questa email è già registrata.";
            }

            request.setAttribute("errore", messaggioErrore);
            request.getRequestDispatcher("registrazione.jsp").forward(request, response);
        }
    }
}