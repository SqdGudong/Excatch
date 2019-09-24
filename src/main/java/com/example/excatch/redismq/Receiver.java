package com.example.excatch.redismq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @Author songqd
 * @Date 2019/9/6
 * @Description
 */
@Component
public class Receiver implements MessageListener {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    @Override
    public void onMessage(Message message, byte[] bytes) {
        RedisSerializer<String> valueSerializer = stringRedisTemplate.getStringSerializer();
        String deserialize = valueSerializer.deserialize(message.getBody());
        logger.info("收到的mq消息" + deserialize);
    }
}
