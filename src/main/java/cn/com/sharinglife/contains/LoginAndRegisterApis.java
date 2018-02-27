package cn.com.sharinglife.contains;

/**
 * Created by hell on 2018/2/11
 */
public class LoginAndRegisterApis extends ApiContains{

    private static final String PREFIX = API_PREFIX + "/lr";

    public static final String VERIFY_CODE = PREFIX + "/getverifyCode";

    public static final String REGISTER = PREFIX + "/register";

    public static final String LOGIN = PREFIX + "/login";

    public static final String IS_EXITS_PHONE =  PREFIX + "/isexistpho";

    public static final String LOGOUT = PREFIX + "/logout";
}
