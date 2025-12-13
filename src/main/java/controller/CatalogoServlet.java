package controller;

import dao.AutomobileDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Automobile;

import java.io.IOException;
import java.util.List;



@WebServlet("/catalogo")
    public class CatalogoServlet extends HttpServlet {

        private AutomobileDAO autoDAO = new AutomobileDAO();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            List<Automobile> listaAuto = autoDAO.getAll();
            request.setAttribute("autoList", listaAuto);

            RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo.jsp");
            dispatcher.forward(request, response);
        }
    }



















