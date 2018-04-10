package cn.com.sharinglife.containapis;

/**
 * Created by hell on 2018/1/31
 * @author hell
 */
public class UserApis extends ApiContains{

    private static final String PREFIX = API_PREFIX + "/user";

    public static final String LIST_USERS = PREFIX + "/list";

    public static final String UPDATE_USER = PREFIX + "/update";

    public static final String GET_USER_BY_ID = PREFIX + "/getbyid";

    public static final String SET_USER_AVATAR = PREFIX + "/setavatar";

    public static final String ADD_USER_FOLLOWER = PREFIX + "/addfollower";

    public static final String DELETE_USER_FOLLOWER = PREFIX + "/deletefollower";

    public static final String GET_FOLLOW_TO_ME_USER = PREFIX + "/followtome";

    public static final String GET_MY_FOLLOW_USER = PREFIX + "/myfollow";

    public static final String MODIFY_PASSWORD_USER = PREFIX + "/modifypassword";
}
