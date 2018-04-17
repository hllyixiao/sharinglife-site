package cn.com.sharinglife.mapper;

import cn.com.sharinglife.pojo.User;
import cn.com.sharinglife.pojo.requestdata.LoginRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hell on 2018/1/31
 *
 * @author hell
 */
@Mapper
public interface UserMapper {

    List<User> getAllUsers();

    void delete();

    Integer addUser(User user);

    User getUserById(@Param(value = "id") Integer id);

    User getUserByLoginData(LoginRequest loginRequest);

    void updateUser(User user);

    void updateAvatarUrl(@Param(value = "avatarUrl") String avatarUrl,
                         @Param(value = "id") Integer id);

    boolean isExistPho(String phone);

    boolean isExistName(String name);

    User getUserByUsername(String name);

    boolean isExistFollower(@Param(value = "userId") Integer userId,
                            @Param(value = "followerId") Integer followerId);

    void addMyFollower(@Param(value = "userId") Integer userId,
                       @Param(value = "followerId") Integer followerId);

    void deleteFollower(@Param(value = "userId") Integer userId,
                        @Param(value = "followerId") Integer followerId);

    /**
     * 关注我的用户（我的粉丝）
     *
     * @param userId
     * @return
     */
    List<User> followToMeUsers(Integer userId);

    /**
     * 我关注的用户
     *
     * @param userId
     * @return
     */
    List<User> myFollowUsers(Integer userId);

    /**
     * 我关注的用户数
     * @param userId
     * @return
     */
    Integer countMyFollowers(Integer userId);

    /**
     * 关注我的用户数
     * @param userId
     * @return
     */
    Integer countFollowToMe(Integer userId);
}
