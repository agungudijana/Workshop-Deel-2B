<%@ page import="java.sql.*"%>
<%@ page import="service.*"%>
<%@ page import="org.mindrot.jbcrypt.BCrypt"%>
<%
    String userName = request.getParameter("userName");    
    String password = request.getParameter("password"); 
    String password2 = request.getParameter("password2");
   	
    if (password.equals(password2) ) {
    	
		    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());   	
			Connection connection = DbUtil.getConnection();
		    Statement st = connection.createStatement();
		    int i = st.executeUpdate("insert into USERACCOUNTS(username, password, regdate) values ('" + userName + "','" + hashedPassword + "', CURDATE())");
			    if (i > 0) {
			        response.sendRedirect("welcome.jsp");
			       
			    } else {
			        response.sendRedirect("index.jsp");
			    }
	}
	else {
			%>
			Tweede wachtwoord invoert match niet met de eerste. <br/>
			<a href="register.jsp">Nogmaals proberen.</a>
			<%
	}
%>