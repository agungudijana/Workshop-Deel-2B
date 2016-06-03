<%@ page import="java.sql.*"%>
<%@ page import="service.*"%>
<%@ page import="org.mindrot.jbcrypt.BCrypt"%>
<%
	String userName = request.getParameter("userName");    
    String password = request.getParameter("password");
	Connection connection = DbUtil.getConnection();
    Statement st = connection.createStatement();
    ResultSet rs;
    rs = st.executeQuery("select password from useraccounts where userName='" + userName + "'");
    if (rs.next()) {
    	
    	String hashedPassword = rs.getString(1);
    	
    	if (BCrypt.checkpw(password, hashedPassword)) {
    	        session.setAttribute("userid", userName);
    	        response.sendRedirect("success.jsp");
    	} 
    	else {	%>	
    		 	Ongeldig wachtwoord of gebruikersnaam
				<br>
				<br>
				<a href='index.jsp'>Probeer het nogmaals</a>
				<% 				  
		} 	
    }
%>