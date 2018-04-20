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
public class categoryAction extends Action{
        CategoryModel categoryModel;
        ProductModel productModel;

    public categoryAction(CategoryModel categoryModel, ProductModel productModel){
        this.categoryModel = categoryModel;
        this.productModel = productModel;
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        String string_category_id= req.getParameter("categoryid");
        req.setAttribute("categoryid", string_category_id);
        req.getSession().setAttribute("categories", categoryModel.retrieveAll());
        int category_id = Integer.parseInt(string_category_id);
        //System.out.println(category_id);
        Category cat= categoryModel.retrievebyId(category_id);
        req.getSession().setAttribute("categoryName", cat.getName());
        //System.out.println(cat.getName()+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        List <Product> elements_of_category= productModel.selectbyCat(cat);
        req.getSession().setAttribute("list_elements_category", elements_of_category);

        //req.setAttribute("category_id", productModel.selectbyId(category_id));
        // saving an object with name categories with Request Scope, requesting
        // all the categories that have been defined 
        ViewManager.nextView(req, resp, "/view/category.jsp");
        // forward the request to a .jsp. In all perform methods this is the last line

    }   
}
