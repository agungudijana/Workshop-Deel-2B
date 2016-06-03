<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registratie</title>
    </head>
    <body bgcolor="silver">
        <form method="post" action="userRegistration.jsp">
            <center>
            <table border="1" width="30%" cellpadding="5">
                <thead>
                    <tr>
                        <th colspan="3">Vul onderstaand formulier in</th>
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
                        <td>Voer wachtwoord nogmaals in</td>
                        <td><input type="password" name="password2" value="" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Verzenden" /></td>
                        <td><input type="reset" value="Reset" /></td>
                    </tr>
                    <tr>
                        <td colspan="3">Oeps, ik herinner me dat ik toch al geregistreerd ben!! <br><a href="index.jsp">Ga naar het inlogscherm</a></td>
                    </tr>
                </tbody>
            </table>
            </center>
        </form>
    </body>
</html>