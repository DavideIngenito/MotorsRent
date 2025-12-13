package controller;

import dao.PreventivoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Preventivo;

import java.io.IOException;
import java.util.List;

@WebServlet("/statoPreventivi")
public class StatoPreventivoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int idUtente = (int) session.getAttribute("idUtente");

        List<Preventivo> lista = PreventivoDAO.getByUser(idUtente);
         request.setAttribute("preventivi", lista);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/jsp/cliente/statoPreventivi.jsp");
        dispatcher.forward(request, response);
    }
}
