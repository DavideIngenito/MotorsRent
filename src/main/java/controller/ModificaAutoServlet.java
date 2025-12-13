package controller;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

import dao.AutomobileDAO;
import model.Automobile;

@WebServlet("/admin/auto/modifica")
public class ModificaAutoServlet extends HttpServlet {

    private Automobile autoDAO = new AutomobileDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idAuto = Integer.parseInt(request.getParameter("id"));
        Automobile auto = autoDAO.findById(idAuto);

        request.setAttribute("auto", auto);
        RequestDispatcher rd =
                request.getRequestDispatcher("/jsp/admin/modificaAuto.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Automobile auto = new Automobile();
        auto.setIdAuto(Integer.parseInt(request.getParameter("idAuto")));
        auto.setMarca(request.getParameter("marca"));
        auto.setModello(request.getParameter("modello"));
        auto.setAnno(Integer.parseInt(request.getParameter("anno")));
        auto.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
        auto.setChilometraggio(Integer.parseInt(request.getParameter("chilometraggio")));
        auto.setStato(request.getParameter("stato"));

        autoDAO.update(auto);

        response.sendRedirect(request.getContextPath() + "/admin/auto/lista");
    }
}
