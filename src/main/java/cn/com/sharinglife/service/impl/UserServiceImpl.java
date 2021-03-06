package cn.com.sharinglife.service.impl;

import cn.com.sharinglife.mapper.UserMapper;
import cn.com.sharinglife.pojo.User;
import cn.com.sharinglife.pojo.requestdata.LoginRequest;
import cn.com.sharinglife.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<User> getAllUsers(int page, int limit) {
        //mybatis分页插件
        PageHelper.startPage(page, limit);
        List<User> users = userMapper.getAllUsers();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
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
    public User getUserByLoginData(LoginRequest loginRequest) {
        return userMapper.getUserByLoginData(loginRequest);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void updateAvatarUrl(String avatarUrl,Integer id) {
        userMapper.updateAvatarUrl(avatarUrl,id);
    }

    @Override
    public boolean isExistPho(String phone) {
        return userMapper.isExistPho(phone);
    }

    @Override
    public boolean isExistName(String name) {
        return userMapper.isExistName(name);
    }

    @Override
    public boolean isExistFollower(Integer userId, Integer followerId) {
        return userMapper.isExistFollower(userId,followerId);
    }

    @Override
    public void addMyFollower(Integer userId, Integer followerId) {
        userMapper.addMyFollower(userId,followerId);
    }

    @Override
    public void deleteFollower(Integer userId, Integer followerId) {
        userMapper.deleteFollower(userId,followerId);
    }

    @Override
    public List<User> followToMeUsers(Integer id) {
        return userMapper.followToMeUsers(id);
    }

    @Override
    public List<User> myFollowUsers(Integer id) {
        return userMapper.myFollowUsers(id);
    }
}
