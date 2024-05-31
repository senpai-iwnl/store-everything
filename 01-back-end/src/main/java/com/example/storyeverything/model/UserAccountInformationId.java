package com.example.storyeverything.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserAccountInformationId implements Serializable {

    private Long userAccountId;
    private Long informationId;

    public UserAccountInformationId() {
    }

    public UserAccountInformationId(Long userAccountId, Long informationId) {
        this.userAccountId = userAccountId;
        this.informationId = informationId;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public Long getInformationId() {
        return informationId;
    }

    public void setInformationId(Long informationId) {
        this.informationId = informationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccountInformationId that = (UserAccountInformationId) o;
        return Objects.equals(userAccountId, that.userAccountId) && Objects.equals(informationId, that.informationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAccountId, informationId);
    }
}
