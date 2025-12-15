package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList; // Immagina che qui ci siano i tuoi modelli

// Unico punto di accesso per il catalogo
@WebServlet(name = "CatalogoServlet", value = "/catalogo")
public class CatalogoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. Recupero la sessione per capire chi è collegato
        HttpSession session = request.getSession(false); // false = non crearne una nuova se non esiste

        // Qui dovresti avere il tuo oggetto Utente salvato in sessione al login
        // String ruolo = (session != null && session.getAttribute("ruolo") != null) ? (String) session.getAttribute("ruolo") : "GUEST";

        // ESEMPIO SEMPLIFICATO PER IL TEST:
        String ruolo = "CLIENTE"; // Cambia questo in "ADMIN" per testare l'altro caso

        System.out.println("Utente loggato come: " + ruolo);

        // 2. Recupero i dati (Auto/Moto) dal Service/Database
        // List<Auto> listaVeicoli = service.getVeicoliDisponibili();
        // request.setAttribute("veicoli", listaVeicoli);

        request.setAttribute("messaggio", "Lista caricata dal database...");

        // 3. LOGICA DI SMISTAMENTO (ROUTING)
        if ("AMMINISTRATORE".equals(ruolo)) {
            // L'admin NON vede il catalogo commerciale, ma la gestione
            System.out.println("Reindirizzo Admin alla dashboard di gestione");
            request.getRequestDispatcher("adminGestioneAuto.jsp").forward(request, response);
        } else {
            // Il cliente (o utente non loggato) vede il catalogo per noleggiare
            System.out.println("Mostro il catalogo al cliente");
            request.getRequestDispatcher("catalogo.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}