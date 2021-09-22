package khangnv.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khangnv.product.ProductDAO;
import khangnv.product.ProductDTO;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ViewBookStoreServlet", urlPatterns = {"/ViewBookStoreServlet"})
public class ViewBookStoreServlet extends HttpServlet {

    private final String SHOPPING_PAGE = "shoppingPage";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = "";
        try {

            HttpSession session = request.getSession(true);

            ProductDAO productDAO = new ProductDAO();

            List<ProductDTO> result = productDAO.getAll();
            session.setAttribute("STORE", result); // luu kq vao session scope

            if (result != null) {
                url = SHOPPING_PAGE;
            }
            //5. redirect to online shoppingh

        } catch (SQLException ex) {
            log("ViewBookStoreServlet_SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("ViewBookStoreServlet_NamingException: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
