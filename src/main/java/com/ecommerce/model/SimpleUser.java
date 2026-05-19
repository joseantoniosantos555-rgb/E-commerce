package com.ecommerce.model;

public class SimpleUser {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private UserType type;
    
    public enum UserType {
        ADMIN, CLIENTE
    }
    
    public SimpleUser(String username, String password, String fullName, String email, UserType type) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.type = type;
    }
    
    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public UserType getType() { return type; }
    public void setType(UserType type) { this.type = type; }
    
    public boolean isAdmin() { return type == UserType.ADMIN; }
}