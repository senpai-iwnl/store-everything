package com.example.storyeverything.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class InformationDTO {
    private long id;
    private String title;
    private String content;
    private String link;
    private LocalDate addDate;
    private LocalDate remainder;
    private boolean isPublic;
    private String categoryName;
    private long userAccountId;

    public InformationDTO() {
    }

    public InformationDTO(long id, String title, String content, String link, LocalDate addDate, LocalDate remainder, boolean isPublic, String categoryName, long userAccountId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.link = link;
        this.addDate = addDate;
        this.remainder = remainder;
        this.isPublic = isPublic;
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

    public LocalDate getRemainder() {
        return remainder;
    }

    public void setRemainder(LocalDate remainder) {
        this.remainder = remainder;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
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
