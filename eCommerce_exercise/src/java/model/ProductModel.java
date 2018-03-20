/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import entity.Category;
import entity.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author juanluis
 */
public class ProductModel {

    UserTransaction utx;
    EntityManager em;

    public ProductModel(EntityManager em, UserTransaction utx) {
        this.utx = utx;
        this.em = em;
    }
    
    public List<Product> selectbyCat(Category cat){
        Query q = em.createQuery("select o from Product as o WHERE o.category=:category");
        q.setParameter("category", cat);
        return q.getResultList();
    }
    public Product selectbyId(Integer id){
        Query q = em.createQuery("select o from Product as o WHERE o.id=:id");
        q.setParameter("id", id);
        return (Product) q.getSingleResult();
    }
    
    public List<Product> getallProd(){
        Query q = em.createQuery("select * from Product");
        return q.getResultList();
    }
}