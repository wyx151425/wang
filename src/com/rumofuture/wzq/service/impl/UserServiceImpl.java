package com.rumofuture.wzq.service.impl;

import com.rumofuture.wzq.model.dao.UserDao;
import com.rumofuture.wzq.model.dao.UserSchema;
import com.rumofuture.wzq.model.domain.User;
import com.rumofuture.wzq.service.UserService;
import com.rumofuture.wzq.util.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDateTime;

/**
 * Created by WangZhenqi on 2017/09/27.
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User signUp(User user) {
        Connection connection = null;
        try {
            // 通过连接工厂类获得一个数据库连接
            connection = ConnectionFactory.getInstance().getConnection();
            connection.setAutoCommit(false);
            // 不要关闭自动提交
            // 根据传入的参数查询数据库中是否有对应的用户信息
            int id = userDao.insertUser(connection, user);
            connection.commit();
            if (0 != id) {
                user.setId(id);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();  // 异常处理
            try {
                if (null != connection) {
                    connection.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public User logIn(String mobilePhoneNumber) {
        Connection connection = null;
        try {
            // 通过连接工厂类获得一个数据库连接
            connection = ConnectionFactory.getInstance().getConnection();
            connection.setAutoCommit(false);  // 关闭自动提交
            // 根据传入的参数查询数据库中是否有对应的用户信息
            ResultSet resultSet = userDao.selectUserByMobilePhoneNumber(connection, mobilePhoneNumber);
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(UserSchema.Table.Cols.ID));
                user.setName(resultSet.getString(UserSchema.Table.Cols.NAME));
                user.setMobilePhoneNumber(resultSet.getString(UserSchema.Table.Cols.MOBILE_PHONE_NUMBER));
                user.setPassword(resultSet.getString(UserSchema.Table.Cols.PASSWORD));
                user.setWorkExperience(resultSet.getInt(UserSchema.Table.Cols.WORK_EXPERIENCE));
                user.setAnnualSalary(resultSet.getInt(UserSchema.Table.Cols.ANNUAL_SALARY));
                user.setGraduatedFrom(resultSet.getString(UserSchema.Table.Cols.GRADUATED_FROM));
                user.setEducation(resultSet.getString(UserSchema.Table.Cols.EDUCATION));
                user.setTeamPosition(resultSet.getString(UserSchema.Table.Cols.TEAM_POSITION));
                user.setCreateTime(resultSet.getTimestamp(UserSchema.Table.Cols.CREATE_TIME).toString());
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();    // 异常处理
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public User updateUserPassword(User user) {
        Connection connection = null;
        try {
            // 通过连接工厂类获得一个数据库连接
            connection = ConnectionFactory.getInstance().getConnection();
            connection.setAutoCommit(false);
            // 根据传入的参数查询数据库中是否有对应的用户信息
            int row = userDao.updateUserPassword(connection, user);
            connection.commit();
            if (1 == row) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();    // 异常处理
            try {
                if (null != connection) {
                    connection.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public User updateUserInfo(User user) {
        Connection connection = null;
        try {
            // 通过连接工厂类获得一个数据库连接
            connection = ConnectionFactory.getInstance().getConnection();
            connection.setAutoCommit(false);
            // 根据传入的参数查询数据库中是否有对应的用户信息
            int row = userDao.updateUserInfo(connection, user);
            connection.commit();
            if (1 == row) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();  // 异常处理
            try {
                if (null != connection) {
                    connection.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public User getUserById(Integer id) {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            connection.setAutoCommit(false);
            ResultSet resultSet = userDao.selectUserById(connection, id);
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt(UserSchema.Table.Cols.ID));
                user.setName(resultSet.getString(UserSchema.Table.Cols.NAME));
                user.setMobilePhoneNumber(resultSet.getString(UserSchema.Table.Cols.MOBILE_PHONE_NUMBER));
                user.setPassword(resultSet.getString(UserSchema.Table.Cols.PASSWORD));
                user.setWorkExperience(resultSet.getInt(UserSchema.Table.Cols.WORK_EXPERIENCE));
                user.setAnnualSalary(resultSet.getInt(UserSchema.Table.Cols.ANNUAL_SALARY));
                user.setGraduatedFrom(resultSet.getString(UserSchema.Table.Cols.GRADUATED_FROM));
                user.setEducation(resultSet.getString(UserSchema.Table.Cols.EDUCATION));
                user.setTeamPosition(resultSet.getString(UserSchema.Table.Cols.TEAM_POSITION));
                user.setCreateTime(resultSet.getTimestamp(UserSchema.Table.Cols.CREATE_TIME).toString());
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public User getUserByMobilePhoneNumber(String mobilePhoneNumber) {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getInstance().getConnection();
            connection.setAutoCommit(false);
            ResultSet resultSet = userDao.selectUserByMobilePhoneNumber(connection, mobilePhoneNumber);
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(UserSchema.Table.Cols.ID));
                user.setName(resultSet.getString(UserSchema.Table.Cols.NAME));
                user.setMobilePhoneNumber(resultSet.getString(UserSchema.Table.Cols.MOBILE_PHONE_NUMBER));
                user.setPassword(resultSet.getString(UserSchema.Table.Cols.PASSWORD));
                user.setWorkExperience(resultSet.getInt(UserSchema.Table.Cols.WORK_EXPERIENCE));
                user.setAnnualSalary(resultSet.getInt(UserSchema.Table.Cols.ANNUAL_SALARY));
                user.setGraduatedFrom(resultSet.getString(UserSchema.Table.Cols.GRADUATED_FROM));
                user.setEducation(resultSet.getString(UserSchema.Table.Cols.EDUCATION));
                user.setTeamPosition(resultSet.getString(UserSchema.Table.Cols.TEAM_POSITION));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
