package Gifter;

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
public class GifterCtrl 
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
    
    public static ArrayList<GifterDataBean> getGifters(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        GifterDataBean gifterBean = null;
        ArrayList<GifterDataBean> getGifters = new ArrayList();
        if(session.getAttribute("connection") != null)
        { 
            Connection connection = (Connection)session.getAttribute("connection");
            String query = "SELECT * FROM Gifter;" ;
            try
            {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                    while(resultSet.next())
                    {
                        gifterBean = new GifterDataBean(
                        resultSet.getInt("gifterID"),
                        resultSet.getString("event"),
                        resultSet.getString("registeredGifter"),
                        resultSet.getString("relationship"),
                        resultSet.getString("emailAddress"));
                        getGifters.add(gifterBean);
                    }
            }
            catch(SQLException sqle)
            {
                System.err.println("Error retrieving gifters: " + sqle);
            }
        }
        return getGifters;
    }
    
    public static GifterDataBean getGifter(int gifterID, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        GifterDataBean gifterBean = null;
        if(session.getAttribute("connection") != null)
        { 
            Connection connection = (Connection)session.getAttribute("connection");
            String query = "SELECT * FROM Gifter WHERE gifterID = " + gifterID + ";" ;
            try
            {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                    while(resultSet.next())
                    {
                        gifterBean = new GifterDataBean(
                        resultSet.getInt("gifterID"),
                        resultSet.getString("event"),
                        resultSet.getString("registeredGifter"),
                        resultSet.getString("relationship"),
                        resultSet.getString("emailAddress"));
                    }
            }
            catch(SQLException sqle)
            {
                System.err.println("Error retrieving gifters: " + sqle);
            }
        }
        return gifterBean;
    }

    public static Boolean addGifter(GifterDataBean newBean, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        Boolean success = false;
        if(session.getAttribute("connection") != null)
        {
            Connection connection = (Connection)session.getAttribute("connection");
            String query = "INSERT INTO Gifter(event, registeredGifter, relationship, emailAddress)"
            + " VALUES (\"" + newBean.getEvent() + "\",\"" + newBean.getRegisteredGifter() + "\",\"" + newBean.getRelationship() + "\",\"" + newBean.getEmailAddress() + "\")" ;
            System.err.println(query);
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
        
    public static Boolean changeGifter(GifterDataBean changeGifter, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        Boolean success = false;
        if(session.getAttribute("connection") != null)
        { 
            Connection connection = (Connection)session.getAttribute("connection");
            String query = "UPDATE Event" +
                    "SET event = \'" + changeGifter.getEvent() +
                    "\', registeredGifter = \'" + changeGifter.getRegisteredGifter() +
                    "\', relationship = \'" + changeGifter.getRelationship() +
                    "\', emailAddress = \'" + changeGifter.getEmailAddress() +
                    "\' WHERE gifterID = " + changeGifter.getGifterID();
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
    
    public static Boolean deleteGifter(int gifterID, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        Boolean success = false;
        if(session.getAttribute("connection") != null)
        { 
            Connection connection = (Connection)session.getAttribute("connection");
            String query = "DELETE FROM Gifter WHERE gifterID = " + gifterID + ";" ;
            try
            {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                success = true;
            }
            catch(SQLException sqle)
            {
                success = false;
                System.err.println("Error deleting gifters: " + sqle);
            }
        }
        return success;
    }
}