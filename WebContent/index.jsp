<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Workshop Deel 2 - Bestel App</title>
    </head>
    <body bgcolor="silver">
        <form method="post" action="login.jsp">
            <center>
            <table border="0" width="30%" cellpadding="3">
                <thead>
                    <tr>
                        <th colspan="3">Loginscherm</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Gebruikersnaam</td>
                        <td><input type="text" name="userName" value="" /></td>
                    </tr>
                    <tr>
                        <td>Wachtwoord</td>
                        <td><input type="password" name="password" value="" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Verzenden" /></td>
                        <td><input type="reset" value="Reset" /></td>
                    </tr>
                    <tr>
                        <td colspan="2"><a href="register.jsp">Hier Registreren</a></td>
                    </tr>
                </tbody>
            </table>
            </center>
        </form>
    </body>
</html>