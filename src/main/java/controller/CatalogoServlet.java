package controller;

import dao.AutomobileDAO;
import dao.DbConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Automobile;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CatalogoServlet", value = "/catalogo")
public class CatalogoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Connection conn = DbConnection.getInstance().getConnection();
            AutomobileDAO autoDAO = new AutomobileDAO(conn);

            // --- 1. RECUPERO E CONVERSIONE PARAMETRI ---

            // Marca
            String marca = request.getParameter("marca");

            // Prezzo
            String prezzoStr = request.getParameter("prezzoMax");
            Double prezzoMax = null;
            if (prezzoStr != null && !prezzoStr.isEmpty()) {
                try {
                    prezzoMax = Double.parseDouble(prezzoStr);
                } catch (NumberFormatException e) { /* Ignora errore */ }
            }

            // Anni (Gestione Multipla)
            String[] anniStrArray = request.getParameterValues("anno");
            List<Integer> anniList = new ArrayList<>();
            if (anniStrArray != null) {
                for (String s : anniStrArray) {
                    if (s != null && !s.isEmpty()) {
                        try {
                            anniList.add(Integer.parseInt(s));
                        } catch (NumberFormatException e) { /* Ignora errore */ }
                    }
                }
            }
            // Se la lista è vuota, la settiamo a null così il DAO non filtra per anno
            if (anniList.isEmpty()) {
                anniList = null;
            }

            // Chilometraggio
            String kmStr = request.getParameter("kmMax");
            Integer kmMax = null;
            if (kmStr != null && !kmStr.isEmpty()) {
                try {
                    kmMax = Integer.parseInt(kmStr);
                } catch (NumberFormatException e) { /* Ignora errore */ }
            }

            // Stato ("Nuova" o "Usata")
            String stato = request.getParameter("stato");

            // Disponibilità
            String dispStr = request.getParameter("disponibilita");
            Boolean disponibilita = (dispStr != null && dispStr.equals("true"));

            // --- 2. LOGICA DI FILTRAGGIO ---

            List<Automobile> veicoli;

            // Verifichiamo se l'utente ha impostato almeno un filtro
            boolean filtriAttivi = (marca != null && !marca.isEmpty()) ||
                    prezzoMax != null ||
                    anniList != null ||
                    kmMax != null ||
                    (stato != null && !stato.isEmpty()) ||
                    disponibilita;

            if (filtriAttivi) {
                // Chiamata al nuovo metodo DAO con tutti i parametri
                veicoli = autoDAO.ricercaAvanzata(marca, prezzoMax, anniList, kmMax, stato, disponibilita);
            } else {
                // Nessun filtro: mostra tutto
                veicoli = autoDAO.getAll();
            }

            request.setAttribute("autoList", veicoli);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore nel caricamento del catalogo.");
        }

        // --- 3. REINDIRIZZAMENTO IN BASE AL RUOLO ---
        HttpSession session = request.getSession(false);
        Utente utente = (session != null) ? (Utente) session.getAttribute("utente") : null;
        String ruolo = (utente != null) ? utente.getRuolo() : "GUEST";

        if ("ADMIN".equalsIgnoreCase(ruolo) || "AMMINISTRATORE".equalsIgnoreCase(ruolo)) {
            // L'admin viene reindirizzato alla gestione
            response.sendRedirect("AdminAutoController?action=list");
        } else {
            // Clienti e ospiti vedono il catalogo JSP
            request.getRequestDispatcher("catalogo.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}