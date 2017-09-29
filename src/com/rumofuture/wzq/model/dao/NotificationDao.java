package com.rumofuture.wzq.model.dao;

import com.rumofuture.wzq.model.domain.Notification;

import java.sql.Connection;
import java.sql.SQLException;

public interface NotificationDao {
    int saveNotification(Connection connection, Notification notification) throws SQLException;
    int deleteNotification(Connection connection, Integer id) throws SQLException;
    Notification getNotification(Connection connection, Integer notifierId, Integer targetId) throws SQLException;
    Notification getNotificationByTarget(Connection connection, Integer id) throws SQLException;
}
