package cn.yuheng.server.dao;

import cn.yuheng.server.pojo.User;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByID(Integer id);

    User selectByEmail(String email);

    User selectByWeChatID(String wechatID);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}