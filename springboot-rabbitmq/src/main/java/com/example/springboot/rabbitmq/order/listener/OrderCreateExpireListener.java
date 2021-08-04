package com.example.springboot.rabbitmq.order.listener;

import com.example.springboot.rabbitmq.order.MQConstant;
import com.example.springboot.rabbitmq.order.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class OrderCreateExpireListener {

    @RabbitListener(queues = MQConstant.PAY_ORDER_CREATE_EXPIRE_QUEUE)
    public void process(Order message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        System.out.println("处理订单过期：" + time + " 收到消息," + message);
    }

}