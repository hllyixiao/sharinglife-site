package cn.com.sharinglife.service;

import cn.com.sharinglife.pojo.User;
import cn.com.sharinglife.pojo.data.LoginData;

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

    User getUserByLoginData(LoginData loginData);

    void updateUser(User user);

    boolean isExistPho(String phone);
}
