package com.kk.sso.query.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * @ClassName User
 * @Author Administrator
 * @Param
 * @Return
 * @Date 2018/5/7 21:38
 */
public class User implements java.io.Serializable {

    private static final long serialVersionUID = -5332775332484044922L;

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private String phone;

    private String email;

    private Date created;

    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
