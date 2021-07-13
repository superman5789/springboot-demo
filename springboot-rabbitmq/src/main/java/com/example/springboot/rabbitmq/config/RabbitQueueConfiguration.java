package com.example.springboot.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class RabbitQueueConfiguration {

 /**
  * 启动时创建队列
  * @return
  */
 @Bean
 public Queue queue() {
  return new Queue("hello");
 }
}