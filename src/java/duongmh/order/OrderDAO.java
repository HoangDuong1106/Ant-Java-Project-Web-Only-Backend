/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongmh.order;

import duongmh.product.ProductDTO;
import duongmh.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author admin
 */
public class OrderDAO implements Serializable {

    public boolean insertOrderDetail(OrderDTO dto, int lastOrderId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String insertOrderDetail = "INSERT INTO Order_Detail (orderId, productId, price, quantity, total) "
                        + "VALUES (?, ?, ?, ?, ?)";
                stm = con.prepareStatement(insertOrderDetail);
                int rows = 0;
                for (ProductDTO key : dto.getItems().keySet()) {
                    stm.setInt(1, lastOrderId);
                    stm.setString(2, key.getSku());
                    stm.setDouble(3, key.getPrice());
                    stm.setInt(4, dto.getItems().get(key));
                    stm.setDouble(5, key.getPrice() * dto.getItems().get(key));

                    rows += stm.executeUpdate();
                }

                if (rows == dto.getItems().size()) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }

        }

        return false;
    }

    public int updateProduct(OrderDTO dto) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String updateProductQuantitySql = "UPDATE product "
                        + "SET quantity = ? "
                        + "WHERE sku = ?";
                stm = con.prepareStatement(updateProductQuantitySql);
                int rows = 0;
                for (ProductDTO key : dto.getItems().keySet()) {
                    stm.setInt(1, key.getQuantity() - dto.getItems().get(key));
                    stm.setString(2, key.getSku());
                    rows += stm.executeUpdate();
                }

                return rows;
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }

        }

        return 0;
    }

    public int getMaxOrderId() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String getNextOrderId = "SELECT MAX(orderId) "
                        + "FROM [order] ";
                stm = con.prepareStatement(getNextOrderId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return -1;
    }

    public boolean checkout(OrderDTO dto)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String insertOrderSql = "INSERT INTO [order] (total, userId) "
                        + "VALUES (?, ?)";
                stm = con.prepareStatement(insertOrderSql);
                stm.setDouble(1, dto.getTotal());
                stm.setInt(2, dto.getUserId());
                int rows = stm.executeUpdate();
                if (rows > 0) {
                    int lastOrderId = getMaxOrderId();
                    rows = updateProduct(dto);
                    if (rows > 0) {
                        boolean result = insertOrderDetail(dto, lastOrderId);
                        return result;
                    }

                }

            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        return false;
    }
    
    public boolean checkoutNoUser(OrderDTO dto)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String insertOrderSql = "INSERT INTO [order] (total) "
                        + "VALUES (?)";
                stm = con.prepareStatement(insertOrderSql);
                stm.setDouble(1, dto.getTotal());
                int rows = stm.executeUpdate();
                if (rows > 0) {
                    int lastOrderId = getMaxOrderId();
                    rows = updateProduct(dto);
                    if (rows > 0) {
                        boolean result = insertOrderDetail(dto, lastOrderId);
                        return result;
                    }

                }

            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        return false;
    }
    
    public boolean insertOrderNoUser(OrderDTO dto)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String insertOrderSql = "INSERT INTO [order] (total) "
                        + "VALUES (?)";
                stm = con.prepareStatement(insertOrderSql);
                stm.setDouble(1, dto.getTotal());
                int rows = stm.executeUpdate();
                if (rows > 0) {
                    return true;
                }
            }

        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }

        return false;
    }
    
    
    
}
