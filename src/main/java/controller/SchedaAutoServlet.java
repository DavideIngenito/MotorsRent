package controller;

import dao.AutomobileDAO;
import dao.DbConnection;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Automobile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/schedaAuto")
public class SchedaAutoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idAuto = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = DbConnection.getConnection()) {

            AutomobileDAO autoDAO = new AutomobileDAO(conn);
            Automobile auto = autoDAO.getById(idAuto);

            request.setAttribute("auto", auto);
            request.getRequestDispatcher("schedaAuto.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
