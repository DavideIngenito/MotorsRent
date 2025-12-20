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
import java.util.List;

@WebServlet(name = "CatalogoServlet", value = "/catalogo")
public class CatalogoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Connection conn = DbConnection.getInstance().getConnection();
            AutomobileDAO autoDAO = new AutomobileDAO(conn);

            // --- 1. RECUPERO PARAMETRI DALLA JSP ---

            // Marca (Stringa)
            String marca = request.getParameter("marca");

            // Prezzo (Double)
            String prezzoStr = request.getParameter("prezzoMax");
            Double prezzoMax = null;
            if (prezzoStr != null && !prezzoStr.isEmpty()) {
                try {
                    prezzoMax = Double.parseDouble(prezzoStr);
                } catch (NumberFormatException e) {
                    // Se arriva spazzatura, lo ignoriamo lasciandolo null
                }
            }

            // Anno (Integer)
            // Nota: Se ci sono checkbox multiple con lo stesso nome "anno",
            // request.getParameter prende solo la prima. Per filtri semplici va bene così.
            String annoStr = request.getParameter("anno");
            Integer anno = null;
            if (annoStr != null && !annoStr.isEmpty()) {
                try {
                    anno = Integer.parseInt(annoStr);
                } catch (NumberFormatException e) {}
            }

            // Chilometraggio (Integer)
            String kmStr = request.getParameter("kmMax");
            Integer kmMax = null;
            if (kmStr != null && !kmStr.isEmpty()) {
                try {
                    kmMax = Integer.parseInt(kmStr);
                } catch (NumberFormatException e) {}
            }

            // Stato (String - "Nuova", "Usata" o null/vuoto)
            String stato = request.getParameter("stato");

            // Disponibilità (Boolean)
            // Le checkbox inviano "true" se selezionate, null se non selezionate
            String dispStr = request.getParameter("disponibilita");
            Boolean disponibilita = (dispStr != null && dispStr.equals("true"));

            // --- 2. CHIAMATA AL DAO ---
            List<Automobile> veicoli;

            // Se almeno uno dei filtri è presente, usiamo la ricerca avanzata
            boolean filtriAttivi = (marca != null && !marca.isEmpty()) ||
                    prezzoMax != null ||
                    anno != null ||
                    kmMax != null ||
                    (stato != null && !stato.isEmpty()) ||
                    disponibilita;

            if (filtriAttivi) {
                veicoli = autoDAO.ricercaAvanzata(marca, prezzoMax, anno, kmMax, stato, disponibilita);
            } else {
                // Nessun filtro: restituisci tutto
                veicoli = autoDAO.getAll();
            }

            // --- 3. INVIO DATI ALLA JSP ---
            request.setAttribute("autoList", veicoli);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore nel caricamento del catalogo: " + e.getMessage());
        }

        // --- 4. GESTIONE UTENTE E REINDIRIZZAMENTO ---
        HttpSession session = request.getSession(false);
        Utente utente = (session != null) ? (Utente) session.getAttribute("utente") : null;
        String ruolo = (utente != null) ? utente.getRuolo() : "GUEST";

        if ("ADMIN".equalsIgnoreCase(ruolo) || "AMMINISTRATORE".equalsIgnoreCase(ruolo)) {
            // L'admin va alla gestione
            response.sendRedirect("AdminAutoController?action=list");
        } else {
            // Clienti e Ospiti vedono il catalogo
            request.getRequestDispatcher("catalogo.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}