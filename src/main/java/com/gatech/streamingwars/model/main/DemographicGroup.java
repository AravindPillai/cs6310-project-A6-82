package com.gatech.streamingwars.model.main;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "demographicgroup", schema = "main")
@Data
public class DemographicGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String shortName;
    private String longName;
    private String description;

    public DemographicGroup()
    {

    }

    public DemographicGroup(String shortName, String longName,int numberOfAccounts) {
        this.shortName = shortName;
        this.longName = longName;
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
}
