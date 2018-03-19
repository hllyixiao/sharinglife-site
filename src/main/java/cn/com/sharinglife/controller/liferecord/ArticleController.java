package cn.com.sharinglife.controller.liferecord;

import cn.com.sharinglife.containapis.ArticleApis;
import cn.com.sharinglife.containapis.UserApis;
import cn.com.sharinglife.controller.LoginRegisterController;
import cn.com.sharinglife.pojo.Article;
import cn.com.sharinglife.pojo.User;
import cn.com.sharinglife.service.ArticleService;
import cn.com.sharinglife.util.CommonUtil;
import cn.com.sharinglife.util.SessionCookieUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hell on 2018/3/1
 */
@RestController
public class ArticleController {

    private final Logger LOG = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "点击喜欢文章")
    @GetMapping(value = ArticleApis.LIKE + "/{articleId}")
    public boolean addlike(
            @PathVariable(value = "articleId",required = true) Integer articleId){
        LOG.info("addlike - 点击喜欢文章");
        articleService.addlike(articleId);
        LOG.error("register方法 - 参数registerData不能有为null的属性");
       // response.setStatus(HttpStatus.SC_PRECONDITION_FAILED);
        return false;
    }

    @ApiOperation(value = "添加文章")
    @PostMapping(value = ArticleApis.ADD_ARTICLE)
    public Integer addArticle(@RequestBody final Article article ){
        LOG.info("addArticle - 添加文章");
        if(Objects.nonNull(article)){
            int id = articleService.addArticle(article);
            return id;
        }
        LOG.error("addArticle - 参数article不能有为null的属性");
        // response.setStatus(HttpStatus.SC_PRECONDITION_FAILED);
        return null;
    }

    @ApiOperation(value = "添加文章图片", notes = "添加文章图片")
    @PostMapping(value = ArticleApis.ADD_ARTICLE_PICTURE)
    public String addArticlePicture(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam("file") final MultipartFile file,
                              @RequestParam("userId") final Integer userId,
                              @RequestParam("articleId") final Integer articleId) {
        LOG.info("addArticlePicture — 添加文章图片");
        if(Objects.nonNull(file)){
            Map<String,String> res = CommonUtil.getUserFilePath(file, true, userId);
            //后缀名
            String suffixName = res.get("suffixName");
            //用户数据库保存路径
            String dataBasePath = res.get("userPath");
            //用户磁盘保存路径
            String diskPath = res.get("diskPath");
            //用户头像路径
            long timeLong = System.currentTimeMillis();
            String diskAvatarPath = diskPath + "article//" +  articleId + timeLong + suffixName;
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
                return databaseAvatarPath;
            } catch (IOException e) {
                LOG.error("setUserAvatar — 设置用户头像出错 —",e);
            }
        }else{
            LOG.error("setUserAvatar — user为null，请登陆！");
            response.setStatus(HttpStatus.SC_PRECONDITION_FAILED);
            return null;
        }
        return null;
    }
}
