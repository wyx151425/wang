package com.rumofuture.wang.util;

import com.rumofuture.wang.model.domain.Notification;
import com.rumofuture.wang.model.domain.User;
import com.rumofuture.wang.model.schema.NotificationSchema;
import com.rumofuture.wang.service.NotificationService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectMapper {

    public static Notification getNotification(ResultSet resultSet) throws SQLException {
        Notification notification = new Notification();
        notification.setId(resultSet.getInt(NotificationSchema.Table.Cols.ID));
        int notifierId = resultSet.getInt(NotificationSchema.Table.Cols.NOTIFIER_ID);
        User notifier = new User();
        notifier.setId(notifierId);
        notification.setNotifier(notifier);
        int targetId = resultSet.getInt(NotificationSchema.Table.Cols.TARGET_ID);
        User target = new User();
        target.setId(targetId);
        notification.setTarget(target);
        notification.setContent(resultSet.getString(NotificationSchema.Table.Cols.CONTENT));
        notification.setChecked(resultSet.getBoolean(NotificationSchema.Table.Cols.IS_CHECKED));
        notification.setType(resultSet.getInt(NotificationSchema.Table.Cols.TYPE));
        notification.setCreateTime(String.valueOf(resultSet.getTimestamp(NotificationSchema.Table.Cols.CREATE_TIME)));
        return notification;
    }
}
