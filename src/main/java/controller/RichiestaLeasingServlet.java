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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Utente utente = (session != null) ? (Utente) session.getAttribute("utente") : null;

        if (utente == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String idAuto = request.getParameter("idAuto");
        if (idAuto == null || idAuto.isEmpty()) {
            response.sendRedirect("catalogo");
            return;
        }

        request.getRequestDispatcher("richiestaLeasing.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Utente utente = (session != null) ? (Utente) session.getAttribute("utente") : null;

        if (utente == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String idAutoParam = request.getParameter("idAuto");
        if (idAutoParam == null || idAutoParam.isEmpty()) {
            response.sendRedirect("catalogo");
            return;
        }


        double anticipo;
        try {
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
            request.setAttribute("errore", "La durata inserita non è valida.");
            request.getRequestDispatcher("richiestaLeasing.jsp").forward(request, response);
            return;
        }


        try {
            Leasing l = new Leasing();
            l.setIdUtente(utente.getIdUtente());
            l.setIdAuto(Integer.parseInt(idAutoParam));
            l.setDurataMesi(durata);
            l.setAnticipo(anticipo);
            l.setKmAnnui(kmAnnui);
            l.setDataRichiesta(new Timestamp(System.currentTimeMillis()));
            l.setNote(request.getParameter("note"));
            l.setStato("IN VALUTAZIONE");

            Connection conn = DbConnection.getInstance().getConnection();
            LeasingDAO leasingDAO = new LeasingDAO(conn);
            leasingDAO.insert(l);

            response.sendRedirect("dashboardCliente?msg=LeasingRichiesto");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore di sistema durante il salvataggio: " + e.getMessage());
            request.getRequestDispatcher("richiestaLeasing.jsp").forward(request, response);
        }
    }
}