package controller;



import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/statoLeasing")
public class StatoLeasingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int idUtente = (int) session.getAttribute("idUtente");

        // List<Leasing> lista = LeasingDAO.getByUtente(idUtente);
        // request.setAttribute("leasing", lista);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/jsp/cliente/statoLeasing.jsp");
        dispatcher.forward(request, response);
    }
}
