package khangnv.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Map.Entry;
import javax.naming.NamingException;
import khangnv.product.ProductDTO;
import khangnv.utils.DBhelper;

/**
 *
 * @author Admin
 */

public class OrderDAO implements Serializable {
    
    // save items to OrderDetail table in database
    public boolean saveOrder(Map<ProductDTO, Integer> order) 
                                    throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            Integer savedOrderId = getOrderId();

            if (savedOrderId != null) {
                con = DBhelper.makeConnection();

                String sqlStatement = "insert into OrderDetail "
                        + "(OrderID, ProductID, Quantity, Total) VALUES "
                        + "(?, ?, ?, ?)";

                if (con != null) {
                    for (Entry<ProductDTO, Integer> pair : order.entrySet()) {
                        
                        ProductDTO product = pair.getKey();
                        int quantity = pair.getValue();
                        stm = con.prepareStatement(sqlStatement);

                        stm.setInt(1, savedOrderId);
                        stm.setString(2, product.getID());
                        stm.setInt(3, quantity);
                        stm.setInt(4, (int) product.getPrice() * quantity);

                        int affectedArrow = stm.executeUpdate();
                        if (affectedArrow == 0) {
                            return false;
                        }
                    }
                } else {
                    return false;
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
        return true;
    }

    //Lay ID auto incresing trong table Order to luu items
    private Integer getOrderId() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBhelper.makeConnection();

            if (con != null) {
                String sql = "insert into OrderHead (nonsense) "
                        + "values (0)";
                stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stm.executeUpdate();
                
                rs = stm.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    return id;
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
        return null;
    }
}
