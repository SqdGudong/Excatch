package com.example.excatch.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author songqd
 * @Date 2019/9/3
 * @Description 邮件工具类
 */
@Component
public class MailUtil {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    AlarmHelperBean alarmHelperBean;

    /**
     * @description 根据配置信息群发普通邮件
     * @parm [exceptionMailBean]
     * @author songqd
     * @date 2019/9/5
     * @reutun void
     * @modifier
     */
    public void sendGroupMail(ExceptionMailBean exceptionMailBean) {
        for (String receiveName : alarmHelperBean.getReceiveList()
        ) {
            sendMail(exceptionMailBean, receiveName);
        }
    }

    /**
     * @description 发送普通邮件
     * @parm [exceptionMailBean, receiveName]
     * @author songqd
     * @date 2019/9/5
     * @reutun void
     * @modifier
     */
    public void sendMail(ExceptionMailBean exceptionMailBean, String receiveName) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setFrom(alarmHelperBean.getUsername());
            helper.setTo(receiveName);
            helper.setSubject(exceptionMailBean.getExceptionClassName());
            helper.setText(exceptionMailBean.getExceptionDetail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }

    /**
     * @description 发送html邮件
     * @parm [exceptionMailBean]
     * @author songqd
     * @date 2019/9/5
     * @reutun void
     * @modifier
     */
    public void sendHtmlMail(ExceptionMailBean exceptionMailBean) {
        String content = "<html>\n" +
                "<body>\n" +
                "    <h3>" + exceptionMailBean.getExceptionClassName() + "</h3>\n" +
                "    <h4>" + exceptionMailBean.getExceptionClassName() + "</h4>" +
                "</body>\n" +
                "</html>";
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(alarmHelperBean.getUsername());
            helper.setTo(alarmHelperBean.getReceiveList().get(0));
            helper.setSubject("html mail");
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @description 向所有收件人同步发送邮件
     * @parm [exceptionMailBean]
     * @author songqd
     * @date 2019/9/6
     * @reutun void
     * @modifier
     */
    public void sendByThread(ExceptionMailBean exceptionMailBean) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (String receiver : alarmHelperBean.getReceiveList()
        ) {
            executorService.execute(() -> {
                sendMail(exceptionMailBean,receiver);
            });
        }

    }


}
