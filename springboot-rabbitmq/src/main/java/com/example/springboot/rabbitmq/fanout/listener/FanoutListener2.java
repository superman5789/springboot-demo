package com.example.springboot.rabbitmq.fanout.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 扇型队列消费者
 * @Author zhangdongkang
 * @Date 2021/8/2
 */
@RabbitListener(queues = "fanoutQueue2")
@Component
public class FanoutListener2 {

	@RabbitHandler
	public void process(String msg){
		System.out.println("FanoutListener222 接收到了消息： " + msg);
	}

}
