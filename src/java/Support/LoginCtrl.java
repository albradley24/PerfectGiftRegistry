/* Amanda Bradley
ITSCM 325, Web Development
Spring 2016
LoginCtrl */

package Support;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;

/**
 *
 * @author Amanda
 */

public class LoginCtrl extends HttpServlet 
{
      /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
        
    {      
        HttpSession session = request.getSession();
                        
        if (request.getParameter("username").equals("BradleyAL24") && request.getParameter("password").equals("Amanda")) 
            {
                Cookie userCookie = new Cookie ("USERNAME", request.getParameter("username"));
                userCookie.setMaxAge(86400);
                response.addCookie(userCookie);
                request.setAttribute("validLogin", true);
                //request.getRequestDispatcher("WEB-INF/listmanagerpage.jsp").forward(request, response);
                request.getRequestDispatcher("EventMgrCtrl").forward(request, response);
            }
        else
            {
                request.setAttribute("invalidLogin", "Invalid login. Try again.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
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
