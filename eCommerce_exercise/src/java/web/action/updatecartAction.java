/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import cart.ShoppingCart;
import entity.Category;
import entity.Product;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoryModel;
import model.ProductModel;
import web.ViewManager;

/**
 *
 * @author Hector
 */
public class updatecartAction extends Action{
    ProductModel productModel;
    ShoppingCart shoppingCart;
    public updatecartAction(ProductModel productModel){
        this.productModel = productModel;
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        shoppingCart = (ShoppingCart) req.getSession().getAttribute("shoppingCart");
        String product_id = req.getParameter("productId");
        String quantity = req.getParameter("quantity");

        Product productbyId = (Product) productModel.selectbyId(Integer.parseInt(product_id));
        shoppingCart.update(productbyId, quantity);
        req.getSession().setAttribute("shoppingCart", shoppingCart);
        String string_category_id= req.getParameter("categoryid");
        int category_id = Integer.parseInt(string_category_id);
        req.setAttribute("categoryid", category_id);
        req.getSession().setAttribute("numberItems", shoppingCart.getNumberOfItems());
        if (shoppingCart.getNumberOfItems()>0){ //still items in the cart
            ViewManager.nextView(req, resp, "/view/cart.jsp");
        }else{ //in the case the number of items is 0 I need to get back to the categories view
            //CategoryModel categoryModel = null;
            //Category cat= categoryModel.retrievebyId(category_id);
            //List <Product> elements_of_category= productModel.selectbyCat(cat);
            //req.setAttribute("list_elements_category", elements_of_category);
            //req.setAttribute("categoryName", cat.getName());
            //req.setAttribute("categories", categoryModel.retrieveAll());
            ViewManager.nextView(req, resp, "/view/category.jsp");
        }
    }
}
