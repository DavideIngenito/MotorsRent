
package controller;

import dao.UtenteDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Utente;

import java.io.IOException;

    @WebServlet("/login")
    public class LoginServlet extends HttpServlet {

        private UtenteDAO utenteDAO = new UtenteDAO();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            Utente utente = utenteDAO.login(email, password);

            if (utente != null) {
                HttpSession session = request.getSession();
                session.setAttribute("utente", utente);

                switch (utente.getRuolo()) {
                    case "CLIENTE":
                        response.sendRedirect("dashboardCliente.jsp");
                        break;
                    case "VENDITORE":
                        response.sendRedirect("dashboardVenditore.jsp");
                        break;
                    case "ADMIN":
                        response.sendRedirect("dashboardAdmin.jsp");
                        break;
                }
            } else {
                request.setAttribute("errore", "Credenziali non valide");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }
    }
