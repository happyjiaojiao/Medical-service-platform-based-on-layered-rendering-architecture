package cn.yuheng.server.dao;

import cn.yuheng.server.pojo.LoginHistory;

import java.util.List;

public interface LoginHistoryDao {
    int insert(LoginHistory record);

    int insertSelective(LoginHistory record);

    List<LoginHistory> getByUser(Integer user);
}