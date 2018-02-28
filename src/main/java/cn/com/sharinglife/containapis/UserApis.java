package cn.com.sharinglife.containapis;

/**
 * Created by hell on 2018/1/31
 */
public class UserApis extends ApiContains{

    private static final String PREFIX = API_PREFIX + "/user";

    public static final String GET_ALL_USERS_URL = PREFIX + "/list";

    public static final String GET_USERS_URL = PREFIX + "/get";

    public static final String UPDATE_USERS_URL = PREFIX + "/update";

    public static final String GET_USERS_BY_ID_URL = PREFIX + "/getbyid";

    public static final String SET_USERS_AVATAR = PREFIX + "/setavatar";

    public static final String ADD_USERS_FOLLOWER = PREFIX + "/addfollower";

    public static final String DELETE_USERS_FOLLOWER = PREFIX + "/deletefollower";

    public static final String GET_USERS_FOLLOWER = PREFIX + "/getfollower";
}
