package com.gatech.streamingwars.configurations.model.main;

public class Event {
    public static enum EventTypes {movie,ppv};
    private String name;
    private int year;
    private int duration;
    private EventTypes eventType;
    private  Studio studio;
    private Integer eventLicensingFee;

    public Event(String eventType,String name,int year,int duration,Studio studio,Integer eventLicenseFees)
    {
        this.eventType = EventTypes.valueOf(eventType);
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.studio = studio;
        this.eventLicensingFee =eventLicenseFees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public EventTypes getEventType() {
        return eventType;
    }

    public void setEventType(EventTypes eventType) {
        this.eventType = eventType;
    }

    public Integer getEventLicensingFee() {
        return eventLicensingFee;
    }
    public void setEventLicensingFee(Integer eventLicensingFee) {
        this.eventLicensingFee = eventLicensingFee;
    }
}
