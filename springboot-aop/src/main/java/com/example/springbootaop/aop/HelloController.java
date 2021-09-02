package com.example.springbootaop.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


	@MyAnnotation("123123")
	@GetMapping("hello")
	public String hello(String name) {
		return "hello," + name;
	}

	@GetMapping("hello2")
	public String hello2(String name, Integer age) {
		return "hello2," + name + "age," + age;
	}

}
