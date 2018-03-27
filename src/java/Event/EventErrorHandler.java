/* Amanda Bradley
ITSCM 325, Web Development
Spring 2016
Event Error Handler */

package Event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;


public class EventErrorHandler
{
    
    public static boolean isEvent(HttpServletRequest request)
    {
        String eventDate = request.getParameter("eventDate");
        String eventName = request.getParameter("eventName");
        String eventLocation = request.getParameter("eventLocation");
        String eventDescription = request.getParameter("eventDescription");
        
        if(eventDate != null && eventDate != "")
        {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try 
            {
                Date eventDateConvert = df.parse(eventDate);
            } 
            catch (ParseException ex) 
            {
                request.setAttribute("errorMessage", "Invalid date. Use this format xx/xx/xxxx");
                return false;
            }
        }
        else
        {
            return false;
        }
        
        if(eventName != null && eventName != "")
        {
            if(eventName.contains("@"))
            {
                request.setAttribute("errorMessage", "Invalid character @");
                return false; 
            }
            if(eventName.contains(";"))
            {
                request.setAttribute("errorMessage", "Invalid character ;");
                return false;
            }
            if(eventName.contains("<"))
            {
                request.setAttribute("errorMessage", "Invalid character <");
                return false;
            }
            if(eventName.contains(">"))
            {
                request.setAttribute("errorMessage", "Invalid character >");
                return false;
            }
            if(eventName.contains("="))
            {
                request.setAttribute("errorMessage", "Invalid character =");
                return false;
            }
            
        }
        else
        {
            return false;
        }
        
        if(eventLocation != null && eventLocation != "")
        {
            if(eventLocation.contains("@"))
            {
                request.setAttribute("errorMessage", "Invalid character @");
                return false; 
            }
            if(eventLocation.contains(";"))
            {
                request.setAttribute("errorMessage", "Invalid character ;");
                return false;
            }
            if(eventLocation.contains("<"))
            {
                request.setAttribute("errorMessage", "Invalid character <");
                return false;
            }
            if(eventLocation.contains(">"))
            {
                request.setAttribute("errorMessage", "Invalid character >");
                return false;
            }
            if(eventLocation.contains("="))
            {
                request.setAttribute("errorMessage", "Invalid character =");
                return false;
            }
        }
        else
        {
            return false;
        }
        
        if(eventDescription != null && eventDescription != "")
        {
            if(eventDescription.contains("@"))
            {
                request.setAttribute("errorMessage", "Invalid character @");
                return false; 
            }
            if(eventDescription.contains(";"))
            {
                request.setAttribute("errorMessage", "Invalid character ;");
                return false;
            }
            if(eventDescription.contains("<"))
            {
                request.setAttribute("errorMessage", "Invalid character <");
                return false;
            }
            if(eventDescription.contains(">"))
            {
                request.setAttribute("errorMessage", "Invalid character >");
                return false;
            }
            if(eventDescription.contains("="))
            {
                request.setAttribute("errorMessage", "Invalid character =");
                return false;
            }
        }
        else
        {
            return false;
        }
        return false;
    }
    public static String isEvent(String newEventDate, String newEventName, String newEventLocation, String newEventDescription)
    {
        String eventDate = newEventDate;
        String eventName = newEventName;
        String eventLocation = newEventLocation;
        String eventDescription = newEventDescription;
        String errorMessage = "";
        
        if(eventDate != null && eventDate != "")
        {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try 
            {
                Date eventDateConvert = df.parse(eventDate);
            } 
            catch (ParseException ex) 
            {
                errorMessage = "Invalid date. Use this format: dd/mm/yyyy";
            }
        }
        else
        {
            errorMessage = "Missing value: Event Date";
        }
        
        if(eventName != null && eventName != "")
        {
            if(eventName.contains("@"))
            {
                errorMessage = "Invalid character @";
            }
            if(eventName.contains(";"))
            {
                errorMessage = "Invalid character ;";
            }
            if(eventName.contains("<"))
            {
                errorMessage = "Invalid character <";
            }
            if(eventName.contains(">"))
            {
                errorMessage = "Invalid character >";
            }
            if(eventName.contains("="))
            {
                errorMessage = "Invalid character =";
            }            
        }
        else
        {
            errorMessage = "Missing value: Event Name";
        }

        if(eventLocation != null && eventLocation != "")
        {
            if(eventLocation.contains("@"))
            {
                errorMessage = "Invalid character @"; 
            }
            if(eventLocation.contains(";"))
            {
                errorMessage = "Invalid character ;"; 
            }
            if(eventLocation.contains("<"))
            {
                errorMessage = "Invalid character <";
            }
            if(eventLocation.contains(">"))
            {
                errorMessage = "Invalid character >";
            }
            if(eventLocation.contains("="))
            {
                errorMessage = "Invalid character =";
            }
        }
        else
        {
            errorMessage = "Missing value: Event Location";
        }
        
        if(eventDescription != null && eventDescription != "")
        {
            if(eventDescription.contains("@"))
            {
                errorMessage = "Invalid character @";  
            }
            if(eventDescription.contains(";"))
            {
                errorMessage = "Invalid character ;"; 
            }
            if(eventDescription.contains("<"))
            {
                errorMessage = "Invalid character <";  
            }
            if(eventDescription.contains(">"))
            {
                errorMessage = "Invalid character >";
            }            
            if(eventDescription.contains("="))
            {
                errorMessage = "Invalid character =";
            }
        }
        else
        {
            errorMessage = "Missing value: Event Location";
        }
        return errorMessage;
    }
    
    public static String toMySQLdate(String inDate)
    {
        String outDate = "";
        outDate = inDate.substring(inDate.lastIndexOf("/")+1);
        outDate = outDate + "-" + inDate.substring(0, inDate.indexOf("/"));
        outDate = outDate + "-" + inDate.substring(inDate.indexOf("/")+1, inDate.lastIndexOf("/"));
        return outDate;
    }
    
    public static String fromMySQLdate(String inDate)
    {
        String outDate = "";
        outDate = inDate.substring(inDate.lastIndexOf("-")+1);
        outDate = outDate + "/" + inDate.substring(0, inDate.indexOf("-"));
        outDate = inDate.substring(inDate.indexOf("-")+1, inDate.lastIndexOf("-")) + "/" + outDate;
        return outDate;
    }
}