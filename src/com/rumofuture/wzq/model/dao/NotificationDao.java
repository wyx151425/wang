package com.rumofuture.wzq.model.dao;

import com.rumofuture.wzq.model.domain.Notification;

public interface NotificationDao {
    int saveNotification(Notification notification);
    int deleteNotification(Integer id);
    Notification getNotification(Integer inviterId, Integer inviteeId);
    Notification getNotificationByInvitee(Integer id);
}
