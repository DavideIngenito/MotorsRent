package controller;

import dao.DbConnection;
import dao.PreventivoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Preventivo;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

@WebServlet("/richiestaPreventivo")
public class RichiestaPreventivoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Controllo se l'utente è loggato prima di mostrare il form
        HttpSession session = request.getSession();
        if (session.getAttribute("utente") == null) {
            response.sendRedirect("login.jsp?msg=Devi loggarti per chiedere un preventivo");
            return;
        }

        String idAuto = request.getParameter("idAuto");

        // Controllo validità ID
        if(idAuto == null || idAuto.isEmpty()) {
            response.sendRedirect("catalogo");
            return;
        }

        request.setAttribute("idAuto", idAuto);

        // ATTENZIONE: Se la JSP è nella cartella principale, usa solo il nome file
        RequestDispatcher dispatcher = request.getRequestDispatcher("richiestaPreventivo.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Controllo Sessione (Sicurezza)
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 2. Recupero Parametri
        String idAutoStr = request.getParameter("idAuto");
        String note = request.getParameter("note");

        try {
            int idAuto = Integer.parseInt(idAutoStr);

            // 3. Creazione Oggetto Model
            Preventivo p = new Preventivo();
            p.setIdUtente(utente.getIdUtente()); // Fondamentale!
            p.setIdAuto(idAuto);
            p.setNote(note);
            p.setDataPreventivo(new Timestamp(System.currentTimeMillis()));
            p.setStato("NUOVA"); // Impostiamo lo stato iniziale (come da tua classe String)

            // 4. Salvataggio tramite DAO e Singleton
            Connection conn = DbConnection.getInstance().getConnection();
            PreventivoDAO preventivoDAO = new PreventivoDAO(conn);

            preventivoDAO.insert(p); // Metodo che abbiamo corretto nel primo step

            // 5. Successo -> Rimando all'area personale (o lista preventivi)
            // Nota: Assicurati di avere una pagina dashboardCliente.jsp o simile
            response.sendRedirect("dashboardCliente.jsp?msg=Richiesta inviata con successo");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("catalogo");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore durante l'invio della richiesta.");
            request.setAttribute("idAuto", idAutoStr); // Rimetto l'id per non perdere il form
            request.getRequestDispatcher("richiestaPreventivo.jsp").forward(request, response);
        }
    }
}