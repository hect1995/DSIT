/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software
 * except in compliance with the terms of the license at:
 * http://developer.sun.com/berkeley_license.html
 */

package cart;

import entity.Product;

/**
 *
 * @author juanluis
 */
public class ShoppingCartItem {
    Product product;
    int quantityProd;
    public ShoppingCartItem(Product product){
        this.product= product;
    }
    public Product getProduct(){
        return product;
    }
    public int getQuantity(){
        return quantityProd;
    }
    public void setQuantity(int quantity){
        quantityProd= quantity;
    }
    public double getTotal(){
        double price=  (double) product.getPrice();
        return price*quantityProd;
    }
}