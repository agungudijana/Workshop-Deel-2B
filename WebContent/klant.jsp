<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link type="text/css"
    href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
<title>Add new user</title>
</head>
<body>
    <form method="POST" action='KlantController' name="frmAddKlant">
    	<input type="hidden" readonly="readonly" name="klantId"
            value="<c:out value="${klant.id}" />" /> <br /> 
        Voornaam : <input
            type="text" name="voornaam"
            value="<c:out value="${klant.voornaam}" />" /> <br /> 
        Achternaam : <input
            type="text" name="achternaam"
            value="<c:out value="${klant.achternaam}" />" /> <br /> 
        Tussenvoegsel : <input
            type="text" name="tussenvoegsel"
            value="<c:out value="${klant.tussenvoegsel}" />" /> <br /> 
        Email : <input type="text" name="email"
            value="<c:out value="${klant.email}" />" /> <br /> <input
            type="submit" value="Verzenden" />
    </form>
</body>
</html>