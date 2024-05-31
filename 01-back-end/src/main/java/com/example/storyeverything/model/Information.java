package com.example.storyeverything.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "information")
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @NotNull
    @Column(name = "add_date", nullable = false)
    private LocalDate addDate;

    @Column(name = "remainder", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP + interval '1 day'")
    private LocalDateTime remainder;

    @Column(name = "is_public", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isPublic = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_account_information",
            joinColumns = @JoinColumn(name = "information_id"),
            inverseJoinColumns = @JoinColumn(name = "user_account_id")
    )
    private Set<UserAccount> userAccounts = new HashSet<>();

    @PrePersist
    public void prePersist() {
        addDate = LocalDate.now();
        if (remainder == null) {
            remainder = LocalDateTime.now().plusDays(1);
        }
        if (isPublic == null) {
            isPublic = false;
        }
    }

    public Information() {
    }

    public Information(String title, String content, String link) {
        this.title = title;
        this.content = content;
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDateTime getRemainder() {
        return remainder;
    }

    public void setRemainder(LocalDateTime remainder) {
        this.remainder = remainder;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(Set<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }
}
