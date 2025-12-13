package controller;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/richiestaLeasing")
public class RichiestaLeasingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idAuto = request.getParameter("idAuto");
        request.setAttribute("idAuto", idAuto);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/jsp/cliente/richiestaLeasing.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int durata = Integer.parseInt(request.getParameter("durata"));
        double anticipo = Double.parseDouble(request.getParameter("anticipo"));
        int kmAnnui = Integer.parseInt(request.getParameter("kmAnnui"));

        // LeasingDAO.creaRichiesta(...)

        response.sendRedirect("statoLeasing");
    }
}
