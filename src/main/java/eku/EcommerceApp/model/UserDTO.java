package eku.EcommerceApp.model;

import jakarta.persistence.Entity;

import java.util.Date;

public class UserDTO {
    private int user_id;
    private String user_name;
    private String role;
    private Date created_at;

    public UserDTO(int user_id, String user_name, String role, Date created_at) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.role = role;
        this.created_at = created_at;
    }

    public UserDTO(){;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
