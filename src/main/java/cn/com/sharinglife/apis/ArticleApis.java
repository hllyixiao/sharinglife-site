package cn.com.sharinglife.apis;

/**
 * Created by hell on 2018/3/1
 *
 * @author hell
 */
public class ArticleApis extends ApiContains {

    private static final String PREFIX = API_PREFIX + "/article";

    public static final String ADD_ARTICLE = PREFIX + "/add";

    public static final String UPDATE_ARTICLE = PREFIX + "/update";

    public static final String ADD_ARTICLE_PICTURE = PREFIX + "/addpicture";

    public static final String GET_PUBLISH_ARTICLE_BY_ID = PREFIX + "/getpublishbyid";

    public static final String GET_ARTICLE_BY_ID = PREFIX + "/getbyid";

    public static final String LIST_ALL_ARTICLE_BY_USER_ID = PREFIX + "/listbyuserid";

    public static final String DELETE_ARTICLE_BY_IDS = PREFIX + "/deletebyids";

    public static final String RECOVERY_ARTICLE_BY_IDS = PREFIX + "/recoverybyids";

    public static final String THOROUGH_DELETE_ARTICLE_BY_IDS = PREFIX + "/thoroughdeletebyids";
}
