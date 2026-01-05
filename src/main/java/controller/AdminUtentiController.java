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
import java.util.List;

@WebServlet("/AdminUtentiController")
public class AdminUtentiController extends HttpServlet {

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
        if (action == null) action = "listVenditori";

        try {
            Connection conn = DbConnection.getInstance().getConnection();
            UtenteDAO utenteDAO = new UtenteDAO(conn);

            switch (action) {
                case "addVenditore":
                    Utente newU = new Utente();
                    newU.setNome(request.getParameter("nome"));
                    newU.setCognome(request.getParameter("cognome"));
                    newU.setEmail(request.getParameter("email"));
                    newU.setPassword(request.getParameter("password"));
                    newU.setTelefono("0000000000"); // Default
                    newU.setRuolo("VENDITORE");

                    utenteDAO.insert(newU);
                    response.sendRedirect("AdminUtentiController?action=listVenditori");
                    break;

                case "deleteVenditore":
                    int id = Integer.parseInt(request.getParameter("id"));
                    utenteDAO.delete(id);
                    response.sendRedirect("AdminUtentiController?action=listVenditori");
                    break;

                case "listVenditori":
                default:
                    List<Utente> list = utenteDAO.findVenditori();
                    request.setAttribute("listaVenditori", list);
                    request.getRequestDispatcher("adminGestioneUtenti.jsp").forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            response.sendError(500, "Errore gestione venditori");
        }
    }
}