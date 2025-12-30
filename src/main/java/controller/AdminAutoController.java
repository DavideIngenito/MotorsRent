package controller;

import dao.AutomobileDAO;
import dao.DbConnection;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Automobile;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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

        // 1. SICUREZZA
        HttpSession session = request.getSession();
        Utente u = (Utente) session.getAttribute("utente");

        if (u == null || !u.getRuolo().equalsIgnoreCase("AMMINISTRATORE")) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) action = "list";

        try {
            Connection conn = DbConnection.getInstance().getConnection();
            AutomobileDAO autoDAO = new AutomobileDAO(conn);

            switch (action) {
                case "addForm":
                    showAddForm(request, response);
                    break;
                case "add":
                    insertAuto(request, response, autoDAO);
                    break;
                case "delete":
                    deleteAuto(request, response, autoDAO);
                    break;
                case "editForm":
                    showEditForm(request, response, autoDAO);
                    break;
                case "update":
                    updateAuto(request, response, autoDAO);
                    break;
                case "list":
                default:
                    listAuto(request, response, autoDAO);
                    break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.sendError(500, "Errore Database: " + ex.getMessage());
        }
    }

    // --- METODI DI SUPPORTO ---

    private void listAuto(HttpServletRequest request, HttpServletResponse response, AutomobileDAO dao)
            throws SQLException, ServletException, IOException {
        List<Automobile> listAuto = dao.getAll();
        request.setAttribute("listaAuto", listAuto);
        request.getRequestDispatcher("adminGestioneAuto.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("adminAggiungiAuto.jsp").forward(request, response);
    }

    // =================================================================================
    //  SEZIONE VALIDAZIONE PER TEST CASE 3.1 (Aggiunta Veicolo)
    // =================================================================================
    private void insertAuto(HttpServletRequest request, HttpServletResponse response, AutomobileDAO dao)
            throws SQLException, IOException, ServletException {

        // Recupero parametri
        String marca = request.getParameter("marca");
        String modello = request.getParameter("modello");
        String annoStr = request.getParameter("anno");
        String prezzoStr = request.getParameter("prezzo");
        String kmStr = request.getParameter("chilometraggio");
        String stato = request.getParameter("stato");
        String immagine = request.getParameter("immagine");
        String descrizione = request.getParameter("descrizione");

        // 1. Validazione Marca (TC_3.1_1)
        // Accetta lettere, numeri e spazi. Niente caratteri speciali come @.
        if (marca == null || !marca.matches("^[a-zA-Z0-9\\s]+$")) {
            request.setAttribute("errore", "L’aggiunta del veicolo non va a buon fine perché la marca non è in un formato valido.");
            request.getRequestDispatcher("adminAggiungiAuto.jsp").forward(request, response);
            return;
        }

        // 2. Validazione Modello (TC_3.1_2)
        // Accetta lettere, numeri e spazi. Niente simboli come ! o £.
        if (modello == null || !modello.matches("^[a-zA-Z0-9\\s]+$")) {
            request.setAttribute("errore", "L’aggiunta del veicolo non va a buon fine perché il modello non è in un formato valido.");
            request.getRequestDispatcher("adminAggiungiAuto.jsp").forward(request, response);
            return;
        }

        // 3. Validazione Anno (TC_3.1_3)
        // Deve essere un numero valido e verosimile (es. > 1900).
        int anno;
        try {
            anno = Integer.parseInt(annoStr);
            int annoCorrente = Calendar.getInstance().get(Calendar.YEAR);
            if (anno < 1900 || anno > annoCorrente + 1) { // Range realistico
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errore", "L’aggiunta del veicolo non va a buon fine perché l’anno non è in un formato valido.");
            request.getRequestDispatcher("adminAggiungiAuto.jsp").forward(request, response);
            return;
        }

        // 4. Validazione Prezzo (TC_3.1_4)
        // Deve essere positivo.
        double prezzo;
        try {
            prezzo = Double.parseDouble(prezzoStr);
            if (prezzo < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            request.setAttribute("errore", "L’aggiunta del veicolo non va a buon fine perché il prezzo non è in un formato valido.");
            request.getRequestDispatcher("adminAggiungiAuto.jsp").forward(request, response);
            return;
        }

        // 5. Validazione Chilometraggio (TC_3.1_5)
        // Non può essere negativo.
        int km;
        try {
            km = Integer.parseInt(kmStr);
            if (km < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            request.setAttribute("errore", "L’aggiunta del veicolo non va a buon fine perché il chilometraggio non è in un formato valido.");
            request.getRequestDispatcher("adminAggiungiAuto.jsp").forward(request, response);
            return;
        }

        // 6. Validazione Stato (TC_3.1_6)
        // Deve essere "Nuova" o "Usata".
        if (stato == null || (!stato.equalsIgnoreCase("Nuova") && !stato.equalsIgnoreCase("Usata"))) {
            request.setAttribute("errore", "L’aggiunta del veicolo non va a buon fine perché lo stato non è ammesso.");
            request.getRequestDispatcher("adminAggiungiAuto.jsp").forward(request, response);
            return;
        }

        // --- Se arriviamo qui, l'input è valido (TC_3.1_7) ---

        Automobile a = new Automobile();
        a.setMarca(marca);
        a.setModello(modello);
        a.setAnno(anno);
        a.setPrezzo(prezzo);
        a.setChilometraggio(km);
        a.setStato(stato);
        a.setDescrizione(descrizione);
        a.setImmagine(immagine);

        boolean disp = "1".equals(request.getParameter("disponibilita"));
        a.setDisponibilita(disp);

        dao.insert(a);
        response.sendRedirect("AdminAutoController?action=list");
    }

    // Nota: Dovresti applicare una logica simile anche a updateAuto per la Sezione 3.2 del TCS
    private void showEditForm(HttpServletRequest request, HttpServletResponse response, AutomobileDAO dao)
            throws SQLException, ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            Automobile auto = dao.getById(id);
            request.setAttribute("auto", auto);
            request.getRequestDispatcher("adminModificaAuto.jsp").forward(request, response);
        } else {
            response.sendRedirect("AdminAutoController?action=list");
        }
    }

    private void updateAuto(HttpServletRequest request, HttpServletResponse response, AutomobileDAO dao)
            throws SQLException, IOException {
        // Implementazione base (migliorabile con validazione simile a insertAuto)
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

        dao.update(a);
        response.sendRedirect("AdminAutoController?action=list");
    }

    private void deleteAuto(HttpServletRequest request, HttpServletResponse response, AutomobileDAO dao)
            throws SQLException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            dao.delete(Integer.parseInt(idStr));
        }
        response.sendRedirect("AdminAutoController?action=list");
    }
}