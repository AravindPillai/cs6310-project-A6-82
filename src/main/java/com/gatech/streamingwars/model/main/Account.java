package com.gatech.streamingwars.model.main;

import com.gatech.streamingwars.model.AuditEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Account", schema = "main")
@Data
public class Account extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;
    String name;

    public Account(){
        id = UUID.randomUUID().toString();
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
