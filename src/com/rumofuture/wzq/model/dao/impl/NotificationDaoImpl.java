package com.rumofuture.wzq.model.dao.impl;

import com.rumofuture.wzq.model.dao.NotificationDao;
import com.rumofuture.wzq.model.domain.Notification;
import com.rumofuture.wzq.model.schema.NotificationSchema;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("notificationDao")
public class NotificationDaoImpl implements NotificationDao {


    @Override
    public int saveNotification(Connection connection, Notification notification)  throws SQLException {
        String insertSql = "INSERT INTO " + NotificationSchema.Table.NAME + "(" +
                NotificationSchema.Table.Cols.NOTIFIER_ID + ", " +
                NotificationSchema.Table.Cols.TARGET_ID + ", " +
                NotificationSchema.Table.Cols.CONTENT + ", " +
                NotificationSchema.Table.Cols.IS_CHECKED + ", " +
                NotificationSchema.Table.Cols.TYPE + ") " +
                "VALUES(?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS);

        statement.setInt(1, notification.getNotifier().getId());
        statement.setInt(2, notification.getTarget().getId());
        statement.setString(3, notification.getContent());
        statement.setBoolean(4, notification.getChecked());
        statement.setInt(5, notification.getType());

        statement.executeUpdate();

        ResultSet resultSet = statement.getGeneratedKeys();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }

        return 0;
    }

    @Override
    public int deleteNotification(Connection connection, Integer id) throws SQLException {
        String deleteSql = "DELETE FROM " + NotificationSchema.Table.NAME + " WHERE " +
                NotificationSchema.Table.Cols.ID + " = ?";
        PreparedStatement statement = connection.prepareStatement(deleteSql);
        statement.setInt(1, id);
        return statement.executeUpdate();
    }

    @Override
    public Notification getNotification(Connection connection, Integer notifierId, Integer targetId) throws SQLException {
        String selectSql = "SELECT * FROM WHERE " + NotificationSchema.Table.NAME + " WHERE " +
                NotificationSchema.Table.Cols.NOTIFIER_ID + " = ? AND" +
                NotificationSchema.Table.Cols.TARGET_ID + " = ?";
        PreparedStatement statement = connection.prepareStatement(selectSql);
        return null;
    }

    @Override
    public Notification getNotificationByTarget(Connection connection, Integer id) throws SQLException {
        return null;
    }
}
