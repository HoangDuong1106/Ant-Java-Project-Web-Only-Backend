/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongmh.product;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author admin
 */
public class ProductDTO implements Serializable {
    private String sku;
    private String name;
    private double price;
    private String description;
    private int quantity;

    public ProductDTO() {
    }

    public ProductDTO(String sku) {
        this.sku = sku;
    }
    
    public ProductDTO(String sku, String name, double price, String description, int quantity) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.sku);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductDTO other = (ProductDTO) obj;
        if (!Objects.equals(this.sku, other.sku)) {
            return false;
        }
        return true;
    }
    
    
    
}
