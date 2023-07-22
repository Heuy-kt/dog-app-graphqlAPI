package com.devanle.dog_graphql_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Dog {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO )
    private Long id;
    private String name;
    private String breed;
    private String owner;

    public Dog(String name, String breed, String owner) {
        this.name = name;
        this.breed = breed;
        this.owner = owner;
    }

    public Dog(Long id) {
        this.id = id;
    }

    public Dog(Long id, String name, String breed, String owner) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.owner = owner;
    }

    public Dog() {

    }



}
