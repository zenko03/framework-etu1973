<%-- 
    Document   : index
    Created on : 31 mars 2023, 14:25:52
    Author     : itu
--%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Vector<String> f =(Vector<String>) request.getAttribute("test"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% for(int i=0; i<f.size();i++ ) {
        out.print(f.get(i));
        %>
        
        <% } %>
        <h1>Hello World!</h1>
    </body>
</html>
