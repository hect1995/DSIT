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
            table, td {
                border: 1px solid black;
            }
        </style>
    </head>
    <body>
        <h1>Products available for category: <%=request.getAttribute("categoryName")%> </h1>
        <p>
        <%
        int nItems;
        if(request.getSession().getAttribute("numberItems")==null){
            nItems=0;
        }else{
            nItems= (Integer) request.getSession().getAttribute("numberItems");
        }
        %>
        <%=nItems%> Items</p>
        <%
        if (nItems>0){
            
        %>
        <a href="viewcart.do?cart=<%=request.getSession().getAttribute("shoppingCart")%>&categoryid=<%=request.getAttribute("categoryid")%>">
        View Cart  
        </a>
        <%}
        %>
        <table id="tableProductsCategories">
            <% // means java
            List<Product> products = (List<Product>)request.getAttribute("list_elements_category");

            for(Product product : products){
                System.out.println(product.getName()+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
            %>
            <tr width="14%" valign="center" align="middle">
                <td>
                    <img src="img/products/<%=product.getName()%>.png"
                        alt="<%=product.getName()%>" >
                </td>
                <td>
                    <%=product.getName()%>
                </td>
                <td>
                    <%=product.getPrice()%>
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

            <% } %>
            <% // means java
            List<Category> categories = (List<Category>)request.getAttribute("categories");
            %>
            <td width="14%" valign="center" align="middle">
                    <%
                    for(Category category : categories){
                    %>
                        <a href="category.do?categoryid=<%=category.getId()%>&numberItems=<%=nItems%>">
                            <%=category.getName()%>    
                        </a>
                    <% } %> 
            </td>
        </table>
    </body>
</html>
