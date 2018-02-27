package cn.com.sharinglife.service.impl;

import cn.com.sharinglife.mapper.UserMapper;
import cn.com.sharinglife.pojo.User;
import cn.com.sharinglife.pojo.data.LoginData;
import cn.com.sharinglife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hell on 2018/1/31
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public void delete() {
        userMapper.delete();
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    @Cacheable(value = "userServiceCache")
    public User getUserById(Integer id) {
        if(id != null){
            System.out.println("数据不是从缓存中获取");
           return userMapper.getUserById(id);
        }
        return null;
    }

    @Override
    public User getUser(User user) {
        return userMapper.getUser(user);
    }

    @Override
    public User getUserByLoginData(LoginData loginData) {
        return userMapper.getUserByLoginData(loginData);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public boolean isExistPho(String phone) {
        return userMapper.isExistPho(phone);
    }
}
