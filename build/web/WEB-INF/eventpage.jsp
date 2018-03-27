<!--Amanda Bradley
ITSCM 325, Web Development
Spring 2016
Event Page-->

<%@page import="java.util.Date"%>

<!DOCTYPE html>

<html lang="en">
<head>
	<meta charset="utf-8" />
	<title> Perfect Gift: Gift Registry </title>
	<link rel="stylesheet" href="stylesheet.css">
        <%  
                Date date = new Date();
                String eventDate = new String();
                String eventName = new String();
                String eventLocation = new String();
                String eventDescription = new String();
                String errorMessage = new String();
                String itemLocation = new String();
                if(request.getAttribute("errorMessage") != null)
                {
                    errorMessage = request.getAttribute("errorMessage").toString();
                }
                else
                {
                    errorMessage = "";
                }
                if(request.getAttribute("itemLoc") != null)
                {
                    itemLocation = request.getAttribute("itemLoc").toString();
                }
                else
                {
                    itemLocation = ""; 
                }
                
                if(request.getAttribute("oldEventName") != null)
                {
                    eventDate = request.getAttribute("oldEventDate").toString();
                    eventName = request.getAttribute("oldEventName").toString();
                    eventLocation = request.getAttribute("oldEventLocation").toString();
                    eventDescription = request.getAttribute("oldEventDescription").toString();
                }
                else
                {
                    eventDate = "";
                    eventName = "";
                    eventLocation = "";
                    eventDescription = "";
                    itemLocation = "";
                }
                
          
        %>
</head>

<body>
    
    <header>
            <img src="images/banner.jpg" alt="Banner" width="800" height="160">
    </header>
    
    <section>
            <h3 id="errorMessage"> <%=errorMessage.toString()%> </h3>
            <form id="enterEvent" action="EventMgrCtrl" method="POST">
                <input type="hidden" name="itemLoc" value="<%=itemLocation%>">
                Event Date: <br>
                    <input type="text" name="eventDate" value="<%=eventDate%>"><br><br>
                Event Name: <br>
                <input type="text" name="eventName" value="<%=eventName%>"><br><br>
                Event Location: <br>
                    <input type="text" name="eventLocation" value="<%=eventLocation%>"><br><br>
                Event Description: <br>
                <textarea name="eventDescription" rows="4" cols="30"><%=eventDescription%></textarea><br><br>
                            <input type="submit" name="submitEvent" value="Submit"/>
                            <input type="submit" name="returnEvent" value="Return"/>
                            
            </form>
            <h3 id="date"> <%=date.toString()%> </h3>
    </section>

  <footer>
	<p> &copy; Amanda Bradley, Spring 2016 UW-Whitewater, ITSCM325 Web Development Project</p>
  </footer>
    
</body>

</html>