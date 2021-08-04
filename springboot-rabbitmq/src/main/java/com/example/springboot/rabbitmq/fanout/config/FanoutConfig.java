package com.example.springboot.rabbitmq.fanout.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 扇型队列配置;;;如果是 Fanout 类型，则会将消息发送给所有与该 Exchange 定义过 Binding 的所有 Queues 中去，其实是一种广播行为。
 * @Author zhangdongkang
 * @Date 2021/8/2
 */
@Configuration
public class FanoutConfig {

	@Bean
	public Queue fanoutQueue1() {
		return new Queue("fanoutQueue1");
	}

	@Bean
	public Queue fanoutQueue2() {
		return new Queue("fanoutQueue2");
	}

	@Bean
	public Exchange fanoutExchange() {
		return new FanoutExchange("fanoutExchange");
	}

	@Bean
	public Binding fanoutBinding1() {
		return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange()).with("").noargs();
	}

	@Bean
	public Binding fanoutBinding2() {
		return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange()).with("").noargs();
	}

}

