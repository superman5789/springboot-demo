package com.example.springboot.rabbitmq.direct.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 发消息
 * @Author zhangdongkang
 * @Date 2021/8/2
 */
@RestController
public class DirectController {

	@Resource
	private RabbitTemplate rabbitTemplate;

	@GetMapping("direct/send")
	public String send() {
		rabbitTemplate.convertAndSend("directExchange", "directRoutingKey", "i am a direct msg !");
		return "success";
	}


}
