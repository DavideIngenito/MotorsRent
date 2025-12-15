package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// URL MAPPING: /catalogo
@WebServlet(name = "CatalogoServlet", value = "/catalogo")
public class CatalogoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CatalogoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Accesso a CatalogoServlet (Admin/Generale)");

        // Esempio: Carica una lista di prodotti dal database (simulato)
        request.setAttribute("messaggio", "Benvenuto nel Catalogo Generale");

        // Inoltra alla pagina JSP (assicurati che esista o cambia il percorso)
        request.getRequestDispatcher("/WEB-INF/views/catalogo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}