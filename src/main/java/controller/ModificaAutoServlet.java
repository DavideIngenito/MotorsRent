package controller;


import dao.DbConnection;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.AutomobileDAO;
import model.Automobile;

@WebServlet("/admin/auto/modifica")
public class ModificaAutoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idAuto = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = DbConnection.getConnection()) {

            AutomobileDAO autoDAO = new AutomobileDAO(conn);
            Automobile auto = autoDAO.getById(idAuto);

            request.setAttribute("auto", auto);
            request.getRequestDispatcher("/jsp/admin/modificaAuto.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
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

        try (Connection conn = DbConnection.getConnection()) {

            AutomobileDAO autoDAO = new AutomobileDAO(conn);
            autoDAO.update(auto);

            response.sendRedirect(request.getContextPath() + "/admin/auto/lista");

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

