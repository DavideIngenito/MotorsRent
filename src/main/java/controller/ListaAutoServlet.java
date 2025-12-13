package controller;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import dao.AutomobileDAO;
import model.Automobile;

@WebServlet("/admin/auto/lista")
public class ListaAutoServlet extends HttpServlet {

    private AutomobileDAO autoDAO = new AutomobileDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Automobile> autoList = autoDAO.findAll();
        request.setAttribute("autoList", autoList);

        RequestDispatcher rd =
                request.getRequestDispatcher("/jsp/admin/listaAuto.jsp");
        rd.forward(request, response);
    }
}
