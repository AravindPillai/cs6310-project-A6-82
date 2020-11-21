package com.gatech.streamingwars.ui;

import com.gatech.streamingwars.maindb.model.EventOffer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventOfferData {
   private Long id;
   private Long eventId;
   private String eventName;
   private int eventYear;
   private Long serviceId;
   private String serviceName;
   private LocalDateTime createdAt;
   private boolean retracted;

   public EventOfferData()
   {

   }
   public EventOfferData(Long id, Long eventId, String name, Long id2, String shortName, LocalDateTime createdAt,boolean retracted,int eventYear) {
      this.id =id;
      this.eventId = eventId;
      this.eventName = name;
      this.serviceId = id2;
      this.serviceName = shortName;
      this.createdAt = createdAt;
      this.retracted = retracted;
      this.eventYear = eventYear;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getEventId() {
      return eventId;
   }

   public void setEventId(Long eventId) {
      this.eventId = eventId;
   }

   public String getEventName() {
      return eventName;
   }

   public void setEventName(String eventName) {
      this.eventName = eventName;
   }

   public Long getServiceId() {
      return serviceId;
   }

   public void setServiceId(Long serviceId) {
      this.serviceId = serviceId;
   }

   public String getServiceName() {
      return serviceName;
   }

   public void setServiceName(String serviceName) {
      this.serviceName = serviceName;
   }

   public LocalDateTime getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(LocalDateTime createdAt) {
      this.createdAt = createdAt;
   }

   public boolean isRetracted() {
      return retracted;
   }

   public void setRetracted(boolean retracted) {
      this.retracted = retracted;
   }

   public int getEventYear() {
      return eventYear;
   }

   public void setEventYear(int eventYear) {
      this.eventYear = eventYear;
   }
}
