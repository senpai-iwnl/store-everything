package com.example.storyeverything.model;

import com.example.storyeverything.annotation.LowerCase;
import javax.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(name = "name", nullable = false)
    @Size(min = 3, max = 20, message = "The field must contain between 3 to 20 characters.")
    @LowerCase
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
