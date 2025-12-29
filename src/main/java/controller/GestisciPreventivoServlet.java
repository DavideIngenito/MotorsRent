package controller;

import dao.DbConnection;
import dao.PreventivoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/gestisciPreventivo")
public class GestisciPreventivoServlet extends HttpServlet {

    // Se arrivo in GET (cliccando "Gestisci" dalla lista), mostro il dettaglio
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null) { response.sendRedirect("venditorePreventivi"); return; }

        try {
            int id = Integer.parseInt(idStr);
            Connection conn = DbConnection.getInstance().getConnection();
            PreventivoDAO dao = new PreventivoDAO(conn);

            // Carica il preventivo e lo manda alla JSP di dettaglio
            request.setAttribute("preventivo", dao.getById(id));
            request.getRequestDispatcher("venditoreDettaglioPreventivo.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Errore caricamento preventivo");
        }
    }

    // Se arrivo in POST (submit del form di risposta), salvo i dati
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("idPreventivo"));
            double importo = Double.parseDouble(request.getParameter("importo")); // Il prezzoProposto
            String stato = request.getParameter("stato");
            String messaggio = request.getParameter("messaggio"); // Le note

            Connection conn = DbConnection.getInstance().getConnection();
            PreventivoDAO dao = new PreventivoDAO(conn);

            // Chiamata al nuovo metodo che salva tutto
            dao.gestisciRisposta(id, stato, importo, messaggio);

            // Torna alla lista
            response.sendRedirect("venditorePreventivi");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Errore salvataggio risposta");
        }
    }
}