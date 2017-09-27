package com.rumofuture.wzq.model.dto;

import com.rumofuture.wzq.model.domain.User;

import java.io.Serializable;

/**
 * Created by WangZhenqi on 2017/09/27.
 */

public class ResponseUser implements Serializable {

    private static final int SUCCESS = 1;
    private static final int ERROR = 0;

    private int status;
    private String message;
    private User user;

    public ResponseUser(boolean isSuccess, String message, User user) {
        this.status = isSuccess ? SUCCESS : ERROR;
        this.message = message;
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
