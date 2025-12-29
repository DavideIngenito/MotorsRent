package controller;

import dao.DbConnection;
import dao.UtenteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/modificaProfilo")
public class ModificaProfiloController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Utente utenteSessione = (session != null) ? (Utente) session.getAttribute("utente") : null;

        if (utenteSessione == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // 1. Recupera i nuovi dati dal form
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");
            String password = request.getParameter("password");

            // 2. Crea un oggetto Utente aggiornato (mantenendo l'ID originale)
            Utente utenteAggiornato = new Utente();
            utenteAggiornato.setIdUtente(utenteSessione.getIdUtente());
            utenteAggiornato.setRuolo(utenteSessione.getRuolo()); // Il ruolo non cambia

            utenteAggiornato.setNome(nome);
            utenteAggiornato.setCognome(cognome);
            utenteAggiornato.setEmail(email);
            utenteAggiornato.setTelefono(telefono);
            utenteAggiornato.setPassword(password);

            // 3. Salva nel Database
            Connection conn = DbConnection.getInstance().getConnection();
            UtenteDAO utenteDAO = new UtenteDAO(conn);

            boolean successo = utenteDAO.updateProfilo(utenteAggiornato);

            if (successo) {
                // 4. IMPORTANTE: Aggiorna l'utente in sessione per vedere subito le modifiche
                session.setAttribute("utente", utenteAggiornato);

                // Redirect con messaggio di successo
                response.sendRedirect("profiloCliente.jsp?msg=Profilo aggiornato con successo");
            } else {
                response.sendRedirect("profiloCliente.jsp?error=Errore durante l'aggiornamento");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Errore interno durante la modifica profilo.");
        }
    }
}