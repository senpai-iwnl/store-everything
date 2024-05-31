package com.example.storyeverything.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;

public class UserAccountDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private int age;
    private String role;
    @JsonIgnore
    private Set<Long> informationIds;

    public UserAccountDTO() {
    }

    public UserAccountDTO(long id, String firstName, String lastName, String login, String password, int age, String role, Set<Long> informationIds) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.age = age;
        this.role = role;
        this.informationIds = informationIds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Long> getInformationIds() {
        return informationIds;
    }

    public void setInformationIds(Set<Long> informationIds) {
        this.informationIds = informationIds;
    }
}
