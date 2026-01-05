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

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("catalogo"); // Se manca l'ID, torna al catalogo
            return;
        }

        try {
            int idAuto = Integer.parseInt(idParam);

            Connection conn = DbConnection.getInstance().getConnection();
            AutomobileDAO autoDAO = new AutomobileDAO(conn);

            Automobile auto = autoDAO.getById(idAuto);

            if (auto == null) {
                response.sendError(404, "Automobile non trovata.");
                return;
            }

            request.setAttribute("auto", auto);
            RequestDispatcher dispatcher = request.getRequestDispatcher("schedaAuto.jsp");
            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(400, "ID Auto non valido.");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500, "Errore database.");
        }
    }
}