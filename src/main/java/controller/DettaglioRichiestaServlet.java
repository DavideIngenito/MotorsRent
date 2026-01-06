package controller;

import dao.DbConnection;
import dao.LeasingDAO;     // Assumo tu abbia questo DAO
import dao.PreventivoDAO;  // Assumo tu abbia questo DAO
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Leasing;
import model.Preventivo;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/dettaglioRichiesta")
public class DettaglioRichiestaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String tipo = request.getParameter("tipo");
        String idStr = request.getParameter("id");

        if (tipo == null || idStr == null) {
            response.sendRedirect("dashboardCliente");
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            Connection conn = DbConnection.getInstance().getConnection();

            if ("preventivo".equals(tipo)) {
                PreventivoDAO preventivoDAO = new PreventivoDAO(conn);
                Preventivo p = preventivoDAO.getById(id);

                request.setAttribute("richiesta", p);
                request.setAttribute("tipoRichiesta", "Preventivo");
            }
            else if ("leasing".equals(tipo)) {
                LeasingDAO leasingDAO = new LeasingDAO(conn);
                Leasing l = leasingDAO.getById(id);

                request.setAttribute("richiesta", l);
                request.setAttribute("tipoRichiesta", "Leasing");
            }

            request.getRequestDispatcher("dettaglioRichiesta.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Errore nel recupero del dettaglio.");
        }
    }
}