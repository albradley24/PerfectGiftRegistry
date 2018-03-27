/* Amanda Bradley
ITSCM 325, Web Development
Spring 2016
Gifter Error Handler */

package Gifter;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GifterErrorHandler
{
    public static boolean isGifter(HttpServletRequest request)
    {
        String event = request.getParameter("event");
        String registeredGifter = request.getParameter("registeredGifter");
        String emailAddress = request.getParameter("emailAddress");
        
        if(event != null && event != "")
        {
            if(event.contains("@"))
            {
                request.setAttribute("errorMessage", "Invalid character @");
                return false; 
            }
            if(event.contains(";"))
            {
                request.setAttribute("errorMessage", "Invalid character ;");
                return false;
            }
            if(event.contains("<"))
            {
                request.setAttribute("errorMessage", "Invalid character <");
                return false;
            }
            if(event.contains(">"))
            {
                request.setAttribute("errorMessage", "Invalid character >");
                return false;
            }
            if(event.contains("="))
            {
                request.setAttribute("errorMessage", "Invalid character =");
                return false;
            }
        }
        else
        {
            return false;
        }
        
        if(registeredGifter != null && registeredGifter != "")
        {
            if(registeredGifter.contains("@"))
            {
                request.setAttribute("errorMessage", "Invalid character @");
                return false; 
            }
            if(registeredGifter.contains(";"))
            {
                request.setAttribute("errorMessage", "Invalid character ;");
                return false;
            }
            if(registeredGifter.contains("<"))
            {
                request.setAttribute("errorMessage", "Invalid character <");
                return false;
            }
            if(registeredGifter.contains(">"))
            {
                request.setAttribute("errorMessage", "Invalid character >");
                return false;
            }
            if(registeredGifter.contains("="))
            {
                request.setAttribute("errorMessage", "Invalid character =");
                return false;
            }
        }
        else
        {
            return false;
        }
        
        if(emailAddress != null && emailAddress != "")
        {
            String atSign = "@";
            if(!emailAddress.contains(atSign))
            {
                request.setAttribute("errorMessage", "Invalid email address");
                return false;
            }
        }
        else
        {
            return false;
        }
        return true;
        
    }

    public static String isGifter(String newEvent, String newRegisteredGifter, String newEmailAddress)
    {
        String event = newEvent;
        String registeredGifter = newRegisteredGifter;
        String emailAddress = newEmailAddress;
        String errorMessage = "";
        
        if(event != null && event != "")
        {
            if(event.contains("@"))
            {
                errorMessage = "Invalid character @";
            }
            if(event.contains(";"))
            {
                errorMessage = "Invalid character ;";
            }
            if(event.contains("<"))
            {
                errorMessage = "Invalid character <";
            }
            if(event.contains(">"))
            {
                errorMessage = "Invalid character >";
            }
            if(event.contains("="))
            {
                errorMessage = "Invalid character =";
            }
        }
        else
        {
            errorMessage = "Missing value: Event Name";
        }
        
        if(registeredGifter != null && registeredGifter != "")
        {
            if(registeredGifter.contains("@"))
            {
                errorMessage = "Invalid character @"; 
            }
            if(registeredGifter.contains(";"))
            {
                errorMessage = "Invalid character ;";
            }
            if(registeredGifter.contains("<"))
            {
                errorMessage = "Invalid character <";
            }
            if(registeredGifter.contains(">"))
            {
                errorMessage = "Invalid character >";
            }
            if(registeredGifter.contains("="))
            {
                errorMessage = "Invalid character =";
            }
        }
        else
        {
            errorMessage = "Missing value: Gifter Name";
        }
               
        if(emailAddress != null && emailAddress != "")
        {
            Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailAddress);
            if(!matcher.find())
            {
                errorMessage = "Invalid email address";
            }
        }
        else
        {
            errorMessage = "Missing value: Email Address";
        }
        return errorMessage;
    }
}
