/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongmh.order;

import duongmh.product.ProductDTO;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author admin
 */
public class OrderDTO implements Serializable {
    private HashMap<ProductDTO, Integer> items;
    private double total;
    private int userId;

    public OrderDTO() {
    }
    
    public OrderDTO(HashMap<ProductDTO, Integer> items, double total) {
        this.items = items;
        this.total = total;
    }

    public OrderDTO(HashMap<ProductDTO, Integer> items, double total, int userId) {
        this.items = items;
        this.total = total;
        this.userId = userId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public HashMap<ProductDTO, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<ProductDTO, Integer> items) {
        this.items = items;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    
}
