package cn.com.sharinglife.containapis;

/**
 * Created by hell on 2018/3/1
 */
public class ArticleApis extends ApiContains {

    private static final String PREFIX = API_PREFIX + "/article";

    public static final String LIKE = PREFIX + "/like";

    public static final String ADD_ARTICLE = PREFIX + "/addarticle";

    public static final String DELETE_ARTICLE = PREFIX + "/deletearticle";

    public static final String ADD_ARTICLE_PICTURE = PREFIX + "/addarticlepicture";

    public static final String GET_PUBLISH_ARTICLE_BY_ID = PREFIX + "/getpublishbyid";

    public static final String GET_ARTICLE_BY_ID = PREFIX + "/getbyid";

    public static final String GET_ALL_ARTICLE_BY_USER_ID = PREFIX + "/getbyuserid";

    public static final String DELETE_ARTICLE_BY_IDS = PREFIX + "/deletebyids";

    public static final String RECOVERY_ARTICLE_BY_IDS = PREFIX + "/recoverybyids";

    public static final String THOROUGH_DELETE_ARTICLE_BY_IDS = PREFIX + "/thoroughdeletebyids";
}
