<%@page import="java.util.Date"%>
<!--Amanda Bradley
ITSCM 325, Web Development
Spring 2016
Gift Giver Page-->

<!DOCTYPE html>

<html lang="en">
<head>
	<meta charset="utf-8" />
	<title> Perfect Gift: Gift Registry </title>
	<link rel="stylesheet" href="stylesheet.css">
        <link rel="contents" href="sendingEmails.html">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
        <%  
                Date date = new Date();
                
                String event = new String();
                String registeredGifter = new String();
                String relationship = new String();
                String emailAddress = new String();
                String errorMessage = new String();
                String itemLocation = new String();
                String selectedFamily = new String();
                String selectedFriend = new String();
                String selectedOther = new String();
                
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
                if(request.getAttribute("oldEvent") != null)
                {
                    event = request.getAttribute("oldEvent").toString();
                    registeredGifter = request.getAttribute("oldRegisteredGifter").toString();
                    relationship = request.getAttribute("oldRelationship").toString();
                    emailAddress = request.getAttribute("oldEmailAddress").toString();
                }
                else
                {
                    event = "";
                    registeredGifter = "";
                    relationship = "";
                    emailAddress = "";
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
            <form id="newGifter" action="EventMgrCtrl" method="POST">
                <input type="hidden" name="itemLoc" value="<%=itemLocation%>">
                Event Name: <br>
                    <input type="text" name="event" value="<%=event%>"><br><br>
                Add Gifter (Name): <br>
                    <input type="text" name="registeredGifter" value="<%=registeredGifter%>"><br><br>
                Relationship to Giftee: <br>
                    <select name="relationship" >
                        <option>Family</option>
                        <option>Friend</option>
                        <option>Other</option>
                    </select><br><br>
            </form>
                <form name="inviteGifterForm" id="inviteGifterForm">
                    Email Address of Gifter: <br>
                    <input type="text" name="emailAddress" id="emailAddress" value="<%=emailAddress%>" maxlength="45" style="width:30%;" /><br>
                    <input type="submit" name="submitGifter" id="submitGifterButton" value="Submit" onclick="sendingEmails"/>
                        <input type="submit" name="returnGifter" id="returnGifterButton" value="Return"/>   
                </form>
            <h3 id="date"> <%=date.toString()%> </h3>
    </section>

  <footer>
	<p> &copy; Amanda Bradley, Spring 2016 UW-Whitewater, ITSCM325 Web Development Project</p>
  </footer>
    
</body>

</html>
