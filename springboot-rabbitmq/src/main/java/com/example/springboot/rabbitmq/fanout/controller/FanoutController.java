package com.example.springboot.rabbitmq.fanout.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 发送消息
 * @Author zhangdongkang
 * @Date 2021/8/2
 */
@RestController
public class FanoutController {

	@Resource
	private RabbitTemplate rabbitTemplate;

	@GetMapping("fanout/send")
	public String send() {
		rabbitTemplate.convertAndSend("fanoutExchange", "", "i am fanout msg !");
		return "success";
	}

}
