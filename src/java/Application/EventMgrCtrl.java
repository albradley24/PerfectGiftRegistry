/* Amanda Bradley
ITSCM 325, Web Development
Spring 2016
EventMgrCtrl */

package Application;

import Event.EventCtrl;
import Event.EventDataBean;
import Event.EventErrorHandler;
import Gifter.GifterErrorHandler;
import Gifter.GifterDataBean;
import Gifter.GifterCtrl;
import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.util.*;

public class EventMgrCtrl extends HttpServlet 
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
        int rowNumber = 0;
        
        GifterErrorHandler gifterError = new GifterErrorHandler();
        EventErrorHandler eventError = new EventErrorHandler();
        
        ArrayList<EventDataBean> eventList = null;
        ArrayList<GifterDataBean> gifterList = null;
        
        int eventID = 0;
        int gifterID = 0;
        
        response.setContentType("text/html;charset=UTF-8");
        
        if((request.getParameter("exitEvent") != null) || (request.getParameter("exitGift") != null))
        {    
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
        if(request.getParameter("newEvent") != null)
        {
            request.getRequestDispatcher("WEB-INF/eventpage.jsp").forward(request, response);
        }
        
        if(request.getParameter("newGifter") != null)
        {
            request.getRequestDispatcher("WEB-INF/giftgiverpage.jsp").forward(request, response);
        }
        
        if(request.getParameter("updateEvent") != null)
        {
            if(request.getParameter("eventRowNumber") != null)
            {
                int eventRowNumber = Integer.parseInt(request.getParameter("eventRowNumber"));
                EventCtrl.makeDatabaseConnection(request);
                EventDataBean updateEvent = EventCtrl.getEvent(eventRowNumber, request);
                
                request.setAttribute("oldEventDate", updateEvent.getEventDate());
                request.setAttribute("oldEventName", updateEvent.getEventName());
                request.setAttribute("oldEventLocation", updateEvent.getEventLocation());
                request.setAttribute("oldEventDescription", updateEvent.getEventDescription());
                request.setAttribute("itemLoc", updateEvent.getEventID());
                request.getRequestDispatcher("WEB-INF/eventpage.jsp").forward(request, response);
            }
            else
            {
                request.setAttribute("errorMessage", "Please select an event to update.");
            }
        }
               
        if(request.getParameter("updateGifter") != null)
        {
            if(request.getParameter("gifterRowNumber")!= null)
            {
                int gifterRowNumber = Integer.parseInt(request.getParameter("gifterRowNumber"));
                GifterCtrl.makeDatabaseConnection(request);
                GifterDataBean updateGifter = GifterCtrl.getGifter(gifterRowNumber, request);
                
                request.setAttribute("oldEvent", updateGifter.getEvent());
                request.setAttribute("oldRegisteredGifter", updateGifter.getRegisteredGifter());
                request.setAttribute("oldEmailAddress", updateGifter.getEmailAddress());
                request.setAttribute("oldRelationship", updateGifter.getRelationship());
                request.setAttribute("itemLoc", updateGifter.getGifterID());
                request.getRequestDispatcher("WEB-INF/giftgiverpage.jsp").forward(request, response);
            }
            else
            {
                request.setAttribute("errorMessage", "Please select a gifter to update.");
            }
        }
        
        if(request.getParameter("deleteEvent") != null)
        {
            eventID = Integer.parseInt(request.getParameter("eventRowNumber"));
            EventCtrl.deleteEvent(eventID, request);
        }
        
        if(request.getParameter("deleteGifter") != null)
        {
            gifterID = Integer.parseInt(request.getParameter("gifterRowNumber"));
            GifterCtrl.deleteGifter(gifterID, request); 
        }
                
        if(request.getParameter("submitEvent") != null)
        {
            eventError = new EventErrorHandler();
            String errorMessage = eventError.isEvent(request.getParameter("eventDate"),request.getParameter("eventName"),request.getParameter("eventLocation"),request.getParameter("eventDescription"));
            
            if(errorMessage.isEmpty())
            {
                if(request.getAttribute("eventRowNumber") != null)
                {
                    EventCtrl.makeDatabaseConnection(request);
                    eventID = Integer.parseInt(request.getParameter("eventID"));
                    EventDataBean newEvent = new EventDataBean(eventID,request.getParameter("eventDate"),request.getParameter("eventName"),request.getParameter("eventLocation"),request.getParameter("eventDescription"));
                    EventCtrl.changeEvent(newEvent, request);
                }
                else
                {
                    EventCtrl.makeDatabaseConnection(request);
                    EventDataBean newEvent = new EventDataBean(request.getParameter("eventDate"),request.getParameter("eventName"),request.getParameter("eventLocation"),request.getParameter("eventDescription"));
                    EventCtrl.addEvent(newEvent, request);
                }
            }
        }
                        
        if(request.getParameter("submitGifter") != null)
        {
            gifterError = new GifterErrorHandler();
            String errorMessage = gifterError.isGifter(request.getParameter("event"),request.getParameter("registeredGifter"),request.getParameter("emailAddress"));
            
            if(errorMessage.isEmpty())
            {
                if(request.getAttribute("gifterRowNumber") != null)
                {
                    GifterCtrl.makeDatabaseConnection(request);
                    gifterID = Integer.parseInt(request.getParameter("gifterID"));
                    GifterDataBean newGifter = new GifterDataBean(gifterID,request.getParameter("event"),request.getParameter("registeredGifter"),request.getParameter("relationship"),request.getParameter("emailAddress"));
                    GifterCtrl.changeGifter(newGifter, request);
                }
                else
                {
                    GifterCtrl.makeDatabaseConnection(request);
                    GifterDataBean newGifter = new GifterDataBean(request.getParameter("event"),request.getParameter("registeredGifter"),request.getParameter("relationship"),request.getParameter("emailAddress"));
                    GifterCtrl.addGifter(newGifter, request);
                }
            }
        }
        
        if(request.getParameter("viewGifts") != null)
        {
            request.getRequestDispatcher("WEB-INF/giftlist.html").forward(request, response);
        }
          
        EventCtrl.makeDatabaseConnection(request);
        GifterCtrl.makeDatabaseConnection(request);
        
        session.setAttribute("events", EventCtrl.getEvents(request));
        session.setAttribute("gifters", GifterCtrl.getGifters(request));

        request.getRequestDispatcher("WEB-INF/listmanagerpage.jsp").forward(request, response);
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