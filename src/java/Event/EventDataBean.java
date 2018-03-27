/* Amanda Bradley
ITSCM 345, Web Development
Spring 2016
GifterData Java Class */

package Event;

/**
 *
 * @author Amanda
 */

public class EventDataBean 
{
        private int eventID = 0;
        private String eventDate = "";
        private String eventName = "";
        private String eventLocation = "";
        private String eventDescription = "";
                 
public EventDataBean()
{
    
}

public EventDataBean(int inEventID, String inEventDate, String inEventName, String inEventLocation, String inEventDescription)
{
    setEventID(inEventID);
    setEventDate(inEventDate);
    setEventName(inEventName);
    setEventLocation(inEventLocation);
    setEventDescription(inEventDescription);
    }

public EventDataBean(String inEventDate, String inEventName, String inEventLocation, String inEventDescription)
{
    setEventDate(inEventDate);
    setEventName(inEventName);
    setEventLocation(inEventLocation);
    setEventDescription(inEventDescription);
    }

    /**
     * @return the eventID
     */
    public int getEventID() {
        return eventID;
    }

    /**
     * @param eventID the eventID to set
     */
    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    /**
     * @return the eventID
     */
    
    public String getEventDate() {
        return eventDate;
    }

    /**
     * @param eventDate the eventDate to set
     */
    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return the eventName
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * @param eventName the eventName to set
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * @return the eventLocation
     */
    public String getEventLocation() {
        return eventLocation;
    }

    /**
     * @param eventLocation the eventLocation to set
     */
    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    /**
     * @return the eventDescription
     */
    public String getEventDescription() {
        return eventDescription;
    }

    /**
     * @param eventDescription the eventDescription to set
     */
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

}

