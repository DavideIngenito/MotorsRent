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
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");
            String password = request.getParameter("password");

            Utente utenteAggiornato = new Utente();
            utenteAggiornato.setIdUtente(utenteSessione.getIdUtente());
            utenteAggiornato.setRuolo(utenteSessione.getRuolo());

            utenteAggiornato.setNome(nome);
            utenteAggiornato.setCognome(cognome);
            utenteAggiornato.setEmail(email);
            utenteAggiornato.setTelefono(telefono);
            utenteAggiornato.setPassword(password);

            Connection conn = DbConnection.getInstance().getConnection();
            UtenteDAO utenteDAO = new UtenteDAO(conn);

            boolean successo = utenteDAO.updateProfilo(utenteAggiornato);

            if (successo) {
                session.setAttribute("utente", utenteAggiornato);

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