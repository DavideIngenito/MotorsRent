package controller;



import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

import dao.AutomobileDAO;
import model.Automobile;

@WebServlet("/admin/auto/inserisci")
public class InserisciAutoServlet extends HttpServlet {

    private AutomobileDAO autoDAO = new AutomobileDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd =
                request.getRequestDispatcher("/jsp/admin/inserisciAuto.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Automobile auto = new Automobile();
        auto.setMarca(request.getParameter("marca"));
        auto.setModello(request.getParameter("modello"));
        auto.setAnno(Integer.parseInt(request.getParameter("anno")));
        auto.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
        auto.setChilometraggio(Integer.parseInt(request.getParameter("chilometraggio")));
        auto.setStato("Disponibile");

        autoDAO.insert(auto);

        response.sendRedirect(request.getContextPath() + "/admin/auto/lista");
    }
}
