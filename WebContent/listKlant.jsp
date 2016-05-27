<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Show All Users</title>
</head>
<body>
    <table border=1>
        <thead>
            <tr>
                <th>Klant ID</th>
                <th>Voornaam</th>
                <th>Achternaam</th>
                <th>Tussenvoegsel</th>
                <th>Email</th>
                <th colspan=2>Actie</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${klanten}" var="klant">
                <tr>
                    <td><c:out value="${klant.id}" /></td>
                    <td><c:out value="${klant.voornaam}" /></td>
                    <td><c:out value="${klant.achternaam}" /></td>
           			<td><c:out value="${klant.tussenvoegsel}" /></td>
                    <td><c:out value="${klant.email}" /></td>
                    <td><a href="KlantController?action=edit&klantId=<c:out value="${klant.id}"/>">Aanpassen</a></td>
                    <td><a href="KlantController?action=delete&klantId=<c:out value="${klant.id}"/>">Verwijderen</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="KlantController?action=insert">Voeg nieuwe klant toe</a></p>
</body>
</html>