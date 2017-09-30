package com.rumofuture.wang.service;

import com.rumofuture.wang.model.domain.Notification;

import java.util.List;

public interface NotificationService {
    int saveNotification(Notification notification);
    int deleteNotification(Integer id);
    List<Notification> getNotificationList(Integer notifierId, Integer targetId);
    List<Notification> getNotificationList(Integer notifierId, Integer targetId, Integer type);
    List<Notification> getNotificationByTarget(Integer id);
}
