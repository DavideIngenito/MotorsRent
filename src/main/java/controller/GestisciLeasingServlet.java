package controller;

import dao.DbConnection;
import dao.LeasingDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import model.Leasing;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/gestisciLeasing")
public class GestisciLeasingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        if(idStr == null) {
            response.sendRedirect("dashboardVenditore");
            return;
        }

        try {
            Connection conn = DbConnection.getInstance().getConnection();
            LeasingDAO leasingDAO = new LeasingDAO(conn);

            // Metodo corretto del DAO
            Leasing leasing = leasingDAO.getByIdCompleto(Integer.parseInt(idStr));

            request.setAttribute("leasing", leasing);
            // Assicurati che il path sia giusto
            request.getRequestDispatcher("venditoreDettaglioLeasing.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idLeasing = Integer.parseInt(request.getParameter("idLeasing")); // name nel form hidden
        String stato = request.getParameter("stato");

        try {
            Connection conn = DbConnection.getInstance().getConnection();
            LeasingDAO leasingDAO = new LeasingDAO(conn);

            leasingDAO.updateStato(idLeasing, stato);

            response.sendRedirect("venditoreLeasing?msg=Leasing aggiornato"); // Torna alla lista leasing (o dashboard)

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}