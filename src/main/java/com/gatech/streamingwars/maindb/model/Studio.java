package com.gatech.streamingwars.maindb.model;

import com.gatech.streamingwars.common.AuditEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "studio", schema = "main")
@Data
public class Studio extends AuditEntity implements Vendor{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String shortName;
    private String longName;
//    private List<Transaction> transactionList;


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
}
