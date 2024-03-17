package com.example.storyeverything.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;

@Entity(name = "information")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name = "title", nullable = false)
    @Size(max = 20, message = "{validation.name.size}")
    private String title;
    @NonNull
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;
    @NonNull
    @Column(name = "link", columnDefinition = "TEXT")
    private String link;
    @Column(name = "add_date", nullable = false, insertable = false, updatable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    @Temporal(TemporalType.DATE)
    private Date addDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_id", nullable = false)
    private UserAccount userAccount;
}
