package controller;

import dao.DbConnection;
import dao.LeasingDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/gestisciLeasing")
public class GestisciLeasingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null) { response.sendRedirect("venditoreLeasing"); return; }

        try {
            int id = Integer.parseInt(idStr);
            Connection conn = DbConnection.getInstance().getConnection();
            LeasingDAO dao = new LeasingDAO(conn);

            request.setAttribute("leasing", dao.getById(id));
            request.getRequestDispatcher("venditoreDettaglioLeasing.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Errore caricamento leasing");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("idLeasing"));
            double rata = Double.parseDouble(request.getParameter("rata"));
            String stato = request.getParameter("stato");
            String messaggio = request.getParameter("messaggio");

            Connection conn = DbConnection.getInstance().getConnection();
            LeasingDAO dao = new LeasingDAO(conn);

            dao.gestisciRisposta(id, stato, rata, messaggio);

            response.sendRedirect("venditoreLeasing");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Errore salvataggio risposta");
        }
    }
}