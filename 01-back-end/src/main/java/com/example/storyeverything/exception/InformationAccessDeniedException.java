package com.example.storyeverything.exception;

public class InformationAccessDeniedException extends RuntimeException{
    private String userLogin;
    private Object informationId;

    public InformationAccessDeniedException(String userLogin, Object informationId) {
        super(String.format("User with login '%s' doesn't have access to information with id: '%s'", userLogin, informationId));
        this.userLogin = userLogin;
        this.informationId = informationId;
    }

    // Getters
    public String getUserLogin() {
        return userLogin;
    }

    public Object getInformationId() {
        return informationId;
    }

}
