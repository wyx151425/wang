package com.rumofuture.wzq.version1.service;

import com.rumofuture.wzq.model.dao.MemberDao;
import com.rumofuture.wzq.model.domain.Member;
import com.rumofuture.wzq.model.dao.impl.MemberDaoImpl;
import com.rumofuture.wzq.version1.util.ConnectionFactory;

import java.sql.Connection;

/**
 * Created by WangZhenqi on 2016/12/30.
 */
public class MemberRemoveService {
    private MemberDao memberDao = new MemberDaoImpl();

    // 执行登录校验的逻辑
    public int memberRemove(Member member) {
        Connection connection = null;
        try {
            // 通过连接工厂类获得一个数据库连接
            connection = ConnectionFactory.getInstance().getConnection();
            // 根据传入的参数查询数据库中是否有对应的用户信息
            int i = memberDao.deleteMember(connection, member.getId());
            System.out.println(i);
            return i;
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
