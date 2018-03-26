package cn.com.sharinglife.controller.liferecord;

import cn.com.sharinglife.containapis.ArticleApis;
import cn.com.sharinglife.pojo.Article;
import cn.com.sharinglife.service.ArticleService;
import cn.com.sharinglife.util.CommonUtil;
import io.swagger.annotations.Api;
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
 */
@Api(value="文章controller",tags={"文章接口"})
@RestController
public class ArticleController {

    private final Logger LOG = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "点赞")
    @GetMapping(value = ArticleApis.LIKE + "/{articleId}")
    public boolean addlike(
            @PathVariable(value = "articleId") Integer articleId){
        LOG.info("addlike - 点赞");
        articleService.addlike(articleId);
        LOG.error("register方法 - 参数registerData不能有为null的属性");
        return false;
    }

    @ApiOperation(value = "添加文章", notes = "添加文章,返回文章id")
    @PostMapping(value = ArticleApis.ADD_ARTICLE)
    public Integer addArticle(@RequestBody final Article article ){
        LOG.info("addArticle - 添加文章");
        if(Objects.nonNull(article)){
            articleService.addArticle(article);
            return article.getId();
        }
        LOG.error("addArticle - 参数article不能有为null的属性");
        return null;
    }

    @ApiOperation(value = "添加文章图片", notes = "添加文章图片")
    @PostMapping(value = ArticleApis.ADD_ARTICLE_PICTURE)
    public Map addArticlePicture(HttpServletResponse response,HttpServletRequest request,
                              @RequestParam("userId") final Integer userId,
                              @RequestParam("articleId") final Integer articleId) {
        LOG.info("addArticlePicture — 添加文章图片");
        List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");
        if(!ListUtils.isEmpty(files)) {
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
            returnData.put("errno",0);
            returnData.put("data",urls);
            return returnData;
        }else{
            return null;
        }
    }

    @ApiOperation(value = "通过id获取文章", notes = "通过id获取文章")
    @GetMapping(value = ArticleApis.GET_ARTICLE_BY_ID + "/{articleId}")
    public Article getArticleById(@PathVariable final Integer articleId ){
        LOG.info("getArticleById - 通过id获取文章");
        if(Objects.nonNull(articleId)){
            return articleService.getArticleById(articleId);
        }
        LOG.error("getArticleById - 参数articleId不能有为null");
        return null;
    }
}
