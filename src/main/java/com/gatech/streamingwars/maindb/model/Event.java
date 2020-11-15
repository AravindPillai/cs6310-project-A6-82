package com.gatech.streamingwars.maindb.model;

import com.gatech.streamingwars.common.AuditEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "event", schema = "main")
@Data
public class Event extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String name;
    private int year;
    private int duration;
    private String eventType;
    private String studioShortName;
    private Integer eventLicensingFee;

    public Event() {
    }

    public Event(String eventType, String name, int year, int duration, String studioShortName, Integer eventLicenseFees) {
        this.eventType = eventType;
        this.name = name;
        this.year = year;
        this.duration = duration;
        this.studioShortName = studioShortName;
        this.eventLicensingFee = eventLicenseFees;
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

    public String getStudioShortName() {
        return studioShortName;
    }

    public void setStudioShortName(String studioShortName) {
        this.studioShortName = studioShortName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Integer getEventLicensingFee() {
        return eventLicensingFee;
    }

    public void setEventLicensingFee(Integer eventLicensingFee) {
        this.eventLicensingFee = eventLicensingFee;
    }
}
