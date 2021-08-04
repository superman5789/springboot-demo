package com.example.springboot.rabbitmq.topic.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 发送主题消息
 * @Author zhangdongkang
 * @Date 2021/8/2
 */
@RestController
public class TopicController {

	@Resource
	private RabbitTemplate rabbitTemplate;

	@GetMapping("topic/send1")
	public String send(){
		rabbitTemplate.convertAndSend("topicExchange","topic.queue1","i am topic msg111 !!");
		return "success";
	}

	@GetMapping("topic/send2")
	public String send2(){
		rabbitTemplate.convertAndSend("topicExchange","topic.222","i am topic msg222 !!");
		return "success";
	}

}
