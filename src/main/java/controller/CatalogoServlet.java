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

        // 1. RECUPERO AUTO DAL DATABASE (Per tutti: admin, clienti e ospiti)
        try {
            Connection conn = DbConnection.getInstance().getConnection();
            AutomobileDAO autoDAO = new AutomobileDAO(conn);

            // Se ci sono parametri di filtro (dalla JSP catalogo.jsp)
            String marca = request.getParameter("marca");
            String prezzoStr = request.getParameter("prezzoMax");

            List<Automobile> veicoli;

            if ((marca != null && !marca.isEmpty()) || (prezzoStr != null && !prezzoStr.isEmpty())) {
                // Se l'utente sta filtrando
                double prezzo = (prezzoStr != null && !prezzoStr.isEmpty()) ? Double.parseDouble(prezzoStr) : 1000000;
                veicoli = autoDAO.cercaAuto(marca, prezzo);
            } else {
                // Lista completa standard
                veicoli = autoDAO.getAll();
            }

            // --- PUNTO CRITICO: Il nome deve essere "autoList" per corrispondere alla tua JSP ---
            request.setAttribute("autoList", veicoli);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errore", "Impossibile caricare il catalogo.");
        } catch (NumberFormatException e) {
            // Gestione errore se il prezzo non è un numero
        }

        // 2. CONTROLLO RUOLO UTENTE
        HttpSession session = request.getSession(false);
        Utente utente = (session != null) ? (Utente) session.getAttribute("utente") : null;
        String ruolo = (utente != null) ? utente.getRuolo() : "GUEST";

        // 3. INDIRIZZAMENTO (ROUTING)
        if ("ADMIN".equalsIgnoreCase(ruolo) || "AMMINISTRATORE".equalsIgnoreCase(ruolo)) {
            // L'admin non vede il catalogo visuale, ma la tabella di gestione
            // Nota: nella tua AdminAutoController usi "listaAuto", qui dobbiamo stare attenti.
            // Se mandi l'admin alla JSP di gestione, meglio fare un redirect al controller admin
            response.sendRedirect("AdminAutoController?action=list");
        } else {
            // Cliente e Ospite vedono il catalogo visivo
            request.getRequestDispatcher("catalogo.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}