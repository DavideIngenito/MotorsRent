package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Recupero la sessione, se esiste
        HttpSession session = request.getSession(false);

        if (session != null) {
            // 2. La distruggo (tutti i dati dell'utente vengono cancellati)
            session.invalidate();
        }

        // 3. Redirect alla pagina di login (o home) con un messaggio
        // Nota: uso il contextPath per essere sicuro che il link sia assoluto e corretto
        response.sendRedirect(request.getContextPath() + "/login.jsp?msg=LogoutEffettuato");
    }
}