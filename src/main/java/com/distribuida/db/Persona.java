package com.distribuida.db;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;


@Entity
@Table(name="persona")
@SequenceGenerator(name = "personaSeq", sequenceName = "persona_seq", allocationSize = 1)
public class Persona extends PanacheEntity {
    //@Id
    //private Integer id;
    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

