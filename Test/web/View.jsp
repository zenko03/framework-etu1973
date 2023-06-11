<%@page import="controller.TestController"%>
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
    <title>Personne</title>
</head>
<body>
    <h1>Liste des Personnes</h1>
    <table class="table" id="tab">
            <thead>
                <tr>
                    <th scope="col">Nom</th>
                    <th scope="col">Prenom</th>
                    <th scope="col">Age</th>
                    <th scope="col">Mesure</th> 
                    <th scope="col"></th>   
                </tr>
            </thead>
            <tbody>
                <%for(int i=0;i<listes.size();i++) { %>
                <tr>
                    <td scope="row"><%out.print(listes.get(i).getnom());%> </td>
                    <td scope="row"><%out.print(listes.get(i).getprenom());%> </td>
                    <td scope="row"><%out.print(listes.get(i).getage());%></td>
                    <td scope="row"><%out.print(listes.get(i).getmesure());%></td>
                    <td scope="row"><a href="detail?id=<%out.print(listes.get(i).getid());%>">Details</td>
                </tr>
                <% } %>
            </tbody>
        </table>
</body>
</html>