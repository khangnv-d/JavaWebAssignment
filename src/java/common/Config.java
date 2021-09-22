package common;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class Config {
//    public static final String DELETE_CONTROLLER = "delete";

    public static final String UPDATE_ACCOUNT_CONTROLLER = "Update";
    public static final String SEARCH_PAGE = "search";
    public static final String CONFIRM_UPDATE_PAGE = "ConfirmUpdate";

    public static String getUpdateAccountControllerUrl(String username, String password,
                    String fullname, boolean isAdmin, String lastSearchValue) {
        String url = UPDATE_ACCOUNT_CONTROLLER
                + "?txtUsername=" + username
                + "&txtPassword=" + password
                + "&txtFullname=" + fullname
                + "&lastSearchValue=" + lastSearchValue;
        if (isAdmin) {
            url += "&checkAdmin=ON";
        }

        return url;
    }

    public static String getConfirmUpdateUrl(String username, String password, 
                     String fullname, boolean isAdmin, String lastSearchValue) {
        String url = CONFIRM_UPDATE_PAGE
                + "?txtUsername=" + username
                + "&txtPassword=" + password
                + "&txtFullname=" + fullname
                + "&lastSearchValue=" + lastSearchValue;
        if (isAdmin) {
            url += "&checkAdmin=ON";
        }

        return url;
    }

    public static String getSearchUrl(String searchValue) {
        String url = SEARCH_PAGE;
        if (searchValue != null) {
            url += "?btAction=Search&txtSearchValue=" + searchValue;
        }
        return url;
    }
}
