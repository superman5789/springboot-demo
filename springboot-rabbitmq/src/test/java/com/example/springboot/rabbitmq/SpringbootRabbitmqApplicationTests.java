package com.example.springboot.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootRabbitmqApplicationTests {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Test
	void contextLoads() {
		amqpTemplate.convertAndSend("hello", "信息11122333");
	}

}
