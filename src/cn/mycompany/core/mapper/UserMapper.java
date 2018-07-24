package cn.mycompany.core.mapper;

import cn.mycompany.core.pojo.User;

import java.util.List;

public interface UserMapper {
    /**
     * 查询所有用户
     */
    List<User> findAllUser();

    void insertUser(User user);
}
