package com.example.springboot.rabbitmq.topic.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 主题消费者
 * @Author zhangdongkang
 * @Date 2021/8/2
 */
@RabbitListener(queues = "topic.queue2")
@Component
public class TopicListener2 {

	@RabbitHandler
	public void process(String msg){
		System.out.println("TopicListener2 接收到了消息： " + msg);
	}
}
