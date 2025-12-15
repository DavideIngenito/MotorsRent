package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// URL MAPPING: /catalogo-clienti (MODIFICATO PER RISOLVERE L'ERRORE)
@WebServlet(name = "CatalogoClienteController", value = "/catalogo-clienti")
public class CatalogoClienteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CatalogoClienteController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Accesso a CatalogoClienteController (Vista Utente)");

        // Logica specifica per il cliente (es. prodotti filtrati, carrello, ecc.)
        request.setAttribute("messaggio", "Benvenuto nell'area Catalogo Clienti");

        // Inoltra a una JSP diversa specifica per il cliente
        request.getRequestDispatcher("catalogoCliente.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}