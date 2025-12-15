package controller;

import dao.AutomobileDAO;
import dao.DbConnection;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Automobile;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/catalogo")
public class CatalogoClienteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String marca = request.getParameter("marca");
        String prezzoMaxStr = request.getParameter("prezzoMax");

        try {
            Connection conn = DbConnection.getInstance().getConnection();
            AutomobileDAO autoDAO = new AutomobileDAO(conn);
            List<Automobile> lista;

            // Logica di Filtro
            if ((marca != null && !marca.isEmpty()) || (prezzoMaxStr != null && !prezzoMaxStr.isEmpty())) {
                double prezzoMax = (prezzoMaxStr != null && !prezzoMaxStr.isEmpty())
                        ? Double.parseDouble(prezzoMaxStr) : 1000000;
                // Metodo di ricerca (lo aggiungiamo al DAO tra poco)
                lista = autoDAO.cercaAuto(marca, prezzoMax);
            } else {
                // Lista completa
                lista = autoDAO.getAll();
            }

            request.setAttribute("listaAuto", lista);
            request.getRequestDispatcher("catalogoCliente.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Errore caricamento catalogo");
        }
    }
}