package controller;

import dao.DbConnection;
import dao.LeasingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Leasing;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;

@WebServlet("/richiestaLeasing")
public class RichiestaLeasingServlet extends HttpServlet {

    /**
     * GESTISCE LA RICHIESTA GET (Click sul link "Simula Leasing")
     * Mostra la pagina del form.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. Controllo Login
        HttpSession session = request.getSession(false);
        Utente utente = (session != null) ? (Utente) session.getAttribute("utente") : null;

        if (utente == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 2. Controllo ID Auto
        String idAuto = request.getParameter("idAuto");
        if (idAuto == null || idAuto.isEmpty()) {
            response.sendRedirect("catalogo");
            return;
        }

        // 3. Mostra la JSP
        request.getRequestDispatcher("richiestaLeasing.jsp").forward(request, response);
    }

    /**
     * GESTISCE LA RICHIESTA POST (Click su "Invia Richiesta Leasing")
     * Esegue i controlli del TCS 6.1 e salva i dati.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Utente utente = (session != null) ? (Utente) session.getAttribute("utente") : null;

        if (utente == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Recuperiamo idAuto subito per poter ricaricare la pagina in caso di errore
        String idAutoParam = request.getParameter("idAuto");
        if (idAutoParam == null || idAutoParam.isEmpty()) {
            response.sendRedirect("catalogo");
            return;
        }

        // --------------------------------------------------------
        // VALIDAZIONE (Test Case Specification 6.1)
        // --------------------------------------------------------

        double anticipo;
        try {
            // TC_6.1_1: Validazione Anticipo (Non può essere negativo)
            anticipo = Double.parseDouble(request.getParameter("anticipo"));
            if (anticipo < 0) {
                throw new NumberFormatException("Negativo");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errore", "La richiesta non va a buon fine perché l’anticipo non è in un formato valido.");
            request.getRequestDispatcher("richiestaLeasing.jsp").forward(request, response);
            return;
        }

        int kmAnnui;
        try {
            // TC_6.1_2: Validazione Km Annui (Devono essere positivi)
            kmAnnui = Integer.parseInt(request.getParameter("kmAnnui"));
            if (kmAnnui <= 0) {
                throw new NumberFormatException("Negativo o Zero");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errore", "La richiesta non va a buon fine perché i km annui non sono in un formato valido.");
            request.getRequestDispatcher("richiestaLeasing.jsp").forward(request, response);
            return;
        }

        int durata;
        try {
            durata = Integer.parseInt(request.getParameter("durata"));
            if (durata <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            // Controllo di sicurezza extra per la durata
            request.setAttribute("errore", "La durata inserita non è valida.");
            request.getRequestDispatcher("richiestaLeasing.jsp").forward(request, response);
            return;
        }

        // --------------------------------------------------------
        // FINE VALIDAZIONE - SALVATAGGIO DATI
        // --------------------------------------------------------

        try {
            Leasing l = new Leasing();
            l.setIdUtente(utente.getIdUtente());
            l.setIdAuto(Integer.parseInt(idAutoParam));
            l.setDurataMesi(durata);
            l.setAnticipo(anticipo);
            l.setKmAnnui(kmAnnui);
            l.setDataRichiesta(new Timestamp(System.currentTimeMillis()));
            l.setNote(request.getParameter("note"));
            l.setStato("IN VALUTAZIONE"); // Stato iniziale standard

            Connection conn = DbConnection.getInstance().getConnection();
            LeasingDAO leasingDAO = new LeasingDAO(conn);
            leasingDAO.insert(l);

            // TC_6.1_3: Successo
            response.sendRedirect("dashboardCliente?msg=LeasingRichiesto");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore di sistema durante il salvataggio: " + e.getMessage());
            request.getRequestDispatcher("richiestaLeasing.jsp").forward(request, response);
        }
    }
}