package controller;

import dao.UtenteDAO;
import jakarta.servlet.;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.;
import model.Utente;

import java.io.IOException;

@WebServlet("/registrazione")
public class RegistrazioneServlet extends HttpServlet {

    private UtenteDAO utenteDAO = new UtenteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("registrazione.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Utente u = new Utente();
        u.setNome(request.getParameter("nome"));
        u.setCognome(request.getParameter("cognome"));
        u.setEmail(request.getParameter("email"));
        u.setPassword(request.getParameter("password"));
        u.setRuolo("CLIENTE");

        utenteDAO.registraUtente(u);

        response.sendRedirect("login");
    }
}
