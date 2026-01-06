package controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Automobile;
import model.MotorsRentFacade; // Import del Facade
import model.Utente;

import java.io.IOException;
import java.util.List;
import java.util.Calendar;

@WebServlet("/AdminAutoController")
public class AdminAutoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");

        if (u == null || !u.getRuolo().equalsIgnoreCase("AMMINISTRATORE")) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) action = "list";

        // INTEGRAZIONE FACADE: Non serve più il try-catch per SQLException qui
        switch (action) {
            case "addForm":
                showAddForm(request, response);
                break;
            case "add":
                insertAuto(request, response);
                break;
            case "delete":
                deleteAuto(request, response);
                break;
            case "editForm":
                showEditForm(request, response);
                break;
            case "update":
                updateAuto(request, response);
                break;
            case "list":
            default:
                listAuto(request, response);
                break;
        }
    }

    // --- METODI DI SUPPORTO CON FACADE ---

    private void listAuto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Uso il Facade
        List<Automobile> listAuto = MotorsRentFacade.getInstance().getCatalogoAuto();
        request.setAttribute("listaAuto", listAuto);
        request.getRequestDispatcher("adminGestioneAuto.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("adminAggiungiAuto.jsp").forward(request, response);
    }

    private void insertAuto(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // ... (Logica di validazione invariata per brevità) ...
        String marca = request.getParameter("marca");
        String modello = request.getParameter("modello");
        String annoStr = request.getParameter("anno");
        String prezzoStr = request.getParameter("prezzo");
        String kmStr = request.getParameter("chilometraggio");
        String stato = request.getParameter("stato");
        String immagine = request.getParameter("immagine");
        String descrizione = request.getParameter("descrizione");

        // VALIDAZIONE (Esempio parziale, riprendi quella completa se necessario)
        if (marca == null || !marca.matches("^[a-zA-Z0-9\\s]+$")) {
            request.setAttribute("errore", "Marca non valida.");
            request.getRequestDispatcher("adminAggiungiAuto.jsp").forward(request, response);
            return;
        }

        // Parsing parametri (semplificato per l'esempio)
        int anno = Integer.parseInt(annoStr);
        double prezzo = Double.parseDouble(prezzoStr);
        int km = Integer.parseInt(kmStr);
        boolean disp = "1".equals(request.getParameter("disponibilita"));

        Automobile a = new Automobile();
        a.setMarca(marca);
        a.setModello(modello);
        a.setAnno(anno);
        a.setPrezzo(prezzo);
        a.setChilometraggio(km);
        a.setStato(stato);
        a.setDescrizione(descrizione);
        a.setImmagine(immagine);
        a.setDisponibilita(disp);

        // INTEGRAZIONE FACADE
        MotorsRentFacade.getInstance().aggiungiAuto(a);

        response.sendRedirect("AdminAutoController?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            // Uso il Facade
            Automobile auto = MotorsRentFacade.getInstance().getAutoById(id);
            request.setAttribute("auto", auto);
            request.getRequestDispatcher("adminModificaAuto.jsp").forward(request, response);
        } else {
            response.sendRedirect("AdminAutoController?action=list");
        }
    }

    private void updateAuto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Automobile a = new Automobile();
        a.setIdAuto(Integer.parseInt(request.getParameter("idAuto")));
        a.setMarca(request.getParameter("marca"));
        a.setModello(request.getParameter("modello"));
        a.setAnno(Integer.parseInt(request.getParameter("anno")));
        a.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
        a.setChilometraggio(Integer.parseInt(request.getParameter("chilometraggio")));
        a.setDescrizione(request.getParameter("descrizione"));
        a.setImmagine(request.getParameter("immagine"));
        a.setStato(request.getParameter("stato"));
        boolean disp = "1".equals(request.getParameter("disponibilita"));
        a.setDisponibilita(disp);

        // INTEGRAZIONE FACADE
        MotorsRentFacade.getInstance().aggiornaAuto(a);

        response.sendRedirect("AdminAutoController?action=list");
    }

    private void deleteAuto(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            // INTEGRAZIONE FACADE
            MotorsRentFacade.getInstance().eliminaAuto(Integer.parseInt(idStr));
        }
        response.sendRedirect("AdminAutoController?action=list");
    }
}