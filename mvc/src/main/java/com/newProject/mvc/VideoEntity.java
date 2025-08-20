package com.newProject.mvc;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class VideoEntity {
    //@Id is primary key and @GeneratedValue is Auto generated value.
    private @Id @GeneratedValue Long id;
    private String name;
    private String description;

    // For JPA
    protected VideoEntity(){
        this(null, null);
    }

    // For creating new entry in DB
    public VideoEntity(String name, String description) {
        this.id = null; //ID is null so JPA knows it’s new
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
