<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="Event.EventDataBean"%>
<%@page import="Gifter.GifterDataBean"%>

<!--Amanda Bradley
ITSCM 325, Web Development
Spring 2016
List Manager Page-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title> Perfect Gift: Gift Registry </title>
    <link rel="stylesheet" href="stylesheet.css">
        <%  
                Date date = new Date();
                String errorMessage = new String();
                
                if(request.getAttribute("errorMessage") != null)
                {
                    errorMessage = request.getAttribute("errorMessage").toString();
                }
                else
                {
                    errorMessage = "";
                }
        %>
</head>

<header>
    <img src="images/banner.jpg" alt="Banner" width="800" height="160">
</header>

<section>
    <h2> Amanda's Upcoming Gifting Events: </h2>
    
    <h3 id="errorMessage"> <%=errorMessage.toString()%> </h3>
        
    <form name="EventTableForm" action="EventMgrCtrl">
        <table border="2" width="700" cellspacing="2" cellpadding="2" id="Table1">
    <thead>
        <tr>
            <th> Event Date </th>
            <th> Event Name </th>
            <th> Event Location </th>
            <th> Event Description </th>
            <th> Update </th>
        </tr>
    </thead>
    
    <tbody>
        <tr>
            <%
                ArrayList<EventDataBean> events = (ArrayList<EventDataBean>)session.getAttribute("events");
                if(events != null)
                {
                    int rowNumber = 0;
                                       
                    for (EventDataBean item : events)
                    {     
                        rowNumber = rowNumber + 1;
            %>
                        <tr>
                        <td> <%= item.getEventDate() %> </td>
                        <td> <%= item.getEventName() %> </td>
                        <td> <%= item.getEventLocation() %> </td>
                        <td> <%= item.getEventDescription() %> </td>
                        <td> <input type="radio" name="eventRowNumber" value="<%=item.getEventID()%>"/> </td>
                        </tr>
            <%
                    }
                } 
                else
                {
            %>
                    <tr>
                    <td> N/A </td>
                    <td> N/A </td>
                    <td> N/A </td>
                    <td> N/A </td>
                    <td> <input type="radio" name="eventRowNumber" value="0"/> </td>
                    </tr>
            <%
                }
            %>
    </tbody>
        </table>
            
<div>
    <input type="submit" value="New Event" name="newEvent"/>
    <input type="submit" value="Update" name="updateEvent"/>
    <input type="submit" value="View Gifts" name="viewGifts">
    <input type="submit" value="Delete" name="deleteEvent"/>
    <input type="submit" value="Exit" name="exitEvent"/> 
</div>
            
</form>
    
<br>
        <h2> Amanda's Registered Gifters: </h2>
            <form name="GifterTableForm" action="EventMgrCtrl">
            <table border="1" width="700" cellspacing="2" cellpadding="2" id="Table2">
    <thead>
        <tr>
            <th> Event </th>
            <th> Gifter Name </th>
            <th> Relationship </th>
            <th> Email Address </th>
            <th> Update </th>
        </tr>
    </thead>
    
    <tbody>
        <tr>
            <%
                ArrayList<GifterDataBean> gifters = (ArrayList<GifterDataBean>)session.getAttribute("gifters");
                if(gifters != null)
                {
                    int rowNumber = 0;
                                       
                    for (GifterDataBean item : gifters)
                    {     
                        rowNumber = rowNumber + 1;
            %>
                        <tr>
                        <td> <%= item.getEvent() %> </td>
                        <td> <%= item.getRegisteredGifter() %> </td>
                        <td> <%= item.getRelationship() %> </td>
                        <td> <%= item.getEmailAddress() %> </td>
                        <td> <input type="radio" name="gifterRowNumber" value="<%=item.getGifterID()%>"/> </td>
                        </tr>
            <%
                    }
                } 
                else
                {
            %>
                    <tr>
                    <td> N/A </td>
                    <td> N/A </td>
                    <td> N/A </td>
                    <td> N/A </td>
                        <td> <input type="radio" name="gifterRowNumber" value="0"/> </td>
                    </tr>
            <%
                }
            %>
    </tbody>

</table>
                
<div>
    <input type="submit" value="New Gifter" name="newGifter"/>
    <input type="submit" value="Update" name="updateGifter"/>
    <input type="submit" value="Delete" name="deleteGifter"/>
    <input type="submit" value="Exit" name="exitGift"/> 
</div>
                
</form>
            <h3 id="date"> <%=date.toString()%> </h3>
</section>
    <footer>
        <p> &copy; Amanda Bradley, Spring 2016 UW-Whitewater, ITSCM325 Web Development Project</p>
    </footer>
</body>
</html>


