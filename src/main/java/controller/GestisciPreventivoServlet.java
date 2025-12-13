package controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import dao.PreventivoDAO;
import model.Preventivo;

@WebServlet("/gestisciPreventivo")
public class GestisciPreventivoServlet extends HttpServlet {

    private PreventivoDAO preventivoDAO = new PreventivoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idPreventivo = Integer.parseInt(request.getParameter("id"));

        Preventivo preventivo = preventivoDAO.getPreventivoById(idPreventivo);

        request.setAttribute("preventivo", preventivo);
        request.getRequestDispatcher("/jsp/venditore/gestisciPreventivo.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idPreventivo = Integer.parseInt(request.getParameter("id"));
        String stato = request.getParameter("stato");
        String note = request.getParameter("note");

        preventivoDAO.aggiornaPreventivo(idPreventivo, stato, note);

        response.sendRedirect("dashboardVenditore");
    }
}