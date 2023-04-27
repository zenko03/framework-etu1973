<%-- 
    Document   : Valider
    Created on : 26 avr. 2023, 13:58:46
    Author     : itu
--%>

<%@page import="controller.TestController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>

<%
    ArrayList<TestController> listes=(ArrayList<TestController>)request.getAttribute("Liste_personne");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hello</title>
</head>
<body>
    <%for(int i=0;i<listes.size();i++) { %>
        <h1>Bienvenue <%out.print(listes.get(i).getprenom()+" "+listes.get(i).getnom()+". Vous avez "+listes.get(i).getage()+"an(s) ");%> </h1>
    <% } %>
</body>
</html>
