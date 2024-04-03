package com.example.storyeverything.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "information")

public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(name = "title", nullable = false)
    @Size(min = 3, max = 20, message = "The field must contain between 3 to 20 characters.")
    private String title;
    @NotNull
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    @Size(min = 5, max = 500, message = "The field must contain between 5 to 500 characters.")
    private String content;
    @Column(name = "link", columnDefinition = "TEXT")
    private String link;
    @Column(name = "add_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate addDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_id", nullable = false)
    private UserAccount userAccount;

    @PrePersist
    public void prePersist() {
        addDate = LocalDate.now();
    }

    public Information() {
    }

    public Information(String title, String content, String link) {
        this.title = title;
        this.content = content;
        this.link = link;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public LocalDate getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
