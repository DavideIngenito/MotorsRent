package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/richiestaPreventivo")
public class RichiestaPreventivoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idAuto = request.getParameter("idAuto");
        request.setAttribute("idAuto", idAuto);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/jsp/cliente/richiestaPreventivo.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idAuto = request.getParameter("idAuto");
        String note = request.getParameter("note");

        // Qui andrà la DAO PreventivoDAO.create(...)
        // PreventivoDAO.creaPreventivo(...)

        response.sendRedirect("statoPreventivi");
    }
}
