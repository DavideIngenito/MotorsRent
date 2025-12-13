package controller;



import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import dao.UtenteDAO;
import model.Utente;

@WebServlet("/admin/venditori")
public class GestioneVenditoriServlet extends HttpServlet {

    private UtenteDAO utenteDAO = new UtenteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Utente> venditori = utenteDAO.findVenditori();
        request.setAttribute("venditori", venditori);

        RequestDispatcher rd =
                request.getRequestDispatcher("/jsp/admin/gestioneVenditori.jsp");
        rd.forward(request, response);
    }
}
