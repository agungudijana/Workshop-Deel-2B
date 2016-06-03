<%
if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {

	    %>U bent niet ingelogd<br/>
		<a href="index.jsp">Logt u alstublieft in</a>
		<%
} 
else {
		response.sendRedirect("main.jsp");
}
%>