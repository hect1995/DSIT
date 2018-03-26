package cart;

import entity.Product;
import java.text.NumberFormat;
import java.util.*;
import cart.ShoppingCartItem;

/**
 *
 * @author juanluis
 */
public class ShoppingCart {
    private List<ShoppingCartItem> shocartItems;
    public ShoppingCart(){
        this.shocartItems= new ArrayList<ShoppingCartItem>();
    }
    public synchronized void addItem(Product product){
        /* 
        In case it was not already added the Item, do it. In the other case,
        just change the quantity
        */
        if(shocartItems.isEmpty()){
            // In case nothing in cart just add it
            ShoppingCartItem item1 = new ShoppingCartItem(product);
            item1.setQuantity(1);
            shocartItems.add(item1);
        }else{
            int check = 0;
            Iterator<ShoppingCartItem> iter = shocartItems.iterator();
            while(iter.hasNext() && check==0){
                ShoppingCartItem shop_it= iter.next();
                if(shop_it.getProduct().getId() == product.getId()){
                    check = 1;
                    int quantity = shop_it.getQuantity()+1;
                    this.update(product, Integer.toString(quantity));
                }
            }              
            if (check==0){
                ShoppingCartItem item = new ShoppingCartItem(product);
                item.setQuantity(1);
                shocartItems.add(item);    
            }
        }
    }
    public synchronized void update(Product product, String quantity){
        int quantity_item = Integer.parseInt(quantity);
        List<ShoppingCartItem> cart_items = this.getItems();
        Iterator<ShoppingCartItem> iter = cart_items.iterator();
        while(iter.hasNext()){
            ShoppingCartItem shop_it= iter.next();
            if(shop_it.getProduct().getId()==product.getId()){
                if(quantity_item >0){
                    shop_it.quantityProd = quantity_item;      
                }else if(quantity_item == 0){
                    //cart_items.remove(shop_it);
                    shocartItems.remove(shop_it);
                }
            }
        }
    }
    public synchronized List<ShoppingCartItem> getItems(){
        return shocartItems;
    }
    public synchronized int getNumberOfItems(){
        int total_number= 0;
        Iterator<ShoppingCartItem> iter = shocartItems.iterator();
        while(iter.hasNext()){
            ShoppingCartItem shop_it= iter.next();
            total_number = total_number + shop_it.getQuantity();
        }
        return total_number;
    }
    public synchronized double getTotal(){
        double totalPrice = 0;
        Iterator<ShoppingCartItem> iter = shocartItems.iterator();
        while(iter.hasNext()){
            ShoppingCartItem shop_it= iter.next();
            totalPrice = totalPrice + shop_it.getTotal();
        }
        return totalPrice;
    }
    public synchronized void clear(){}
}