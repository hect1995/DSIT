<%@page import="demo.spec.Message"%>
<%@ page import="demo.spec.UserAccess"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator"%>
<%@ page import="demo.impl.MessageWall_and_RemoteLogin_Impl"%>

<head>
    <meta http-equiv="Expires" CONTENT="0">
    <meta http-equiv="Cache-Control" CONTENT="no-cache">
    <meta http-equiv="Pragma" CONTENT="no-cache">
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <%--<meta http-equiv="refresh" content="5">--%> <%--refresh the site every 5 seconds--%>
    <title>Message Wall</title>
</head>

<%
    UserAccess user = (UserAccess)request.getSession().getAttribute("useraccess");
    MessageWall_and_RemoteLogin_Impl messagewall =
        (MessageWall_and_RemoteLogin_Impl) getServletContext().getAttribute("remoteLogin");
    List <Message> list_mes = messagewall.getAllMessages();
    Iterator<Message> iter_mes = list_mes.iterator();
%>

<script>
    
    
    
    
</script>

<body> 
    <h3>user: <em><%=user.getUser()%></em>
        <a href=logout.do>[Close session]</a></h3>

    <h2> <%=list_mes.size()%> Messages shown:</h2>

    <table width="50%" border="1" bordercolordark="#000000" bordercolorlight="#FFFFFF" cellpadding="3" cellspacing="0">

        <td width="14%" valign="center" align="middle">
            Message
        </td>

        <td width="14%" valign="center" align="middle">
            Owner
        </td>

        <td width="14%" valign="center" align="middle">
            Click to:
        </td>

        <%
        int counter= 0; 
        while(iter_mes.hasNext()){
            Message message= iter_mes.next();  
        %>

        <tr> <font size="2" face="Verdana">

        <td width="14%" valign="center" align="middle">
            <%=message.getContent()%>
        </td>

        <td width="14%" valign="center" align="middle">
            <%=message.getOwner()%>
        </td>

        <td width="14%" valign="center" align="middle">
            <form action="delete.do" method="post">
                <input type="hidden"
                       name="index"
                       value="<%=counter%>">
                <input type="hidden"
                       name="useraccess"
                       value="<%=user%>">
                <input type="submit"
                       name="delete"
                       value="delete">
            </form>
        </td>

        </font> 
    </tr>

    <% 
        counter ++;
    }
    %>

</table>

</br>

<HR WIDTH="100%" SIZE="2">

<form action="put.do" method=POST>
    New message:<input type=text name=msg size=10>
    <input type=submit value="Send message"></form>

<HR WIDTH="100%" SIZE="2">

<form action="refresh.do" method=POST>
    <input type=submit value="Refresh wall view message"></form>

</body>