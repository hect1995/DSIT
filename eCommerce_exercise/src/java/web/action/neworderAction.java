/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

import cart.ShoppingCart;
import entity.Category;
import entity.Product;
import java.util.ArrayList;
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
public class neworderAction extends Action{
        CategoryModel categoryModel;
        ProductModel productModel;
        ShoppingCart shoppingCart;

    public neworderAction(CategoryModel categoryModel, ProductModel productModel){
        this.categoryModel = categoryModel;
        this.productModel = productModel;
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        this.shoppingCart = new ShoppingCart();
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute("shoppingCart");
        if (shoppingCart == null){
            shoppingCart = new ShoppingCart();
            req.getSession().setAttribute("shoppingCart", shoppingCart);
        }
        
        String string_product_id= req.getParameter("productId");
        String string_category_id= req.getParameter("categoryId"); 
        req.setAttribute("categoryid", req.getParameter("categoryId"));
        System.out.println(string_product_id+"FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        System.out.println(string_category_id+"CCCCCCCCCCCCCCCCCCC");
        int product_id = Integer.parseInt(string_product_id);
        int category_id = Integer.parseInt(string_category_id);
        Product newProduct= productModel.selectbyId(product_id);        
        System.out.println(newProduct.getDescription()+"TTTTTTTTTTTT");
        shoppingCart.addItem(newProduct);
        System.out.println(shoppingCart.getNumberOfItems()+"IIIIIIIIIII");
        int num_it = shoppingCart.getNumberOfItems();
        req.getSession().setAttribute("numberItems", num_it);
        Category catProduct= categoryModel.retrievebyId(category_id);
        String nameCategory = catProduct.getName();
        req.setAttribute("categoryName", nameCategory); 
        req.setAttribute("categories", categoryModel.retrieveAll());
        List <Product> elements_of_category= productModel.selectbyCat(catProduct);
        req.setAttribute("list_elements_category", elements_of_category);
        

        
        //Category cat= categoryModel.retrievbyId(category_id);
        //req.setAttribute("categoryName", cat.getName());
        //System.out.println(cat.getName()+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        //List <Product> elements_of_category= productModel.selectbyCat(cat);
        //req.setAttribute("list_elements_category", elements_of_category);

        // req.setAttribute("category_id", productModel.selectbyId(category_id));
        // saving an object with name categories with Request Scope, requesting
        // all the categories that have been defined 
        ViewManager.nextView(req, resp, "/view/category.jsp");
        // forward the request to a .jsp. In all perform methods this is the last line

    }
      
}
