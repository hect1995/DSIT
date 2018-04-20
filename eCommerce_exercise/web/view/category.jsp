<%-- 
    Document   : category
    Created on : Mar 8, 2018, 11:48:37 AM
    Author     : Hector
--%>
<%@ page import="entity.Category" %>
<%@ page import="cart.ShoppingCart" %>
<%@ page import="entity.Product" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
        <style>
            td, th {
                border: 1px solid #dddddd;
                text-align: center;
                padding: 0px;
            }
        </style>
        
    </head>
    <body>
        <h1>Products available for category: <%=request.getSession().getAttribute("categoryName")%> </h1>
        <p>
        <%
            int nItems;
            if(request.getSession().getAttribute("numberItems")==null){
                nItems=0;
            }else{
                nItems= (Integer) request.getSession().getAttribute("numberItems");
            }
        %>
        <img src="img/cart.gif" alt="carret" ></img> <%=nItems%> Items
        <%
            if (nItems>0){      
        %>
            <a href="viewcart.do?cart=<%=request.getSession().getAttribute("shoppingCart")%>&categoryid=<%=request.getAttribute("categoryid")%>">
             View Cart  
            </a>
        </p>
        <%
            }
        %>
        <table style="width: 100%; text-align: center; border-collapse: collapse">
            <tr width="14%" valign="center" align="middle">
                <td>
                    <table id= "categories" style="width: 100%; text-align: center; border: 0px solid black" border="1">
                        <tr>
                            <th>
                                Categories
                            </th>
                        </tr>
                        <% // means java
                            List<Category> categories = (List<Category>)request.getSession().getAttribute("categories");
                            for(Category category : categories){
                        %>
                        <tr>    
                            <td>
                                <a href="category.do?categoryid=<%=category.getId()%>&numberItems=<%=nItems%>">
                                    <%=category.getName()%>    
                                </a>                         
                            </td>
                        </tr>
                        <%  } %>
                    </table>
                <td>
                    <table style="width: 100%; text-align: center" border="0">
                        <tr>
                        <% // means java
                            List<Product> products = (List<Product>)request.getSession().getAttribute("list_elements_category");

                            for(Product product : products){
                        %>
                            <td>
                                <img src="img/products/<%=product.getName()%>.png"
                                    alt="<%=product.getName()%>" >
                            </td>
                            <td>
                                <b><%=product.getName()%></b>
                                <p><%=product.getDescription()%></p>
                            </td>
                            <td>
                                <%=product.getPrice()%> €
                            </td>
                            <td>
                            <%
                                Category cat = product.getCategory();
                                int identif2= cat.getId();
                            %>
                                <a href="neworder.do?productId=<%=product.getId()%>&categoryId=<%=identif2%>">
                                    <button type="button" onclick="alert('Product was added to the cart')">add to cart</button>
                                </a> 
                            </td> 
                        </tr>
                        <%  }%>
                    </table>
                </td>
            </tr>
        </table>
    </body>
</html>
