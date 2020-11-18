package com.gatech.streamingwars.maindb.model;

import com.gatech.streamingwars.common.AuditEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "demographicgroup", schema = "main")
@Data
public class DemographicGroup extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(unique = true)
    private String shortName;
    private String longName;
    private String description;
    private boolean archived;
    private int numberOfAccounts;

    public DemographicGroup() {

    }

    public DemographicGroup(String shortName, String longName, String description, int numberOfAccounts) {
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.numberOfAccounts = numberOfAccounts;
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

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public int getNumberOfAccounts() {
        return numberOfAccounts;
    }

    public void setNumberOfAccounts(int numberOfAccounts) {
        this.numberOfAccounts = numberOfAccounts;
    }
}
