package controller;

import dao.AutomobileDAO;
import dao.DbConnection;
import model.Automobile;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection conn = DbConnection.getInstance().getConnection();
            AutomobileDAO autoDAO = new AutomobileDAO(conn);

            // Recuperiamo tutte le auto
            List<Automobile> allCars = autoDAO.getAll();

            // FILTRO: Prendiamo solo le auto DISPONIBILI e limitiamo a 6
            List<Automobile> novita = allCars.stream()
                    .filter(Automobile::getDisponibilita) // Usa il metodo corretto getDisponibilita
                    .limit(6)
                    .collect(Collectors.toList());

            // Passiamo la lista alla JSP
            request.setAttribute("listaNovita", novita);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Inoltriamo alla index.jsp
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}