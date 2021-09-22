package khangnv.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khangnv.registration.RegistrationDAO;

@WebServlet(name = "CookieLoginServlet", urlPatterns = {"/CookieLoginServlet"})
public class CookieLoginServlet extends HttpServlet {

    private final String LOGIN_PAGE = "loginPage";
    private final String SEARCH_PAGE = "searchPage";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = LOGIN_PAGE;

        try {

            HttpSession session = request.getSession(true);
            String username = "";
            String password = "";
            if (session.getAttribute("username") != null) {
                url = SEARCH_PAGE;

            } else {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {

                        // get username , password from name: value
                        if (cookie.getName().equals("username")) {
                            username = cookie.getValue();
                        } else if (cookie.getName().equals("password")) {
                            password = cookie.getValue();
                        }
                    }
                }

                if (username != "" && password != "") {
                    //call DAO to check authentication
                    RegistrationDAO dao = new RegistrationDAO();
                    boolean result = dao.checkLogin(username, password);

                    if (result) {
                        url = SEARCH_PAGE;
                        session.setAttribute("username", username);
                        //session.setAttribute("password", password);
                    }// end authentication is successfully checked   
                }

            }

        } catch (SQLException ex) {
            log("CookieLoginServlet__SQLException" + ex.getMessage());
        } catch (NamingException ex) {
            log("CookieLoginServlet__NamingException" + ex.getMessage());
        } finally {
            response.sendRedirect(url);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
