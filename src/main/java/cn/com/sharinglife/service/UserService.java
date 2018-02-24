package cn.com.sharinglife.service;

import cn.com.sharinglife.pojo.User;

import java.util.List;

/**
 * Created by hell on 2018/1/31
 * change by chup
 */
public interface UserService {

    List<User> getAllUsers();

    void delete();

    Integer addUser(User user);

    User getUserById(Integer id);

    User getUser(User user);

    void updateUser(User user);

    boolean isExistPho(String phone);
}
