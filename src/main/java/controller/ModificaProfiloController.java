package controller;

import dao.DbConnection;
import dao.UtenteDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/ModificaProfiloController")
public class ModificaProfiloController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");

        if (u == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Recupero nuovi dati
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");

        try {
            // Aggiorno l'oggetto in memoria
            u.setNome(nome);
            u.setCognome(cognome);
            u.setEmail(email);
            u.setTelefono(telefono);

            // Aggiorno nel DB
            Connection conn = DbConnection.getInstance().getConnection();
            UtenteDAO utenteDAO = new UtenteDAO(conn);

            // Metodo da aggiungere al DAO!
            utenteDAO.updateProfilo(u);

            // Aggiorno la sessione con i dati nuovi
            session.setAttribute("utente", u);

            response.sendRedirect("profiloCliente.jsp?msg=ProfiloAggiornato");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("profiloCliente.jsp?err=Errore");
        }
    }
}