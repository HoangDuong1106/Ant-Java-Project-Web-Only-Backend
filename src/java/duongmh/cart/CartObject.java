/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongmh.cart;

import duongmh.product.ProductDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author admin
 */
public class CartObject implements Serializable {
    private Map<ProductDTO, Integer> items;

    public Map<ProductDTO, Integer> getItems() {
        return items;
    }
    
    public boolean addItemToCart(ProductDTO item) {
        
        if (item == null) {
            return false;
        }
        
        if (this.items == null) {
            this.items = new HashMap<ProductDTO, Integer>();
        }
        
        if (item.getQuantity() <= 0) {
            return false;
        }
        
        int quantity = 1;
        if (this.items.containsKey(item)) {
            if (this.items.get(item) < item.getQuantity()) {
                quantity = this.items.get(item) + 1;
            } else {
                return false;
            }
        } 
        
        this.items.put(item, quantity);
        
        return true;
    }
    
    public boolean deleteItemInCart(String sku) {
        
        if (items == null) {
            return false;
        }
        
        if (this.items.containsKey(new ProductDTO(sku))) {
            this.items.remove(new ProductDTO(sku));
            if (this.items.isEmpty()) {
                this.items = null;
            }
            return true;
        } else {
            return false;
        }
    }
    
    public boolean minusQuantityItem(String sku) {
        if (items == null) {
            return false;
        }
        ProductDTO product = new ProductDTO(sku);
        if (this.items.containsKey(product)) {
            int quantity = this.items.get(product);
            if (quantity <= 1) {
                this.items.remove(product); 
            } else {
                quantity -= 1;
                this.items.put(product, quantity);
            }
            return true;
        }
        
        return false;
    }
    
    public boolean plusQuantityItem(String sku) {
        if (items == null) {
            return false;
        }
        
        ProductDTO product = new ProductDTO(sku);
        int quantityInStore = this.items.keySet().stream().filter(p -> p.equals(product)).collect(Collectors.toList()).get(0).getQuantity();
        if (this.items.containsKey(product)) {
            int quantity = this.items.get(product);
            if (quantity < quantityInStore) {
                quantity += 1;
                this.items.put(product, quantity);
            }
            return true;
        }
        
        return false;
        
    }
    
}
