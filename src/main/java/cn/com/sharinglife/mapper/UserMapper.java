package cn.com.sharinglife.mapper;

import cn.com.sharinglife.pojo.User;
import cn.com.sharinglife.pojo.data.LoginData;
import io.swagger.models.auth.In;
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

    User getUserByLoginData(LoginData loginData);

    void updateUser(User user);

    void updateAvatarUrl(@Param(value = "avatarUrl") String avatarUrl,
                         @Param(value = "id") Integer id);

    boolean isExistPho(String phone);

    boolean isExistName(String name);

    boolean isExistFollower(@Param(value = "userId")Integer userId,
                            @Param(value = "followerId")Integer followerId);

    void addMyFollower(@Param(value = "userId")Integer userId,
                       @Param(value = "followerId")Integer followerId);

    void deleteFollower(@Param(value = "userId")Integer userId,
                       @Param(value = "followerId")Integer followerId);

    //关注我的用户（我的粉丝）
    List<User> getMyFollowerUser(Integer id);

    //我关注的用户
    List<User> getFollowerMyUser(Integer id);
}
