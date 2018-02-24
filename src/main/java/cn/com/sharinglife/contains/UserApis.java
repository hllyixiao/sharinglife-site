package cn.com.sharinglife.contains;

/**
 * Created by hell on 2018/1/31
 */
public class UserApis extends ApiContains{

    private static final String PREFIX = API_PREFIX + "/user";

    public static final String GET_ALL_USERS_URL = PREFIX + "/list";

    public static final String GET_USERS_URL = PREFIX + "/get";

    public static final String UPDATE_USERS_URL = PREFIX + "/update";

    public static final String GET_USERS_BY_ID_URL = PREFIX + "/getbyid";
}
