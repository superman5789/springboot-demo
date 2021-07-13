package com.example.springboot.rabbitmq.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitQueueListener {

	/**
	 * 监听 hello 队列的处理器
	 *
	 * @param message
	 */
	@RabbitListener(queues = "hello")
	@RabbitHandler
	public void onMessage(Message message) {
		log.info("#############消费者：{}", message);
	}

}