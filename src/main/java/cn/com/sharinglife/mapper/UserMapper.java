package cn.com.sharinglife.mapper;

import cn.com.sharinglife.pojo.User;
import cn.com.sharinglife.pojo.data.LoginData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hell on 2018/1/31
 */
@Mapper
public interface UserMapper {

    List<User> getAllUsers();

    void delete();

    Integer addUser(User user);

    User getUserById(@Param(value = "id") Integer id);

    User getUser(User user);

    User getUserByLoginData(LoginData loginData);

    void updateUser(User user);

    boolean isExistPho(String phone);
}
