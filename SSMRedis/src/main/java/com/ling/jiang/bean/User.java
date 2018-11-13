package com.ling.jiang.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    10/20/2018
 * version: v1.0
 */
public class User implements Serializable {
    private int userId;
    private String username;
    @Pattern(regexp = "\\d+", message = "密码必须是数字")
    @Length(min = 6, message = "密码长度不得小于6位")
    private String password;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
