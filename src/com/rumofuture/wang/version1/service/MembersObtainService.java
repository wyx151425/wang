package com.rumofuture.wang.version1.service;

import com.rumofuture.wang.model.dao.MemberDao;
import com.rumofuture.wang.model.schema.MemberSchema;
import com.rumofuture.wang.model.domain.Member;
import com.rumofuture.wang.model.dao.impl.MemberDaoImpl;
import com.rumofuture.wang.version1.util.ConnectionFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by WangZhenqi on 2016/12/29.
 */
public class MembersObtainService {
    private MemberDao memberDao = new MemberDaoImpl();
    // 执行登录校验的逻辑
    public JSONArray membersObtain(Member member) {
        Connection connection = null;
        try {
            // 通过连接工厂类获得一个数据库连接
            connection = ConnectionFactory.getInstance().getConnection();
            connection.setAutoCommit(false);  // 关闭自动提交
            // 根据传入的参数查询数据库中是否有对应的用户信息
            ResultSet resultSet = memberDao.selectMemberListByLeader(connection, member.getLeader().getId());
            JSONArray jsonArray = new JSONArray();
            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(MemberSchema.Table.Cols.ID,
                        resultSet.getLong(MemberSchema.Table.Cols.ID));
                jsonObject.put(MemberSchema.Table.Cols.NAME,
                        resultSet.getString(MemberSchema.Table.Cols.NAME));
                jsonObject.put(MemberSchema.Table.Cols.MOBILE_PHONE_NUMBER,
                        resultSet.getString(MemberSchema.Table.Cols.MOBILE_PHONE_NUMBER));
                jsonObject.put(MemberSchema.Table.Cols.LEADER_ID,
                        resultSet.getLong(MemberSchema.Table.Cols.LEADER_ID));
                jsonObject.put(MemberSchema.Table.Cols.WORK_EXPERIENCE,
                        resultSet.getInt(MemberSchema.Table.Cols.WORK_EXPERIENCE));
                jsonObject.put(MemberSchema.Table.Cols.ANNUAL_SALARY,
                        resultSet.getInt(MemberSchema.Table.Cols.ANNUAL_SALARY));
                jsonObject.put(MemberSchema.Table.Cols.GRADUATED_FROM,
                        resultSet.getString(MemberSchema.Table.Cols.GRADUATED_FROM));
                jsonObject.put(MemberSchema.Table.Cols.HIGHEST_EDUCATION,
                        resultSet.getString(MemberSchema.Table.Cols.HIGHEST_EDUCATION));
                jsonObject.put(MemberSchema.Table.Cols.TEAM_POSITION,
                        resultSet.getString(MemberSchema.Table.Cols.TEAM_POSITION));
                jsonObject.put(MemberSchema.Table.Cols.CREATE_TIME,
                        resultSet.getTimestamp(MemberSchema.Table.Cols.CREATE_TIME).toString());
                jsonArray.put(jsonObject);
            }
            return jsonArray;
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

        return null;
    }
}
