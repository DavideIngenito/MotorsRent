package controller;


import dao.DbConnection;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.AutomobileDAO;
import model.Automobile;

@WebServlet("/admin/auto/lista")
public class ListaAutoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection conn = DbConnection.getConnection()) {

            AutomobileDAO autoDAO = new AutomobileDAO(conn);
            List<Automobile> autoList = autoDAO.getAll();

            request.setAttribute("autoList", autoList);
            request.getRequestDispatcher("/jsp/admin/listaAuto.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

