package com.example.springboot.rabbitmq.topic.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 主题模式配置;;如果是Topic类型，则会按照正则表达式，对RoutingKey与BindingKey进行匹配，如果匹配成功，则发送到对应的Queue中。
 * @Author zhangdongkang
 * @Date 2021/8/2
 */
@Configuration
public class TopicConfig {

	@Bean
	public Queue topicQueue1() {
		return new Queue("topic.queue1");
	}

	@Bean
	public Queue topicQueue2() {
		return new Queue("topic.queue2");
	}

	@Bean
	public Exchange topicExchange() {
		return new TopicExchange("topicExchange");
	}

	@Bean
	public Binding topicBinding1() {
		return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("topic.queue1").noargs();
	}

	@Bean
	public Binding topicBinding2() {
		return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("topic.#").noargs();
	}

}
