/*Amanda Bradley
ITSCM 325, Web Development
Spring 2016
Event Ctrl */

package Event;

import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Amanda
 */
public class EventCtrl 
{
    public static boolean makeDatabaseConnection(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        Boolean success = false;
        String dbURL = "jdbc:mysql://adams.uww.edu:3306/s16wbbradleyal";
        String username = "s16wbbradleyal";
        String password = "1784429";   
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            try
            {
                Connection connection = (Connection) DriverManager.getConnection(dbURL, username, password);
                session.setAttribute("connection", connection);
                success = true;
            }
            catch(SQLException ex)
            {
                System.err.println("Error connecting: " + ex);
            }
        
        }
        catch(ClassNotFoundException cnfe)
        {
            success = false;
        }
        return success;
    }
    
    public static ArrayList<EventDataBean> getEvents(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        EventDataBean gifterBean = null;
        ArrayList<EventDataBean> getEvents = new ArrayList();
        if(session.getAttribute("connection") != null)
        { 
            Connection connection = (Connection)session.getAttribute("connection");
            String query = "SELECT * FROM Event;" ;
            try
            {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                    while(resultSet.next())
                    {
                        gifterBean = new EventDataBean(
                        resultSet.getInt("eventID"),
                        EventErrorHandler.fromMySQLdate(resultSet.getString("eventDate")),
                        resultSet.getString("eventName"),
                        resultSet.getString("eventLocation"),
                        resultSet.getString("eventDescription"));
                        getEvents.add(gifterBean);
                    }
            }
            catch(SQLException sqle)
            {
                System.err.println("Error retrieving events: " + sqle);
            }
        }
        return getEvents;
    }
    
    public static EventDataBean getEvent(int eventID, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        EventDataBean gifterBean = null;
        if(session.getAttribute("connection") != null)
        { 
            Connection connection = (Connection)session.getAttribute("connection");
            String query = "SELECT * FROM Event WHERE eventID = " + eventID + ";" ;
            try
            {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                    while(resultSet.next())
                    {
                        gifterBean = new EventDataBean(
                        resultSet.getInt("eventID"),
                        EventErrorHandler.fromMySQLdate(resultSet.getString("eventDate")),
                        resultSet.getString("eventName"),
                        resultSet.getString("eventLocation"),
                        resultSet.getString("eventDescription"));
                    }
            }
            catch(SQLException sqle)
            {
                System.err.println("Error retrieving events: " + sqle);
            }
        }
        return gifterBean;
    }

    public static Boolean addEvent(EventDataBean newBean, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        Boolean success = false;
        if(session.getAttribute("connection") != null)
        {
            Connection connection = (Connection)session.getAttribute("connection");
            String query = "INSERT INTO Event(eventDate, eventName, eventLocation, eventDescription)" 
            + " VALUES ('" + EventErrorHandler.toMySQLdate(newBean.getEventDate()) + "',\"" + newBean.getEventName() + "\",\"" + newBean.getEventLocation() + "\",\"" + newBean.getEventDescription() + "\")" ; 
            try
            {
               Statement statement = connection.createStatement();
               int updateRows = statement.executeUpdate(query);
               success = true;
            }
            catch(SQLException sqle)
            {
                System.err.println("Error inserting: " + sqle);
            }
        }    
        return success;
    }
        
    public static Boolean changeEvent(EventDataBean changeEvent, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        Boolean success = false;
        if(session.getAttribute("connection") != null)
        { 
            Connection connection = (Connection)session.getAttribute("connection");
            String query = "UPDATE Event" +
                    "SET eventName = \'" + changeEvent.getEventName() +
                    "\', eventLocation = \'" + changeEvent.getEventLocation() +
                    "\', eventDescription = \'" + changeEvent.getEventDescription() +
                    "\', eventDate = \'" + EventErrorHandler.toMySQLdate(changeEvent.getEventDate()) +
                    "\' WHERE eventID = " + changeEvent.getEventID();
            try
            {
                Statement statement = connection.createStatement();
                int updateRows = statement.executeUpdate(query);
                success = true;
            }
            catch(SQLException sqle)
            {
                System.err.println("Error updating: " + sqle);
            }
        }
        return true;
    }
    
    public static Boolean deleteEvent(int eventID, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        Boolean success = false;
        if(session.getAttribute("connection") != null)
        { 
            Connection connection = (Connection)session.getAttribute("connection");
            String query = "DELETE FROM Event WHERE eventID=" + eventID + ";" ;
            try
            {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                success = true;
            }
            catch(SQLException sqle)
            {
                success = false;
                System.err.println("Error deleting events: " + sqle);
            }
        }
        return success;
    }
}
        
       
