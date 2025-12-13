package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/dashboardCliente")
public class DashboardClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("utente") == null) {
            response.sendRedirect("login");
            return;
        }

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/jsp/cliente/dashboardCliente.jsp");
        dispatcher.forward(request, response);
    }
}
