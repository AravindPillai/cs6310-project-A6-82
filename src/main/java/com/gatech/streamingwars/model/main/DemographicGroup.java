package com.gatech.streamingwars.model.main;

import com.gatech.streamingwars.model.AuditEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "demographicgroup", schema = "main")
@Data
public class DemographicGroup extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(unique=true)
    private String shortName;
    private String longName;
    private String description;
<<<<<<< Updated upstream:src/main/java/com/gatech/streamingwars/model/main/DemographicGroup.java
=======
    private boolean isArchived;
>>>>>>> Stashed changes:src/main/java/com/gatech/streamingwars/maindb/model/DemographicGroup.java
    private int numberOfAccounts;

    public DemographicGroup()
    {

    }

    public DemographicGroup(String shortName, String longName, String description, int numberOfAccounts) {
        this.shortName = shortName;
        this.longName = longName;
<<<<<<< Updated upstream:src/main/java/com/gatech/streamingwars/model/main/DemographicGroup.java
        this.description = description;
=======
>>>>>>> Stashed changes:src/main/java/com/gatech/streamingwars/maindb/model/DemographicGroup.java
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

    public int getNumberOfAccounts() {
        return numberOfAccounts;
    }

    public void setNumberOfAccounts(int numberOfAccounts) {
        this.numberOfAccounts = numberOfAccounts;
    }
<<<<<<< Updated upstream:src/main/java/com/gatech/streamingwars/model/main/DemographicGroup.java
=======

    public int getNumberOfAccounts() {
        return numberOfAccounts;
    }

    public void setNumberOfAccounts(int numberOfAccounts) {
        this.numberOfAccounts = numberOfAccounts;
    }
>>>>>>> Stashed changes:src/main/java/com/gatech/streamingwars/maindb/model/DemographicGroup.java
}
