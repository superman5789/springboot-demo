package com.example.springboot.rabbitmq.direct.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 直连模式配置;;;如果是Direct类型，则会将消息中的RoutingKey与该Exchange关联的所有Binding中的BindingKey进行比较，如果相等，则发送到该Binding对应的Queue中。
 * @Author zhangdongkang
 * @Date 2021/8/2
 */
@Configuration
public class DirectConfig {

	@Bean
	public Queue directQueue() {
		return new Queue("directQueue", true);
	}

	@Bean
	public Exchange directExchange() {
		return new DirectExchange("directExchange", true, false);
	}

	@Bean
	public Binding directBinding() {
		return BindingBuilder.bind(directQueue()).to(directExchange()).with("directRoutingKey").noargs();
	}


}
