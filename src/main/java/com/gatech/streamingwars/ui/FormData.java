package com.gatech.streamingwars.ui;

import com.gatech.streamingwars.maindb.model.EventOffer;

import java.util.ArrayList;
import java.util.List;

public class FormData {
    String startDate;
    String endDate;
    List<EventOfferData> eventOffers;
    
    public FormData()
    {
        eventOffers = new ArrayList<EventOfferData>();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<EventOfferData> getEventOffers() {
        return eventOffers;
    }

    public void setEventOffers(List<EventOfferData> eventOffers) {
        this.eventOffers = eventOffers;
    }
}
