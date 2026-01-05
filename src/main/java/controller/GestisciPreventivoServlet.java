package controller;

import dao.DbConnection;
import dao.PreventivoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Preventivo;
import model.Utente;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/gestisciPreventivo")
public class GestisciPreventivoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isVenditore(request, response)) return;

        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.sendRedirect("dashboardVenditore");
            return;
        }

        try {
            Connection conn = DbConnection.getInstance().getConnection();
            PreventivoDAO dao = new PreventivoDAO(conn);
            Preventivo p = dao.getById(Integer.parseInt(idStr));

            if (p != null) {
                request.setAttribute("preventivo", p);
                request.getRequestDispatcher("venditoreDettaglioPreventivo.jsp").forward(request, response);
            } else {
                response.sendRedirect("dashboardVenditore");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Errore caricamento preventivo");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isVenditore(request, response)) return;

        try {
            String idStr = request.getParameter("idPreventivo");
            String prezzoStr = request.getParameter("prezzo");
            String stato = request.getParameter("stato");
            String messaggio = request.getParameter("messaggio");

            int id = Integer.parseInt(idStr);
            double prezzo = 0;
            if (prezzoStr != null && !prezzoStr.isEmpty()) {
                prezzo = Double.parseDouble(prezzoStr);
            }

            Connection conn = DbConnection.getInstance().getConnection();
            PreventivoDAO dao = new PreventivoDAO(conn);

            dao.gestisciRisposta(id, stato, prezzo, messaggio);

            response.sendRedirect("dashboardVenditore?msg=PreventivoGestito");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errore in GestisciPreventivoServlet: " + e.getMessage());
            response.sendError(500, "Errore salvataggio risposta: " + e.getMessage());
        }
    }


    private boolean isVenditore(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        Utente u = (session != null) ? (Utente) session.getAttribute("utente") : null;
        if (u == null || !"VENDITORE".equalsIgnoreCase(u.getRuolo())) {
            response.sendRedirect("login.jsp");
            return false;
        }
        return true;
    }
}