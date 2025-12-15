package controller;

import dao.AutomobileDAO;
import dao.DbConnection;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Automobile;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/DettaglioAutoClienteController")
public class DettaglioAutoClienteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        try {
            int id = Integer.parseInt(idStr);
            Connection conn = DbConnection.getInstance().getConnection();
            AutomobileDAO autoDAO = new AutomobileDAO(conn);

            Automobile auto = autoDAO.getById(id);

            request.setAttribute("auto", auto);
            // Questa JSP mancava, la creiamo sotto!
            request.getRequestDispatcher("dettaglioAuto.jsp").forward(request, response);

        } catch (Exception e) {
            response.sendRedirect("catalogo");
        }
    }
}