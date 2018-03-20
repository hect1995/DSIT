/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import web.ViewManager;
import cart.ShoppingCart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hector
 */
public class viewcartAction extends Action{
    
    ShoppingCart shoppingCart;
    
    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        shoppingCart = (ShoppingCart) req.getSession().getAttribute("shoppingCart");
        System.out.println(shoppingCart.getNumberOfItems()+"WWWWWWWWWWWWWWW");
        req.setAttribute("shoppingCart", shoppingCart);
        String string_category_id= req.getParameter("categoryid");
        int category_id = Integer.parseInt(string_category_id);
        req.setAttribute("categoryid", category_id);
        
        ViewManager.nextView(req, resp, "/view/cart.jsp");
    }
}
