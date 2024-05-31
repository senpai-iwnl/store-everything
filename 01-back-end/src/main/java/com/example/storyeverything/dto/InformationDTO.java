package com.example.storyeverything.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public class InformationDTO {
    private long id;
    private String title;
    private String content;
    private String link;
    private LocalDate addDate;
    private LocalDateTime remainder;
    private boolean isPublic;
    private String categoryName;
    private Set<Long> userAccountIds;

    public InformationDTO() {
    }

    public InformationDTO(long id, String title, String content, String link, LocalDate addDate, LocalDateTime remainder, boolean isPublic, String categoryName, Set<Long> userAccountIds) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.link = link;
        this.addDate = addDate;
        this.remainder = remainder;
        this.isPublic = isPublic;
        this.categoryName = categoryName;
        this.userAccountIds = userAccountIds;
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

    public LocalDateTime getRemainder() {
        return remainder;
    }

    public void setRemainder(LocalDateTime remainder) {
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

    public Set<Long> getUserAccountIds() {
        return userAccountIds;
    }

    public void setUserAccountIds(Set<Long> userAccountIds) {
        this.userAccountIds = userAccountIds;
    }
}
