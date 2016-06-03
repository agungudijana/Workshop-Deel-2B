<%@ page import="java.sql.*"%>
<%@ page import="service.*"%>
<%
    String userName = request.getParameter("userName");    
    String password = request.getParameter("password");
	Connection connection = DbUtil.getConnection();
    Statement st = connection.createStatement();
    int i = st.executeUpdate("insert into USERACCOUNTS(username, password, regdate) values ('" + userName + "','" + password + "', CURDATE())");
    if (i > 0) {
        response.sendRedirect("welcome.jsp");
       
    } else {
        response.sendRedirect("index.jsp");
    }
%>