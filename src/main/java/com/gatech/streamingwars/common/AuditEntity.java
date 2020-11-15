package com.gatech.streamingwars.common;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditEntity {

    //Activate this if it needs to be auto create.
    //@Column(name = "created_at", nullable = false, updatable = true)
    //@CreationTimestamp
    private LocalDateTime createdAt;

    private String currentMonthYear;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void prePersist() {
     setCreatedAt(this.getCreateDate(this.getCurrentMonthYear()));
    }

    private LocalDateTime getCreateDate(String currentMonthYear)
    {
        String[] split = currentMonthYear.split("-");
        LocalTime time = LocalTime.of(00, 00);
        LocalDate date = LocalDate.of(Integer.parseInt(split[1]), Month.of(Integer.parseInt(split[0])), 01);
        LocalDateTime combined = date.atTime(time);
        return combined;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCurrentMonthYear() {
        return currentMonthYear;
    }

    public void setCurrentMonthYear(String currentMonthYear) {
        this.currentMonthYear = currentMonthYear;
    }


}
