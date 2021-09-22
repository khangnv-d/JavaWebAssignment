/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnv.product;

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
public class ProductDAO implements Serializable{
    public List<ProductDTO> getAll()
                throws SQLException, NamingException{
        
        ArrayList<ProductDTO> result;
        Connection con= null;
        PreparedStatement stm= null;
        ResultSet rs= null;
        result = new ArrayList();
        
        try{
            //1. connect database
            con= DBhelper.makeConnection();
            
            //2. create sql statement String
            if( con != null){
                String sql = "select ID, Name, Description, Price "
                                + "from Product";
                
                //3. create statement to set sql
                stm = con.prepareStatement(sql);
                
                //4. execute query
                rs=  stm.executeQuery();
                
                //5. processs
                while(rs.next()){
                    // get field/ column
                    String id = rs.getString("ID");
                    String name = rs.getString("Name");
                    String description = rs.getString("Description");
                    double price = rs.getDouble("Price");
                    
                    //6. create DTO instance
                    ProductDTO dto = new ProductDTO(id, name, price);
                    
                    //7. add to arraylist
                    result.add(dto);
                }// end rs has more than one record                
            }// end if connection  is existed.           
        } finally{
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
        return result;
    }
    
}
