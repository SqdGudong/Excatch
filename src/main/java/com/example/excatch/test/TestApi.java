package com.example.excatch.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author
 * @Date 2019/8/12 13:34
 * @Version 1.0
 * Description:
 */

@RestController
@RequestMapping("/api")
public class TestApi {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    TestService testService;

/*
    @Value("${spring.mail.receiveList}")
    private List<String> receiveList;
*/

    @RequestMapping("/test1")
    public String test1() {
        testService.test1();
        return "1";
    }

    @RequestMapping("/test2")
    public String test2() {
        testService.test2();
        return "1";
    }

    @RequestMapping("/test3")
    public String test3() {
        testService.test3();
        return "1";
    }

    @RequestMapping("/test4")
    public String test4() {
        testService.test4();
        return "1";
    }
    @RequestMapping("/test5")
    public String testSend() {
        testService.sendTest();
        return "1";
    }

}
