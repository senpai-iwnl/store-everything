package com.example.storyeverything.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "user_account")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name = "firt_name", nullable = false)
    @Size(max = 20, message = "{validation.name.size}")
    private String firstName;
    @NonNull
    @Column(name = "last_name", nullable = false)
    @Size(max = 20, message = "{validation.name.size}")
    private String lastName;
    @NonNull
    @Column(name = "login", nullable = false)
    @Size(max = 20, message = "{validation.name.size}")
    private String login;
    @NonNull
    @Column(name = "password", nullable = false)
    @Size(max = 255, message = "{validation.name.size}")
    private String password;
    @NonNull
    @Column(name = "age", nullable = false)
    @Min(value = 18, message = "{validation.age.restrictions}")
    private Integer age;
    @OneToMany(mappedBy = "userAccount", fetch = FetchType.LAZY)
    private List<Information> information;
}
