package controller;

import dao.DbConnection;
import dao.PreventivoDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Preventivo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/gestisciPreventivo")
public class GestisciPreventivoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Recupero ID dalla query string (?id=5)
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect("venditorePreventivi");
            return;
        }

        try {
            // 2. Connessione Singleton
            Connection conn = DbConnection.getInstance().getConnection();
            PreventivoDAO preventivoDAO = new PreventivoDAO(conn);

            // 3. Recupero il preventivo COMPLETO (con dati utente e auto)
            // Questo metodo l'abbiamo aggiunto al DAO poco fa
            Preventivo p = preventivoDAO.getByIdCompleto(Integer.parseInt(idStr));

            if (p == null) {
                response.sendRedirect("venditorePreventivi");
                return;
            }

            // 4. Invio alla JSP di dettaglio
            request.setAttribute("preventivo", p);
            RequestDispatcher dispatcher = request.getRequestDispatcher("venditoreDettaglioPreventivo.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Errore nel recupero del preventivo.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Recupero parametri dal FORM (nomi presi dalla tua JSP)
        // Nota: nella JSP l'input hidden si chiama "idPreventivo", non "id"
        String idStr = request.getParameter("idPreventivo");
        String stato = request.getParameter("stato"); // "Inviato" o "Rifiutato"

        // Nota: Se vuoi salvare anche "importo" e "messaggio", dovresti aggiungere
        // i metodi nel DAO. Per ora aggiorniamo solo lo stato come da requisiti base.
        // String importo = request.getParameter("importo");
        // String messaggio = request.getParameter("messaggio");

        try {
            int idPreventivo = Integer.parseInt(idStr);

            Connection conn = DbConnection.getInstance().getConnection();
            PreventivoDAO preventivoDAO = new PreventivoDAO(conn);

            // 2. Aggiornamento
            preventivoDAO.updateStato(idPreventivo, stato);

            // 3. Redirect alla lista preventivi con messaggio di successo
            response.sendRedirect("venditorePreventivi?msg=Preventivo aggiornato con successo");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("venditorePreventivi?err=ID non valido");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Errore durante l'aggiornamento.");
        }
    }
}