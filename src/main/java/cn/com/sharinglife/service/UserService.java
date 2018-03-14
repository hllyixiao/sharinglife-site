package cn.com.sharinglife.service;

import cn.com.sharinglife.pojo.User;
import cn.com.sharinglife.pojo.requestdata.LoginRequest;

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

    User getUserByLoginData(LoginRequest loginRequest);

    void updateUser(User user);

    void updateAvatarUrl(String avatarUrl,Integer id);

    boolean isExistPho(String phone);

    boolean isExistName(String name);

    boolean isExistFollower(Integer userId, Integer followerId);

    void addMyFollower(Integer userId, Integer followerId);

    void deleteFollower(Integer userId, Integer followerId);

    /**
     * 获取关注我的用户（我的粉丝）
     * @param id
     * @return
     */
    List<User> followToMeUsers(Integer id);

    /**
     * 获取我关注的所有用户
     * @param id
     * @return
     */
    List<User> myFollowUsers(Integer id);
}
