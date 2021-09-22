/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangnv.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import khangnv.product.ProductDTO;

/**
 *
 * @author Admin
 */
public class CartObject implements Serializable {

    private Map<ProductDTO, Integer> items;

    public Map<ProductDTO, Integer> getItems() {
        return items;
    }

    public void addItemToCart(String id, String name, double price) { // thay cho phuong thuc Set
        if (id == null) {
            return;
        }
        if (id.trim().isEmpty()) {
            return;
        }
        //2. item'id  is existed, check item is existed in cart or not ?
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        ProductDTO product = new ProductDTO(id, name, price);

        // 3.when Items has existed in cart , checking the exist of it in cart
        int quality = 1;
        if (this.items.containsKey(product)) {
            quality = this.items.get(product) + 1;
        }
        // 4. update items
        this.items.put(product, quality);

    }

    public void removeItemFromCart(String id) {
        //1. Check items'id is existed (co null hay khoong, co phai la chuoi rong hay khong)
        if (this.items == null) {
            return;
        }
        // 2 . when items have existed, check item is existed in cart or not ?
                
        ProductDTO product = new ProductDTO(id);
        //boolean contains = this.items.containsKey(product);
        
        boolean check = false;
        
        for (ProductDTO dto : items.keySet()) {
            if (dto.hashCode() == product.hashCode()) {
                if (dto.equals(product)) {
                    check = true;
                }
            }
        }

        if (this.items.containsKey(product)) {
            
            this.items.remove(product);
            
            if (this.items.isEmpty()) {
                //kiem tra xem mon hang nay con so luong trong gio hay khong, neu khong cho = null
                this.items = null;
            }
        }

    }

}
