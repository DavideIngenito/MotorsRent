package controller;

import dao.DbConnection;
import dao.LeasingDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Leasing;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

@WebServlet("/richiestaLeasing")
public class RichiestaLeasingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Controllo Login
        HttpSession session = request.getSession();
        if (session.getAttribute("utente") == null) {
            response.sendRedirect("login.jsp?msg=Effettua il login per richiedere un leasing");
            return;
        }

        String idAuto = request.getParameter("idAuto");
        request.setAttribute("idAuto", idAuto);

        request.getRequestDispatcher("richiestaLeasing.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if (utente == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            // Recupero parametri
            int idAuto = Integer.parseInt(request.getParameter("idAuto")); // Assicurati di averlo nel form hidden!
            int durata = Integer.parseInt(request.getParameter("durata"));
            double anticipo = Double.parseDouble(request.getParameter("anticipo"));
            int kmAnnui = Integer.parseInt(request.getParameter("kmAnnui"));

            // Creazione Model
            Leasing l = new Leasing();
            l.setIdUtente(utente.getIdUtente());
            l.setIdAuto(idAuto);
            l.setDurataMesi(durata);
            l.setAnticipo(anticipo);
            l.setKmAnnui(kmAnnui);
            l.setDataRichiesta(new Timestamp(System.currentTimeMillis()));
            l.setStato("IN VALUTAZIONE");

            // Salvataggio
            Connection conn = DbConnection.getInstance().getConnection();
            LeasingDAO dao = new LeasingDAO(conn);
            dao.insert(l);

            response.sendRedirect("dashboardCliente?msg=Richiesta inviata");

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore nella compilazione della richiesta.");
            request.getRequestDispatcher("richiestaLeasing.jsp").forward(request, response);
        }
    }
}