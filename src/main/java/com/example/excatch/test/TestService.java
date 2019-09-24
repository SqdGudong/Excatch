package com.example.excatch.test;

import com.example.excatch.exception.GlobalExceptionHandler;
import com.example.excatch.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author
 * @Date 2019/8/12 13:34
 * @Version 1.0
 * Description:
 */
@Service
public class TestService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public Integer test1(){
        try {
            String[] i = {};
            String a = i[2];
        }catch (Exception e){
            throw e;
        }
        return 1;
    }

    public Integer test2(){
        throw new MyException("Test my Exception ");
    }

    public Integer test3(){
        int i = 1/0;
        return i;
    }

    public void test4() {
        try {
            String[] i = {};
            String a = i[2];
        }catch (Exception e){
            logger.error("catch中捕捉"+e.getMessage()+"异常");
           /* GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
            globalExceptionHandler.errorHandler(e);*/

        }
    }


    public void sendTest(){
        stringRedisTemplate.convertAndSend("MESSAGE","测试消息！");
    }
}
