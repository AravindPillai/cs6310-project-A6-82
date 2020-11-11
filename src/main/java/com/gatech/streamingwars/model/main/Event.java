package com.gatech.streamingwars.model.main;

import com.gatech.streamingwars.model.AuditEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Event", schema = "main")
@Data
public class Event extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;
    public static enum EventTypes {movie,ppv};
    private String name;
    private int year;
    private int duration;
    private EventTypes eventType;
    //private  Studio studio;
    private Integer eventLicensingFee;

    public Event() {    }
    public Event(String eventType,String name,int year,int duration,Studio studio,Integer eventLicenseFees)
    {
        this.eventType = EventTypes.valueOf(eventType);
        this.name = name;
        this.year = year;
        this.duration = duration;
        //this.studio = studio;
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

//    public Studio getStudio() {
//        return studio;
//    }
//
//    public void setStudio(Studio studio) {
//        this.studio = studio;
//    }

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
