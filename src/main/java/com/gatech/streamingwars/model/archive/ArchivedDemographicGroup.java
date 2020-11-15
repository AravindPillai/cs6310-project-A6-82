package com.gatech.streamingwars.model.archive;

import com.gatech.streamingwars.model.AuditEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "archived_demographic_group", schema = "archive")
@Data
public class ArchivedDemographicGroup extends AuditEntity {
    @Id
    Long id;
    private String shortName;
    private String longName;
    private String description;
    private String currentMonthYear;
    private boolean isArchived;

    public ArchivedDemographicGroup()
    {

    }

    public ArchivedDemographicGroup(Long id,String shortName, String longName,String description) {
        this.id = id;
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrentMonthYear() {
        return currentMonthYear;
    }

    public void setCurrentMonthYear(String currentMonthYear) {
        this.currentMonthYear = currentMonthYear;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }
}
