package khangnv.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khangnv.cart.CartObject;
import khangnv.order.OrderDAO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private final String ERROR_PAGE = "err";
    private final String CHECK_OUT_SUCCESSFUL_PAGE = "checkOutPage";
    private final String SEARCH_PAGE = "search";

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
        String url = ERROR_PAGE;
        try {
            // lay session
            HttpSession session = request.getSession(false);

            if (session != null) {
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    OrderDAO dao = new OrderDAO();
                    // save item to orderDetail table in database
                    boolean saveSuccess = dao.saveOrder(cart.getItems());

                    if (saveSuccess) {
                        session.removeAttribute("CART");
                        url = CHECK_OUT_SUCCESSFUL_PAGE;                                          
                    }
                }
            }

        } catch (SQLException ex) {
            log("SQLException " + ex.getMessage(), ex);
        } catch (NamingException ex) {
            log("NamingException " + ex.getMessage(), ex);
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

    
    
    
    
    
    
    
//    private boolean checkLogin(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            return false;
//        }
//        if (session.getAttribute("username") == null) {
//            return false;
//        }
//        return true;
//    }                       
//     boolean hasLogedIn = checkLogin(request);   
//        if (hasLogedIn) {
//            url = SEARCH_PAGE;
//        }
//        else {
//           url = CHECK_OUT_SUCCESSFUL_PAGE;
//        }

}
