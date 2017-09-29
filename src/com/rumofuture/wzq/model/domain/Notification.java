package com.rumofuture.wzq.model.domain;

import java.io.Serializable;

/**
 * Created by WangZhenqi on 2017/09/29.
 */

public class Notification implements Serializable {

    private Integer id;
    private User inviter;
    private User invitee;
    private String content;
    private Boolean isChecked;
    private Integer type;
    private String createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getInviter() {
        return inviter;
    }

    public void setInviter(User inviter) {
        this.inviter = inviter;
    }

    public User getInvitee() {
        return invitee;
    }

    public void setInvitee(User invitee) {
        this.invitee = invitee;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", inviter=" + inviter +
                ", invitee=" + invitee +
                ", content='" + content + '\'' +
                ", isChecked=" + isChecked +
                ", type=" + type +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
