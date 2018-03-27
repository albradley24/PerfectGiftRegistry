<%@page import="java.util.Date"%>

<!--Amanda Bradley
ITSCM 325, Web Development
Spring 2016
Index (Home) Page-->

<!DOCTYPE html>

<html lang="en">
<head>
	<meta charset="utf-8" />
	<title> Perfect Gift: Gift Registry </title>
        <link rel="stylesheet" href="stylesheet.css">
        <%
            Date date = new Date();
                    Cookie[] userCookies = request.getCookies();
                    String username = "";
                    if(userCookies != null)
                    {
                        for(int i=1; i<userCookies.length-1; i++)
                        {
                            if(userCookies[i].getName() == "USERNAME")
                            {
                                username = userCookies[i].getValue();
                            }
                        }
                    } 
        %>
</head>

<body>    
    <header id="indexheader">
        <img src="images/banner.jpg" alt="Banner" width="800" height="160"> 
        
    </header>
    
        <section>
            <h3 id="errorMessage"> ${invalidLogin} </h3>
            <form id="Login" action="LoginCtrl" method='POST'>
                Username:<br>
                    <input type="text" name="username" value="${cookie.USERNAME.value}" size="30"><br><br>
                Password:<br>
                    <input type="password" name="password" value="" size="30"><br><br>
                                <input type="submit" value="Submit">
            </form>
                <h3 id="date"> <%=date.toString()%> </h3>
        </section>

  <footer>
	<p> &copy; Amanda Bradley, Spring 2016 UW-Whitewater, ITSCM325 Web Development Project</p>
  </footer>
    
</body>

</html>
