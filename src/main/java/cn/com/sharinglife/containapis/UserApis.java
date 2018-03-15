package cn.com.sharinglife.containapis;

/**
 * Created by hell on 2018/1/31
 */
public class UserApis extends ApiContains{

    private static final String PREFIX = API_PREFIX + "/user";

    public static final String GET_USERS = PREFIX + "/list";

    public static final String UPDATE_USER = PREFIX + "/update";

    public static final String GET_USERS_BY_ID = PREFIX + "/getbyid";

    public static final String SET_USERS_AVATAR = PREFIX + "/setavatar";

    public static final String ADD_USERS_FOLLOWER = PREFIX + "/addfollower";

    public static final String DELETE_USERS_FOLLOWER = PREFIX + "/deletefollower";

    public static final String GET_FOLLOW_TO_ME_USERS = PREFIX + "/followtome";

    public static final String GET_MY_FOLLOW_USERS = PREFIX + "/myfollow";

    public static final String MODIFY_PASSWORD_USERS = PREFIX + "/modifypassword";
}
