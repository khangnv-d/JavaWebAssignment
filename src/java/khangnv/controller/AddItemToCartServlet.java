
package khangnv.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khangnv.cart.CartObject;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddItemToCartServlet", urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {

    private final String ERROR_PAGE="err";
    private final String SHOPPING_PAGE="shoppingPage";
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
        
        try  {
            //1. Customer goes to cart place(sessionScope)
            HttpSession session = request.getSession(true);
            //2. Customer take a cart
            CartObject cart = (CartObject) session.getAttribute("CART");
            if(cart == null){// kiem tra xem session da ton tai hay chua, neu chua thi NEW
                cart = new CartObject();
            }
            // 3. Cutomer takes item
            String id = request.getParameter("txtProducID");
            String name = request.getParameter("txtProductname");
            double price = Double.parseDouble(request.getParameter("txtProductprice"));
         
            //4. Customer drops item into cart
            cart.addItemToCart(id, name , price);
            session.setAttribute("CART", cart); // luu cart vao session scope
            //5. redirect to online shopping 
            url=SHOPPING_PAGE;
            
        } finally{
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
