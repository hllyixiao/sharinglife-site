package cn.com.sharinglife.controller.liferecord;

import cn.com.sharinglife.anno.LoginAnnotation;
import cn.com.sharinglife.apis.ArticleApis;
import cn.com.sharinglife.pojo.Article;
import cn.com.sharinglife.pojo.responsedata.PageResponse;
import cn.com.sharinglife.pojo.vo.ArticleVo;
import cn.com.sharinglife.service.ArticleService;
import cn.com.sharinglife.util.CommonUtil;
import cn.com.sharinglife.util.SessionCookieUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.thymeleaf.util.ListUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by hell on 2018/3/1
 *
 * @author hell
 */
@RestController
public class ArticleController {

    private final Logger LOG = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @LoginAnnotation
    @ApiOperation(value = "添加文章", notes = "添加文章,返回文章id")
    @PostMapping(value = ArticleApis.ADD_ARTICLE)
    public Integer addArticle(HttpServletRequest request,
                              @RequestBody final Article article) {
        LOG.info("addArticle - 添加文章");
        if (Objects.nonNull(article)) {
            article.setUserId(SessionCookieUtil.getCurrentUserIdBySession(request));
            articleService.addArticle(article);
            return article.getId();
        }
        LOG.error("addArticle - 参数article不能有为null的属性");
        return null;
    }

    @LoginAnnotation
    @ApiOperation(value = "更新文章", notes = "添加文章,返回文章id")
    @PostMapping(value = ArticleApis.UPDATE_ARTICLE)
    public Integer updateArticle(HttpServletRequest request,
                              @RequestBody final Article article) {
        LOG.info("updateArticle - 更新文章");
        if (Objects.nonNull(article) && Objects.nonNull(article.getId())) {
            return articleService.updateArticle(article);
        }
        LOG.error("updateArticle - 参数article不能有为null的属性");
        return null;
    }

    @LoginAnnotation
    @ApiOperation(value = "添加文章图片", notes = "添加文章图片")
    @PostMapping(value = ArticleApis.ADD_ARTICLE_PICTURE)
    public Map addArticlePicture(HttpServletRequest request,
                                 @RequestParam("articleId") final Integer articleId) {
        Integer userId = SessionCookieUtil.getCurrentUserIdBySession(request);
        LOG.info("addArticlePicture — 添加文章图片");
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        if (!ListUtils.isEmpty(files)) {
            Map returnData = new HashMap(2);
            List urls = new ArrayList(files.size());
            files.forEach(file -> {
                Map<String, String> res = CommonUtil.getUserFilePath(file, true, userId);
                //后缀名
                String suffixName = res.get("suffixName");
                //用户数据库保存路径
                String dataBasePath = res.get("userPath");
                //用户磁盘保存路径
                String diskPath = res.get("diskPath");
                //用户头像路径
                long timeLong = System.currentTimeMillis();
                String diskAvatarPath = diskPath + "article//" + articleId + timeLong + suffixName;
                LOG.info("用户图像全路径为：" + diskAvatarPath);
                //用户数据库头像路径
                String databaseAvatarPath = dataBasePath + "article//" + articleId + timeLong + suffixName;
                File dest = new File(diskAvatarPath);
                // 检测是否存在目录
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                try {
                    file.transferTo(dest);
                    urls.add(databaseAvatarPath);
                } catch (IOException e) {
                    LOG.error("addArticlePicture — 添加文章图片 —", e);
                }
            });
            returnData.put("errno", 0);
            returnData.put("data", urls);
            return returnData;
        } else {
            return null;
        }
    }

    @ApiOperation(value = "通过id获取已发布的文章", notes = "通过id获取已发布的文章")
    @GetMapping(value = ArticleApis.GET_PUBLISH_ARTICLE_BY_ID + "/{articleId}")
    public Article getPublishArticleById(@PathVariable final Integer articleId) {
        LOG.info("getPublishArticleById - 通过id获取已发布的文章");
        if (Objects.nonNull(articleId)) {
            return articleService.getArticleById(articleId, 2);
        }
        LOG.error("getPublishArticleById - 参数articleId不能有为null");
        return null;
    }

    @LoginAnnotation
    @ApiOperation(value = "通过id获取文章", notes = "通过id获取文章")
    @GetMapping(value = ArticleApis.GET_ARTICLE_BY_ID + "/{articleId}")
    public Article getArticleById(@PathVariable final Integer articleId) {
        LOG.info("getArticleById - 通过id获取文章");
        if (Objects.nonNull(articleId)) {
            return articleService.getArticleById(articleId, null);
        }
        LOG.error("getArticleById - 参数articleId不能有为null");
        return null;
    }

    @LoginAnnotation
    @ApiOperation(value = "获取当前用户的文章列表", notes = "获取当前用户的文章列表")
    @GetMapping(value = ArticleApis.LIST_ALL_ARTICLE_BY_USER_ID)
    public PageResponse getAllArticlesByUserId(HttpServletRequest request,
                                               HttpServletResponse response,
                                               @RequestParam("status") final Integer status,
                                               @RequestParam(value = "page", defaultValue = "1") final int page,
                                               @RequestParam(value = "limit", defaultValue = "5") final int limit) {
        LOG.info("getAllArticlesByUserId —— 获取文章列表，当前页:第{}页，每页获取{}个文章信息", page, limit);
        response.addHeader("Access-Control-Allow-Origin", "*");
        Integer userId = SessionCookieUtil.getCurrentUserIdBySession(request);
        PageInfo<ArticleVo> articlePageInfo = articleService.getArticlesByUserId(userId, status, page, limit);
        List<ArticleVo> articleResponses = articlePageInfo.getList();
        PageResponse<ArticleVo> pageResponse = new PageResponse<>(articlePageInfo);
        pageResponse.setDatas(articleResponses);
        return pageResponse;
    }

    @LoginAnnotation
    @ApiOperation(value = "删除文章", notes = "删除文章")
    @PostMapping(value = ArticleApis.DELETE_ARTICLE_BY_IDS)
    public boolean deleteArticleByIds(@RequestBody List<Integer> articleIds) {
        LOG.info("deleteArticleByIds —— 删除文章，文章ids={}", articleIds.toString());
        if (!ListUtils.isEmpty(articleIds)) {
            articleService.deleteArticleByIds(articleIds, 0);
            return true;
        }
        LOG.info("deleteArticleByIds —— 删除文章失败，articleIds不符合要求");
        return false;
    }

    @ApiOperation(value = "恢复已删除的文章", notes = "恢复已删除的文章")
    @PostMapping(value = ArticleApis.RECOVERY_ARTICLE_BY_IDS)
    public boolean recoveryArticleByIds(@RequestParam("articleIds") final List<Integer> articleIds) {
        LOG.info("recoveryArticleById —— 恢复已删除的文章，文章ids={}", articleIds.toString());
        if (!ListUtils.isEmpty(articleIds)) {
            articleService.deleteArticleByIds(articleIds, 1);
            return true;
        }
        LOG.info("recoveryArticleById —— 恢复已删除的文章，articleIds不符合要求");
        return false;
    }

    @LoginAnnotation
    @ApiOperation(value = "彻底删除文章", notes = "彻底删除文章")
    @PostMapping(value = ArticleApis.THOROUGH_DELETE_ARTICLE_BY_IDS)
    public boolean thoroughDeleteArticleByIds(@RequestBody final List<Integer> articleIds) {
        LOG.info("thoroughDeleteArticleByIds —— 彻底删除文章，文章ids={}", articleIds.toString());
        if (!ListUtils.isEmpty(articleIds)) {
            articleService.thoroughDeleteArticleByIds(articleIds);
            return true;
        }
        LOG.info("thoroughDeleteArticleByIds —— 彻底删除文章，articleIds不符合要求");
        return false;
    }
}
