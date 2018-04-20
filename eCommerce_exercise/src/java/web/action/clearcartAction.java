/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.action;

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
public class clearcartAction extends Action{
    CategoryModel categoryModel;
    ProductModel productModel;    
    
    public clearcartAction(CategoryModel categoryModel, ProductModel productModel){
        this.categoryModel = categoryModel;
        this.productModel = productModel;        
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute("shoppingCart");
        String cat_id_aux =  req.getParameter("categoryid");
        int cat_id = Integer.parseInt(cat_id_aux);
        req.setAttribute("categoryid", cat_id);
        //req.setAttribute("categories", categoryModel.retrieveAll());
        //Category cat= categoryModel.retrievebyId(cat_id);
        //req.setAttribute("categoryName", cat.getName());
        //List <Product> elements_of_category= productModel.selectbyCat(cat);
        //req.setAttribute("list_elements_category", elements_of_category);
        req.getSession().setAttribute("numberItems",0);


        ViewManager.nextView(req, resp, "/view/category.jsp");
    }

}
