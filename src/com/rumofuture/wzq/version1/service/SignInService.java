package com.rumofuture.wzq.version1.service;

import com.rumofuture.wzq.model.dao.UserDao;
import com.rumofuture.wzq.model.dao.UserSchema;
import com.rumofuture.wzq.model.domain.User;
import com.rumofuture.wzq.model.dao.impl.UserDaoImpl;
import com.rumofuture.wzq.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDateTime;

/**
 * Created by WangZhenqi on 2016/12/29.
 */
public class SignInService {
    private UserDao userDao = new UserDaoImpl();

    // 执行登录校验的逻辑
    public User signIn(User user) {
        Connection connection = null;
        try {
            // 通过连接工厂类获得一个数据库连接
            connection = ConnectionFactory.getInstance().getConnection();
            connection.setAutoCommit(false);  // 关闭自动提交
            // 根据传入的参数查询数据库中是否有对应的用户信息
            ResultSet resultSet = userDao.selectUserByMobilePhoneNumber(connection, user.getMobilePhoneNumber());
            if (resultSet.next()) {
                User resultUser = new User();
                resultUser.setId(resultSet.getInt(UserSchema.Table.Cols.ID));
                resultUser.setName(resultSet.getString(UserSchema.Table.Cols.NAME));
                resultUser.setMobilePhoneNumber(resultSet.getString(UserSchema.Table.Cols.MOBILE_PHONE_NUMBER));
                resultUser.setWorkExperience(resultSet.getInt(UserSchema.Table.Cols.WORK_EXPERIENCE));
                resultUser.setAnnualSalary(resultSet.getInt(UserSchema.Table.Cols.ANNUAL_SALARY));
                resultUser.setGraduatedFrom(resultSet.getString(UserSchema.Table.Cols.GRADUATED_FROM));
                resultUser.setEducation(resultSet.getString(UserSchema.Table.Cols.EDUCATION));
                resultUser.setTeamPosition(resultSet.getString(UserSchema.Table.Cols.TEAM_POSITION));
                resultUser.setPassword(resultSet.getString(UserSchema.Table.Cols.PASSWORD));
                resultUser.setCreateTime(LocalDateTime.now().withNano(0).toString());

                return resultUser;
            }
        } catch (Exception e) {
            e.printStackTrace();    // 异常处理
            try {
                if (null != connection) {
                    connection.rollback();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }

        return null;
    }
}
