package com.gatech.streamingwars.model.main;

public class TransactionSummary {

    private int currentPeriod;
    private int previousPeriod;
    private int total;
    private int licensing;

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
}
