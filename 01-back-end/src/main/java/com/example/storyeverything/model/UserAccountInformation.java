package com.example.storyeverything.model;

import javax.persistence.*;

@Entity(name = "user_account_information")
public class UserAccountInformation {

    @EmbeddedId
    private UserAccountInformationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userAccountId")
    @JoinColumn(name = "user_account_id")
    private UserAccount userAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("informationId")
    @JoinColumn(name = "information_id")
    private Information information;

    @Column(name = "owner", nullable = false)
    private boolean owner;

    public UserAccountInformation() {
    }

    public UserAccountInformation(UserAccount userAccount, Information information, boolean owner) {
        this.userAccount = userAccount;
        this.information = information;
        this.owner = owner;
        this.id = new UserAccountInformationId(userAccount.getId(), information.getId());
    }

    public UserAccountInformationId getId() {
        return id;
    }

    public void setId(UserAccountInformationId id) {
        this.id = id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    // Getters and setters...
}
