package controller;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import dao.LeasingDAO;
import model.Leasing;

@WebServlet("/gestisciLeasing")
public class GestisciLeasingServlet extends HttpServlet {

    private LeasingDAO leasingDAO = new LeasingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idLeasing = Integer.parseInt(request.getParameter("id"));

        Leasing leasing = leasingDAO.getLeasingById(idLeasing);

        request.setAttribute("leasing", leasing);
        request.getRequestDispatcher("/jsp/venditore/gestisciLeasing.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idLeasing = Integer.parseInt(request.getParameter("id"));
        String stato = request.getParameter("stato");

        leasingDAO.aggiornaStatoLeasing(idLeasing, stato);

        response.sendRedirect("dashboardVenditore");
    }
}
