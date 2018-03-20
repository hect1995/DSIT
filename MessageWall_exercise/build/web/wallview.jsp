<%-- 
    Document   : wallview
    Created on : Mar 18, 2018, 8:31:13 AM
    Author     : Hector
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="demo.spec.Message"%>
<%@page import="demo.impl.MessageWall_and_RemoteLogin_Impl"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!!!!</h1>
        <%
        MessageWall_and_RemoteLogin_Impl messagewall =
            (MessageWall_and_RemoteLogin_Impl) getServletContext().getAttribute("remoteLogin");
        List <Message> list_mes = messagewall.getAllMessages();
        Iterator<Message> iter_mes = list_mes.iterator();
        int counter= 1;
        while(iter_mes.hasNext()){
            Message message= iter_mes.next();        
        %>
        <p>
            <a><%=message.getContent()%></a>
            <a>TIN</a>
        </p>
        <%
        }
        %>
    </body>
</html>
