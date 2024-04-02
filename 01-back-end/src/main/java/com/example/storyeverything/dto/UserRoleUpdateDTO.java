package com.example.storyeverything.dto;

public class UserRoleUpdateDTO {
    private String role;

    public UserRoleUpdateDTO() {
    }

    public UserRoleUpdateDTO(String roleName) {
        this.role = roleName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}