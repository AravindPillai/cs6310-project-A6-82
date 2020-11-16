package com.gatech.streamingwars.maindb.model;

import com.gatech.streamingwars.common.AuditEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "account", schema = "main")
@Data
public class Account extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;

    public Account() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
