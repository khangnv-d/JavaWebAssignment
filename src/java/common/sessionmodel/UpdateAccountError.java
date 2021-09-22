/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.sessionmodel;

/**
 *
 * @author Admin
 */
public class UpdateAccountError {
    private String fullNameEmpty;
    private String incorrectPassword;

    public String getFullNameEmpty() {
        return fullNameEmpty;
    }

    public void setFullNameEmpty() {
        this.fullNameEmpty = "FullName have to have length between 2-20 characters";
    }

    public String getIncorrectPassword() {
        return incorrectPassword;
    }

    public void setIncorrectPassword( ) {
        this.incorrectPassword = "Password have to have length between 6-20 characters";
    }
    
    
}
