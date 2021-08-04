package com.example.springboot.rabbitmq.order.listener;

import com.example.springboot.rabbitmq.order.MQConstant;
import com.example.springboot.rabbitmq.order.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class OrderCreateCouponListener {

    @RabbitListener(queues = MQConstant.PAY_ORDER_CREATE_COUPON_QUEUE)
    public void process(Order message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        System.out.println("处理订单创建----> 发送优惠券：" + time + " 收到消息," + message);
    }

}


