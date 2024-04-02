package com.example.storyeverything.model;

import jakarta.persistence.*;

import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", nullable = false)
    @Size(max = 20, message = "{validation.name.size}")
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Information> information;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Information> getInformation() {
        return information;
    }

    public void setInformation(List<Information> information) {
        this.information = information;
    }
}
