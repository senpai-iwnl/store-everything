package com.example.storyeverything.model;

import com.example.storyeverything.annotation.StartsWithCapital;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToMany(mappedBy = "userAccounts", fetch = FetchType.LAZY)
    private Set<Information> information = new HashSet<>();

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<Information> getInformation() {
        return information;
    }

    public void setInformation(Set<Information> information) {
        this.information = information;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
