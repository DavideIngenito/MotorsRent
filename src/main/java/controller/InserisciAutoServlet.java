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

@WebServlet("/admin/auto/inserisci")
public class InserisciAutoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/jsp/admin/inserisciAuto.jsp")
                .forward(request, response);
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

        try (Connection conn = DbConnection.getConnection()) {

            AutomobileDAO autoDAO = new AutomobileDAO(conn);
            autoDAO.insert(auto);

            response.sendRedirect(request.getContextPath() + "/admin/auto/lista");

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

