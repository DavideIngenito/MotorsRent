package controller;

import dao.DbConnection;
import dao.UtenteDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/registrazione")
public class RegistrazioneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Mostra il form di registrazione
        RequestDispatcher dispatcher = request.getRequestDispatcher("registrazione.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Recupero parametri dal form
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String password = request.getParameter("password");

        // -------------------------------------------------------------------------
        //  SEZIONE VALIDAZIONE (Test Case Specification 1.1.1)
        // -------------------------------------------------------------------------

        // 1. Validazione Nome (TC_1.1_1)
        // Deve contenere solo lettere e spazi. Esempio non valido: "<Giuseppe"
        if (nome == null || !nome.matches("^[a-zA-Z\\s]+$")) {
            request.setAttribute("errore", "La registrazione non va a buon fine perché il nome non è in un formato valido.");
            request.getRequestDispatcher("registrazione.jsp").forward(request, response);
            return;
        }

        // 2. Validazione Cognome (TC_1.1_2)
        // Deve contenere solo lettere e spazi. Esempio non valido: "Nappo>"
        if (cognome == null || !cognome.matches("^[a-zA-Z\\s]+$")) {
            request.setAttribute("errore", "La registrazione non va a buon fine perché il cognome non è in un formato valido.");
            request.getRequestDispatcher("registrazione.jsp").forward(request, response);
            return;
        }

        // 3. Validazione Email (TC_1.1_3)
        // Deve rispettare il formato standard email. Esempio non valido: "g. nappo89libero.it"
        if (email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            request.setAttribute("errore", "La registrazione non va a buon fine perché l’e-mail non è in un formato valido.");
            request.getRequestDispatcher("registrazione.jsp").forward(request, response);
            return;
        }

        // 4. Validazione Telefono (TC_1.1_4)
        // Deve essere composto da esattamente 10 cifre. Esempio non valido: "33345642145544?"
        if (telefono == null || !telefono.matches("^\\d{10}$")) {
            request.setAttribute("errore", "La registrazione non va a buon fine perché il telefono non è in un formato valido.");
            request.getRequestDispatcher("registrazione.jsp").forward(request, response);
            return;
        }

        // 5. Validazione Password (TC_1.1_5)
        // Deve contenere almeno una lettera e un numero, lunghezza minima 8.
        // Esempio non valido: "pe9======…dfrfg" (che potrebbe non rispettare pattern di sicurezza)
        // La regex qui sotto impone: almeno 8 caratteri, almeno una lettera, almeno un numero.
        if (password == null || !password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*?&.]{8,}$")) {
            request.setAttribute("errore", "La registrazione non va a buon fine perché la password non è in un formato valido (Richiesti min. 8 caratteri, lettere e numeri).");
            request.getRequestDispatcher("registrazione.jsp").forward(request, response);
            return;
        }

        // -------------------------------------------------------------------------
        //  FINE VALIDAZIONE - PROCEDIAMO CON LA LOGICA DI BUSINESS
        // -------------------------------------------------------------------------

        // 2. Creazione oggetto Model
        Utente u = new Utente();
        u.setNome(nome);
        u.setCognome(cognome);
        u.setEmail(email);
        u.setTelefono(telefono);
        u.setPassword(password); // Nota: In un caso reale, qui si dovrebbe fare l'hash della password
        u.setRuolo("CLIENTE");   // Ruolo di default per chi si registra dal sito

        try {
            // 3. Connessione e DAO
            Connection conn = DbConnection.getInstance().getConnection();
            UtenteDAO utenteDAO = new UtenteDAO(conn);

            // 4. Inserimento nel database
            utenteDAO.insert(u);

            // 5. Successo -> Reindirizzamento al Login con messaggio di conferma
            response.sendRedirect("login.jsp?registrazione=ok");

        } catch (SQLException e) {
            e.printStackTrace();
            String messaggioErrore = "Errore durante la registrazione.";

            // Gestione specifica per email duplicata (codice errore MySQL 1062 o messaggio "Duplicate")
            if (e.getErrorCode() == 1062 || e.getMessage().contains("Duplicate")) {
                messaggioErrore = "Questa email è già registrata.";
            }

            request.setAttribute("errore", messaggioErrore);
            request.getRequestDispatcher("registrazione.jsp").forward(request, response);
        }
    }
