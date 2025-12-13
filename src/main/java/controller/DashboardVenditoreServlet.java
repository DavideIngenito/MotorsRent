package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

import dao.PreventivoDAO;
import dao.LeasingDAO;
import model.Preventivo;
import model.Leasing;

@WebServlet("/dashboardVenditore")
public class DashboardVenditoreServlet extends HttpServlet {

    private PreventivoDAO preventivoDAO = new PreventivoDAO();
    private LeasingDAO leasingDAO = new LeasingDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Preventivo> preventivi = preventivoDAO.getAll();
        List<Leasing> leasingList = leasingDAO.getAll();

        request.setAttribute("preventivi", preventivi);
        request.setAttribute("leasingList", leasingList);

        request.getRequestDispatcher("/jsp/venditore/dashboardVenditore.jsp")
                .forward(request, response);
    }
}
