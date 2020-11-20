package com.gatech.streamingwars.ui;

public class StreamTransactionSummary {

    private Long id;
    private String shortName;
    private String longName;
    private int subscriptionPrice;
    private int currentPeriod;
    private int previousPeriod;
    private  int total;
    private int licensing;
    private boolean editable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(int currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public int getPreviousPeriod() {
        return previousPeriod;
    }

    public void setPreviousPeriod(int previousPeriod) {
        this.previousPeriod = previousPeriod;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLicensing() {
        return licensing;
    }

    public void setLicensing(int licensing) {
        this.licensing = licensing;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
