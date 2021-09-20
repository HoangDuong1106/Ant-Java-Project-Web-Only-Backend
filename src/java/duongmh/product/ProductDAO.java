/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongmh.product;

import duongmh.utils.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author admin
 */
public class ProductDAO implements Serializable {
    
    private List<ProductDTO> listProduct;

    public List<ProductDTO> getListProduct() {
        return listProduct;
    }

    public void getPagingProduct() 
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT sku, name, price, des, quantity "
                        + "FROM product";
                        
                stm = con.prepareStatement(sql);
                
                rs = stm.executeQuery();
                
                while(rs.next()) {
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    String des = rs.getString("des");
                    int quantity = rs.getInt("quantity");
                    
                    if (this.listProduct == null) {
                        this.listProduct = new ArrayList<ProductDTO>();
                    }
                    
                    ProductDTO product = new ProductDTO(sku, name, price, des, quantity);
                    this.listProduct.add(product);
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
                stm.close();
            }
        }
    }
    
    public void getManyProductByManySku(List<String> listSku) 
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String name = null;
                double price = 0;
                String des = null;
                int quantity = 0;
                String sql = "SELECT sku, name, price, des, quantity "
                        + "FROM product "
                        + "WHERE sku = ?";
                stm = con.prepareStatement(sql);
                for (String sku : listSku) {
                    stm.setString(1, sku);
                    rs = stm.executeQuery();
                    
                    if (rs.next()) {
                        name = rs.getString("name");
                        price = rs.getDouble("price");
                        des = rs.getString("des");
                        quantity = rs.getInt("quantity");
                        
                        if (this.listProduct == null) {
                            this.listProduct = new ArrayList<ProductDTO>();
                        }
                        
                        ProductDTO product = new ProductDTO(sku, name, price, des, quantity);
                        this.listProduct.add(product);
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
        
    }

    
}
