package com.example.springboot.rabbitmq.direct.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 直连
 * @Author zhangdongkang
 * @Date 2021/8/2
 */
@RabbitListener(queues = "directQueue")
@Component
public class DirectListener {

	@RabbitHandler
	public void process(String msg) {
		System.out.println("DirectListener接收到了消息： " + msg);
	}


}
