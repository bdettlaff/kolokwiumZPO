package manager;

public class Event {
    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsFootballPlayerVisible() {
        return isFootballPlayerVisible;
    }

    public void setIsFootballPlayerVisible(int isFootballPlayerVisible) {
        this.isFootballPlayerVisible = isFootballPlayerVisible;
    }

    private int eventID;
    private String eventType;
    private String content;
    private int isFootballPlayerVisible;
}
