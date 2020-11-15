package com.gatech.streamingwars.model.main;

import com.gatech.streamingwars.model.AuditEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "transaction", schema = "main")
@Data
public class Transaction extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String transactionType;
    private String buyer;
    private String vendor;
    private int transactionCost;
    private String eventType;
    private String eventName;
    private int eventYear;
    private int eventDuration;
    // dateOfTransaction should be covered by AuditEntity
    private int ppvCost;
    private int percentage; //based on *100

    public Transaction() {}
    public Transaction(String transactionType, String buyer, String vendor, int transactionCost,
                       String eventName, String eventType, int eventDuration, int eventYear, int ppvCost, int percentage)
    {
        this.transactionType = transactionType;
        this.buyer = buyer;
        this.vendor = vendor;
        this.transactionCost = transactionCost;
        this.eventName = eventName;
        this.eventDuration = eventDuration;
        this.eventYear = eventYear;
        this.eventType = eventType;
        this.ppvCost = ppvCost;
        this.percentage = percentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getTransactionCost() {
        return transactionCost;
    }

    public void setTransactionCost(int transactionCost) {
        this.transactionCost = transactionCost;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventYear() {
        return eventYear;
    }

    public void setEventYear(int eventYear) {
        this.eventYear = eventYear;
    }

    public int getPpvCost() {
        return ppvCost;
    }

    public void setPpvCost(int ppvCost) {
        this.ppvCost = ppvCost;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(int eventDuration) {
        this.eventDuration = eventDuration;
    }
}
