package com.gatech.streamingwars.model.main;

import com.gatech.streamingwars.model.AuditEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "streamingservice", schema = "main")
@Data
public class StreamingService extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String shortName;
    private String longName;
    private int subscriptionPrice;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public int getSubscriptionPrice() {
        return subscriptionPrice;
    }

    public void setSubscriptionPrice(int subscriptionPrice) {
        this.subscriptionPrice = subscriptionPrice;
    }
}
