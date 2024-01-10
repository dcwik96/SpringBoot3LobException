package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class FooEntity {
    @Id
    private Long id;

    @Column
    @Lob
    private String comment;

    public FooEntity() {
    }

    public FooEntity(Long id, String comment) {
        this.id = id;
        this.comment = comment;
    }


    public String getComment() {
        return comment;
    }
}
