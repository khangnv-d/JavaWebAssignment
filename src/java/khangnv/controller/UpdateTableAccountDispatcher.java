package khangnv.controller;

import common.sessionmodel.UpdateAccountError;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static common.Config.*;
import common.errorhandling.UpdateAccountErrorHandling;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateTableAccountDispatcher", urlPatterns = {"/UpdateTableAccountDispatcher"})
public class UpdateTableAccountDispatcher extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UpdateAccountErrorHandling reqHandling = new UpdateAccountErrorHandling();
        reqHandling.init(request);
        UpdateAccountError error = reqHandling.getError();

        if (error != null) {
            request.setAttribute("UERROR", error);
            String url = "SearchLastnameServlet?txtSearchValue=" + reqHandling.getSearchValue();
            request.setAttribute("UERROR_INDEX", reqHandling.getUsername());
            request.getRequestDispatcher(url).forward(request, response);
        } else {
            log("SearchValue: " + reqHandling.getSearchValue());
            String url = getUpdateAccountControllerUrl(reqHandling.getUsername(),
                    reqHandling.getNewPassword(),
                    reqHandling.getFullname(),
                    reqHandling.isRole(),
                    reqHandling.getSearchValue());
            response.sendRedirect(url);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

}
