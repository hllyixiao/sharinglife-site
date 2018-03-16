package cn.com.sharinglife.controller.liferecord;

import cn.com.sharinglife.containapis.ArticleApis;
import cn.com.sharinglife.controller.LoginRegisterController;
import cn.com.sharinglife.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
