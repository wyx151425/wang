package com.rumofuture.wzq.version1.service;

import com.rumofuture.wzq.model.dao.UserDao;
import com.rumofuture.wzq.model.domain.User;
import com.rumofuture.wzq.model.dao.impl.UserDaoImpl;
import com.rumofuture.wzq.version1.util.ConnectionFactory;

import java.sql.Connection;

/**
 * Created by WangZhenqi on 2016/12/29.
 */
public class AccountInfoModifyService {
    private UserDao userDao = new UserDaoImpl();

    // 执行登录校验的逻辑
    public int infoModify(User user) {
        Connection connection = null;
        try {
            // 通过连接工厂类获得一个数据库连接
            connection = ConnectionFactory.getInstance().getConnection();
            // 根据传入的参数查询数据库中是否有对应的用户信息
            return userDao.updateUserInfo(connection, user);
        } catch (Exception e) {
            e.printStackTrace();  // 异常处理
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

        return 0;
    }
}
