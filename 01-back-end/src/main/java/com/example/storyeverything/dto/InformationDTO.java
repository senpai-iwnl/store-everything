package com.example.storyeverything.dto;

import java.time.LocalDate;

public class InformationDTO {
    private long id;
    private String title;
    private String content;
    private String link;
    private LocalDate addDate;
    private String categoryName;
    private long userAccountId;

    public InformationDTO() {
    }

    public InformationDTO(long id, String title, String content, String link, LocalDate addDate, String categoryName, long userAccountId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.link = link;
        this.addDate = addDate;
        this.categoryName = categoryName;
        this.userAccountId = userAccountId;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(long userAccountId) {
        this.userAccountId = userAccountId;
    }
}
