package com.example.storyeverything.model;

import jakarta.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name", nullable = false)
    @Size(max = 20, message = "{validation.name.size}")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @Size(max = 20, message = "{validation.name.size}")
    private String lastName;
    @Column(name = "login", nullable = false)
    @Size(max = 20, message = "{validation.name.size}")
    private String login;
    @Column(name = "password", nullable = false)
    @Size(max = 255, message = "{validation.name.size}")
    private String password;
    @Column(name = "age", nullable = false)
    @Min(value = 18, message = "{validation.age.restrictions}")
    private Integer age;
    @OneToMany(mappedBy = "userAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Information> information;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public UserAccount() {
    }

    public UserAccount(String firstName, String lastName, String login, String password, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.age = age;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Information> getInformation() {
        return information;
    }

    public void setInformation(List<Information> information) {
        this.information = information;
    }
}
