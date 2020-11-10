package com.gatech.streamingwars.configurations.model.main;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "Account", schema = "main")
@Data
public class Account {
    @Id
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
