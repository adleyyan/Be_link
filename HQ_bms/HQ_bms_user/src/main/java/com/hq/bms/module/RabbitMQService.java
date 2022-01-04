package com.hq.bms.module;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMQService {

    @RabbitListener(queues = "")
    public Object ExceptionMsg(String exceptionMsg){
        log.info("rabbitmq全局异常消息：\t"+exceptionMsg);
        return exceptionMsg;
    }
}
