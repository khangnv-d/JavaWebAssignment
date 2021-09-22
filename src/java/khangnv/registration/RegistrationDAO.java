/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnv.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import khangnv.utils.DBhelper;

/**
 *
 * @author Admin
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm= null;
        ResultSet rs= null;
        try {
            //1. connect database
            con = DBhelper.makeConnection();

            //2. create sql statement String
            if (con != null) {
                String sql = "Select username "
                        + "From Registration "
                        + "Where username = ? And password = ?";

                //3. Create statement to set sql
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. execute query
                rs = stm.executeQuery();
                //5. process
                if( rs.next()){
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;

    }
    
    public String getFullName(String username) 
                throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm= null;
        
        try {
            //1. connect database
            con = DBhelper.makeConnection();
            //2. create sql statement String
            if (con != null) {
                String sql = "select lastName From Registration "
                           + "Where username = ?";

                //3. Create statement to set sql
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. execute query
                ResultSet rs= stm.executeQuery();
                //5. process
                String fullname=null;
                while (rs.next()){
                fullname = rs.getString("lastName");
                
                }
                if(fullname != null){
                    return fullname;
                }   
                
            }
        } finally {
            
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
    
    
    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }
    
    public void searchLastname(String searchValue) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm= null;
        ResultSet rs= null;
        try {
            
            //1. connect database
            con = DBhelper.makeConnection();

            //2. create sql statement String
            if (con != null) {
                String sql = "select userName, passWord, lastName, isAdmin " 
                        +"From Registration "
                        +"where lastName like ?";

                //3. Create statement to set sql
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue +"%");
                
                //4. execute query
                rs = stm.executeQuery();
                //5. process
                while (rs.next()){
                    //get field/ column
                    String username = rs.getString("userName");
                    String password = rs.getString("passWord");
                    String lastname = rs.getString("lastName");
                    boolean role    = rs.getBoolean("isAdmin");
                    
                    // create DTO instance
                    RegistrationDTO dto = new RegistrationDTO(username, 
                            password, lastname, role);
                    
                    //add to accounts list
                    if(this.accounts == null){
                        this.accounts= new ArrayList<>();
                    }
                    // account is avaiable
                    this.accounts.add(dto);
                }// end rs has more than one record
            }// end if connection  is existed.
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
    }
    
    
    public boolean deleteAccount(String username)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm= null;
        
        try {
            //1. connect database
            con = DBhelper.makeConnection();

            //2. create sql statement String
            if (con != null) {
                String sql = "Delete From Registration "
                           + "Where username = ?";

                //3. Create statement to set sql
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //4. execute query
                int row = stm.executeUpdate();
                //5. process
                if (row >0){
                    return true;
                }
            }
        } finally {
            
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;       
    }
    
    public boolean UpdateAccount(String userName, String newPassword, boolean newRole, 
                                                        String fullname)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm= null;
        String role = "0";
        if (newRole) {
            role = "1";
        }
        try {
            //1. connect database
            con = DBhelper.makeConnection();

            //2. create sql statement String
            if (con != null) {
                String sql = "Update Registration "
                        + "Set passWord = ?, isAdmin = ?, lastName=? "
                        + "Where username = ?";

                //3. Create statement to set sql
                stm = con.prepareStatement(sql);
                stm.setString(1, newPassword);
                stm.setString(2, role);
                stm.setString(3, fullname);
                stm.setString(4, userName);
                //4. execute query
                int row = stm.executeUpdate();
                //System.out.println("SO rows anh huong " + row);
                //5. process
               if(row>0){
                   return true;
               }
                      
            }
            
        } finally {
            
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;   
    }
    
    public boolean createAccount(RegistrationDTO dto)
            throws SQLException, NamingException {
        // truyen Obj luon luon phai kiem tra Obj co khac null khong
        if(dto== null){
            return false;
        }// end dto is not existed
        Connection con = null;
        PreparedStatement stm= null;
        
        try {
            //1. connect database
            con = DBhelper.makeConnection();

            //2. create sql statement String
            if (con != null) {
                String sql = "insert Into Registration(username, password, lastname, isAdmin) "                       
                           + "Values(?, ?, ?, ?)";

                //3. Create statement to set sql
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getLastname());
                stm.setBoolean(4, dto.isRole());
                //4. execute query
                int row = stm.executeUpdate();
                //5. process
                if (row >0){
                    return true;
                }
            }
        } finally {
            
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;      
        
    }

}
