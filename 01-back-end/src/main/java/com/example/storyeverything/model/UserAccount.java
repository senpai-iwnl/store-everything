package com.example.storyeverything.model;

import com.example.storyeverything.annotation.StartsWithCapital;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(name = "first_name", nullable = false)
    @Size(min = 3, max = 20, message = "The field must contain between 3 to 20 characters.")
    @StartsWithCapital
    private String firstName;
    @NotNull
    @Column(name = "last_name", nullable = false)
    @Size(min = 3, max = 50, message = "The field must contain between 3 to 50 characters.")
    @StartsWithCapital
    private String lastName;
    @NotNull
    @Column(name = "login", nullable = false, unique = true)
    @Size(min = 3, max = 20, message = "The field must contain between 3 to 20 characters.")
    private String login;
    @NotNull
    @Column(name = "password", nullable = false)
    @Size(min = 5, message = "The field must contain at least 5 characters.")
    private String password;
    @NotNull
    @Column(name = "age", nullable = false)
    @Min(value = 18, message = "The field must have a value of at least 18.")
    private Integer age;
    @OneToMany(mappedBy = "userAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Information> information;
    @ManyToOne(fetch = FetchType.EAGER)
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
