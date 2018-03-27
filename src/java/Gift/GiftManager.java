/*Amanda Bradley
ITSCM 325, Web Development
Spring 2016
Gift Manager */

package Gift;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GiftManager extends HttpServlet 
{
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        System.err.println("Processing Started");
        if(request.getParameter("Delete") != null && request.getParameter("Delete") != "")
        {
            DeleteGifts(request, response);
        }
        else if(request.getParameter("Add") != null && request.getParameter("Add") != "")
        {
            AddGifts(request, response);
        }
        else if(request.getParameter("GetGift") != null && request.getParameter("GetGift") != "")
        {
            GetGift(request, response);
        }
        else
        {
            GetGifts(request,response);
        }
    }
    
    protected void GetGifts(HttpServletRequest request, HttpServletResponse response)
    {
            System.err.println("Getting Gifts");
            HttpSession session = request.getSession();
            String dbURL = "jdbc:mysql://adams.uww.edu:3306/s16wbbradleyal";
            String username = "s16wbbradleyal";
            String password = "1784429";
            String gifts = "<Gifts>";
            try
            {
                Connection connection = (Connection) DriverManager.getConnection(dbURL, username, password);
                String query = "SELECT * FROM Gift;" ;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                System.err.println(query);
                while(resultSet.next())
                {
                    gifts = gifts + "<Gift><giftID>";
                    gifts = gifts + resultSet.getInt("giftID") + "</giftID><giftName>";
                    gifts = gifts + resultSet.getString("giftName") + "</giftName><giftPrice>";
                    gifts = gifts + resultSet.getString("giftPrice") + "</giftPrice><giftLocation>";
                    gifts = gifts + resultSet.getString("giftLocation") + "</giftLocation><giftURL>";
                    gifts = gifts + resultSet.getString("giftURL") + "</giftURL><giftDescription>";
                    gifts = gifts + resultSet.getString("giftDescription") + "</giftDescription><imageName>";
                    gifts = gifts + resultSet.getString("imageName") + "</imageName></Gift>";
                }
                gifts = gifts + "</Gifts>";
            }
            catch(SQLException sqle)
            {
                System.err.println("Error retrieving gift: " + sqle);
            }
            try
            {
                System.err.println(gifts);
                PrintWriter writer = response.getWriter();
                writer.println(gifts);
                writer.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
    }
    
    protected void GetGift(HttpServletRequest request, HttpServletResponse response)
    {
            HttpSession session = request.getSession();
            int item = Integer.parseInt(request.getParameter("giftRowNumber"));
            String dbURL = "jdbc:mysql://adams.uww.edu:3306/s16wbbradleyal";
            String username = "s16wbbradleyal";
            String password = "1784429";
            String gifts = "<Gifts>";
            
            try
            {                
                Connection connection = (Connection) DriverManager.getConnection(dbURL, username, password);
                String query = "SELECT * FROM Gift WHERE giftID = " + item + ";" ;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while(resultSet.next())
                {
                    gifts = gifts + "<Gift><giftID>";
                    gifts = gifts + resultSet.getInt("giftID") + "</giftID><giftName>";
                    gifts = gifts + resultSet.getString("giftName") + "</giftName><giftPrice>";
                    gifts = gifts + resultSet.getString("giftPrice") + "</giftPrice><giftLocation>";
                    gifts = gifts + resultSet.getString("giftLocation") + "</giftLocation><giftURL>";
                    gifts = gifts + resultSet.getString("giftURL") + "</giftURL><giftDescription>";
                    gifts = gifts + resultSet.getString("giftDescription") + "</giftDescription><imageName>";
                    gifts = gifts + resultSet.getString("imageName") + "</imageName></Gift>";
                }
                gifts = gifts + "</Gifts>";
            }
            catch(SQLException sqle)
            {
                System.err.println("Error retrieving gift: " + sqle);
            }
            try
            {
                PrintWriter writer = response.getWriter();
                writer.println(gifts);
                writer.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }

        protected void DeleteGifts(HttpServletRequest request, HttpServletResponse response)
        {
                HttpSession session = request.getSession();
                int item = Integer.parseInt(request.getParameter("giftRowNumber"));
                String dbURL = "jdbc:mysql://adams.uww.edu:3306/s16wbbradleyal";
                String username = "s16wbbradleyal";
                String password = "1784429";
                try
                {
                    Connection connection = (Connection) DriverManager.getConnection(dbURL, username, password);
                    String query = "DELETE FROM Gift WHERE giftID=" + item + ";";
                    System.err.println(query);
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(query);
                }
                catch(SQLException sqle)
                {
                    System.err.println("Error deleting gift: " + sqle);
                }
    }
     
        
        
    protected void AddGifts(HttpServletRequest request, HttpServletResponse response)
    {
            HttpSession session = request.getSession();
            String giftName = request.getParameter("giftName");
            String giftPrice = request.getParameter("giftPrice");
            String giftLocation = request.getParameter("giftLocation");
            String giftURL = request.getParameter("giftURL");
            String giftDescription = request.getParameter("giftDescription");
            String imageName = request.getParameter("imageName");
            String dbURL = "jdbc:mysql://adams.uww.edu:3306/s16wbbradleyal";
            String username = "s16wbbradleyal";
            String password = "1784429";
            try
            {               
                Connection connection = (Connection) DriverManager.getConnection(dbURL, username, password);
                String query = "INSERT INTO Gift(giftName, giftPrice, giftLocation, giftURL, giftDescription, imageName)" 
                + " VALUES ('" + giftName + "',\"" + giftPrice + "\",\"" + giftLocation + "\",\"" + giftURL + "\",\"" + giftDescription + "\",\"" + imageName + "\")" ; 
                System.err.println(query);
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
            }
            catch(SQLException sqle)
            {
                System.err.println("Error adding gift: " + sqle);
            }
    }
  
 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
