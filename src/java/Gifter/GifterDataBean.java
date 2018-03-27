/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gifter;

/**
 *
 * @author Amanda
 */
public class GifterDataBean 
{
    private int gifterID = 0;
    private String event = "";
    private String registeredGifter = "";
    private String relationship = "";
    private String emailAddress = "";
        
    public GifterDataBean()
    {
        
    }
    
    public GifterDataBean(int inGifterID, String inEvent, String inRegisteredGifter, String inRelationship, String inEmailAddress)
    {
        setGifterID(inGifterID);
        setEvent(inEvent);
        setRegisteredGifter(inRegisteredGifter);
        setRelationship(inRelationship);
        setEmailAddress(inEmailAddress);
    }
    
    public GifterDataBean(String inEvent, String inRegisteredGifter, String inRelationship, String inEmailAddress)
    {
        setEvent(inEvent);
        setRegisteredGifter(inRegisteredGifter);
        setRelationship(inRelationship);
        setEmailAddress(inEmailAddress);
    }
    
    
        public int getGifterID() {
        return gifterID;
    }

    /**
     * @param gifterID the event to set
     */
    public void setGifterID(int gifterID) {
        this.gifterID = gifterID;
    }

    /**
     * @return the gifterID
     */
        
    public String getEvent() {
        return event;
    }

    /**
     * @param event the event to set
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * @return the registeredGifter
     */
    public String getRegisteredGifter() {
        return registeredGifter;
    }

    /**
     * @param registeredGifter the registeredGifter to set
     */
    public void setRegisteredGifter(String registeredGifter) {
        this.registeredGifter = registeredGifter;
    }
        
    /**
     * @return the relationship
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * @param relationship the relationship to set
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    
    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
