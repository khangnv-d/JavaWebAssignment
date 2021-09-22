package khangnv.controller;

import common.sessionmodel.UpdateAccountError;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khangnv.registration.RegistrationDAO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "errors.html";

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

        String searchValue = request.getParameter("lastSearchValue");
        String username = request.getParameter("txtUsername");
        String fullname = request.getParameter("txtFullname");
        String newPassword = request.getParameter("txtPassword");
        String admin = request.getParameter("checkAdmin");
        boolean role = false;
        if (admin != null && !admin.trim().isEmpty()) {
            role = true;
        }

        String url = ERROR_PAGE;
        try {
            //. call DAO
            RegistrationDAO dao = new RegistrationDAO();

            boolean result = dao.UpdateAccount(username, newPassword, role, fullname);

            if (result) {
                //. call previous function: --call search function
                url = "search?"
                        + "&txtSearchValue=" + searchValue;

                //System.out.println("Duowng truyen cua toi " +url);
            }// end if update is succesfully

        } catch (SQLException ex) {
            log("UpdateAccount Error due to: " + ex.getMessage());
        } catch (NamingException ex) {
            log("UpdateAccount Error due to: " + ex.getMessage());
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
