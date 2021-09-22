/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.errorhandling;

import common.sessionmodel.UpdateAccountError;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Admin
 */
public class UpdateAccountErrorHandling {

    private String searchValue;
    private String username;
    private String fullname;
    private String newPassword;
    private boolean role;
    private boolean hasError = false;
    UpdateAccountError error;


    public void init(HttpServletRequest request) {
        
        ServletContext context = request.getServletContext();
        error = new UpdateAccountError();
        searchValue  = request.getParameter("lastSearchValue");
        username     = request.getParameter("txtUsername");
        newPassword  = request.getParameter("txtPassword");
        fullname     = request.getParameter("txtFullname");
        String admin = request.getParameter("checkAdmin");
        role = false;

        if (admin != null) {
            role = true;
        }

        if (newPassword == null || newPassword.trim().length() < 6 || newPassword.trim().length() > 20) {
            hasError = true;
            context.log("Loi password");
            error.setIncorrectPassword();
        }

        if (fullname == null || fullname.trim().length() < 2 || fullname.trim().length() > 20) {
            context.log("Loi fullname");
            hasError = true;
            error.setFullNameEmpty();
        }

    }

    public boolean isRole() {
        return role;
    }
    
    public boolean hasError() {
        return hasError;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public UpdateAccountError getError() {
        if (hasError) {
            return error;
        }
        return null;
    }
}
