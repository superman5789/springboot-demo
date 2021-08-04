package com.example.springboot.rabbitmq.order;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("send")
    public String send(@RequestBody Order order){
        rabbitTemplate.convertAndSend(MQConstant.PAY_ORDER_CREATE_FANOUT_EXCHANGE, "", order);
        return "OK";
    }

}