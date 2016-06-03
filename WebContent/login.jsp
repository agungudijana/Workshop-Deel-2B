<%@ page import="java.sql.*"%>
<%@ page import="service.*"%>
<%
	String userName = request.getParameter("userName");    
    String password = request.getParameter("password");
	Connection connection = DbUtil.getConnection();
    Statement st = connection.createStatement();
    ResultSet rs;
    rs = st.executeQuery("select * from useraccounts where userName='" + userName + "' and password='" +password + "'");
    if (rs.next()) {
        session.setAttribute("userid", userName);
        response.sendRedirect("success.jsp");
    } else {
%>
     Ongeldig wachtwoord of gebruikersnaam <br><br>
     <a href='index.jsp'>Probeer het nogmaals</a>
<% 	   
    }
%>