package cn.oriki.shiro.entity;

import java.io.Serializable;

public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String role;
    private String description;
    private Boolean available = Boolean.FALSE;

    public Role() {
    }

    public Role(String role, String description, Boolean available) {
        this.role = role;
        this.description = description;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", role='" + role + '\'' + ", description='" + description + '\'' + ", available=" + available + '}';
    }
}
