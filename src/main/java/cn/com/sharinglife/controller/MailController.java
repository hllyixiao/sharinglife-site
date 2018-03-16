package cn.com.sharinglife.controller;

import cn.com.sharinglife.containapis.MailApis;
import cn.com.sharinglife.service.MailService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hell
 * @data 2018/3/8 21:31
 */
@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    private final Logger LOG = LoggerFactory.getLogger(MailController.class);

    @ApiOperation(value = "发送邮件" ,notes = "通过发邮件可以修改密码")
    @GetMapping(value = MailApis.SEND_MAIL)
    public boolean sendSimpleMail(){
        LOG.info("sendSimpleMail - 发送邮件");
        String to = "1272474387@qq.com";
        String subject = "贺淋亮的邮件";
        String content = "我是大帅哥，www.baidu.com";
        mailService.sendSimpleMail(to,subject,content);
        return true;
    }
}
