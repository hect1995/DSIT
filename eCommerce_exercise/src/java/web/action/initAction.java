package web.action;

import javax.servlet.http.*;
import model.CategoryModel;
import web.ViewManager;

public class initAction extends Action {

    CategoryModel categoryModel;

    public initAction(CategoryModel categoryModel){
        this.categoryModel = categoryModel;
    }

    public void perform(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("categories", categoryModel.retrieveAll());
        // saving an object with name categories with Request Scope, requesting
        // all the categories that have been defined 
        ViewManager.nextView(req, resp, "/view/init.jsp");
        // forward the request to a .jsp. In all perform methods this is the last line

    }
}
