package controller;

import dao.AutomobileDAO;
import jakarta.servlet.;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.;
import model.Automobile;

import java.io.IOException;

@WebServlet("/schedaAuto")
public class SchedaAutoServlet extends HttpServlet {

    private AutomobileDAO autoDAO = new AutomobileDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idAuto = Integer.parseInt(request.getParameter("id"));

        Automobile auto = autoDAO.getById(int id);
        request.setAttribute("auto", auto);

        RequestDispatcher dispatcher = request.getRequestDispatcher("schedaAuto.jsp");
        dispatcher.forward(request, response);
    }
}
