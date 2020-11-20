package com.gatech.streamingwars.maindb.model;

import com.gatech.streamingwars.common.AuditEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "EventOffer", schema = "main",  uniqueConstraints= @UniqueConstraint(columnNames={"event_id", "service_id","currentMonthYear"}))
@Data
public class EventOffer  extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     Long id;

    @ManyToOne
    private Event event;

    @ManyToOne
    private StreamingService service;

    private boolean retracted;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public StreamingService getService() {
        return service;
    }

    public void setService(StreamingService service) {
        this.service = service;
    }

    public boolean isRetracted() {
        return retracted;
    }

    public void setRetracted(boolean retracted) {
        this.retracted = retracted;
    }
}
