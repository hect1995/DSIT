<%-- 
    Document   : cart
    Created on : Mar 15, 2018, 4:41:10 PM
    Author     : Hector
--%>
<%@ page import="entity.Product"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.*" %>
<%@ page import="cart.*" %>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ShoppingCart shop_cart = (ShoppingCart) request.getSession().getAttribute("shoppingCart");
            int num_it = shop_cart.getNumberOfItems();
        %>
        <h1>Your shopping cart contains <%=num_it%> items.</h1>
        <p>
            <a href="clearcart.do?categoryid=<%=request.getAttribute("categoryid")%>">
            Clear Cart  
            </a>
        </p>
        <p>
            <a href="category.do?categoryid=<%=request.getAttribute("categoryid")%>">
            Continue Shopping  
            </a>
        </p> 
        <p>
            <%--<a href="checkout.do?cart=<%=shop_cart%>">
            Proceed to Checkout  
            </a>--%>
            <a>
                <form action="https://www.paypal.com/cgi-bin/webscr" method="post">
                    <input type="hidden" name="cmd" value="_cart">
                    <input type="hidden" name="business" value="hect.esteban@gmail.com"
                    <input type="hidden" name="currency_code" value="EUR">           
                    <input type="image" src="http://www.paypal.com/en_US/i/btn/x-click-but01.gif" name="submit" alt="Make payments with PayPal - it's fast, free and secure!">
                    <input type="hidden" name="upload" value="1">
                    <%
                        List<ShoppingCartItem> list_items_aux= shop_cart.getItems();
                        Iterator<ShoppingCartItem> iter_aux = list_items_aux.iterator();
                        int counter= 1;
                        while(iter_aux.hasNext()){
                            ShoppingCartItem shop_it_aux= iter_aux.next();
                            Product product_aux = shop_it_aux.getProduct();
                            String name = product_aux.getName();
                            double price = shop_it_aux.getTotal();
                    %>
                    <input type="hidden" name="item_name_<%=counter%>" value="<%=name%>">
                    <input type="hidden" name="amount_<%=counter%>" value="<%=price%>">
                    <%
                            counter++;
                        }
                    %>
                </form>
            </a>
        </p>
        <table id="tableViewCart" style="width: 100%; align-self: center">
            <style>
                td, th {
                    border: 1px solid #dddddd;
                    text-align: center;
                    padding: 0px;
                }
            </style>
            <tr>
                <td>product</td>
                <td>name</td>
                <td>price</td>
                <td>quantity</td>
            </tr>
            <%
            List<ShoppingCartItem> list_items= shop_cart.getItems();    
            Iterator<ShoppingCartItem> iter = list_items.iterator();
            while(iter.hasNext()){
                ShoppingCartItem shop_it= iter.next(); 
                Product product = shop_it.getProduct();
            %>
            <tr>
                <td><img src="img/products/<%=product.getName()%>.png"></td>
                <td>
                    <b><p><%=product.getName()%></p></b>
                    <p><%=product.getDescription()%></p>
                </td>
                <td><%=product.getPrice()%>€ unit</td>
                <th>
                    <form name="updateForm" action="updatecart.do" method="post">
                        <input type="hidden" name="cart" value="<%=shop_cart%>">
                        <input type="hidden" name="categoryid" value="<%=request.getAttribute("categoryid")%>">
                        <input type="hidden" name="productId" value="<%=product.getId()%>">
                        <input type="number" style="text-align: right" name="quantity" value="<%=shop_it.getQuantity()%>" required>
                        <input type="submit" value="update">
                    </form>
                </th>
            </tr>
            <%
            }
            %>       
        </table>
        <%
            DecimalFormat df = new DecimalFormat("#.##");
        %>
        <p>Total amount: <%=df.format(shop_cart.getTotal())%> €</p>
            
    </body>
</html>
