package com.example.springboot.redis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootRedisApplicationTests {

	@Resource
	private  DemoRedisService demoRedisService;

    @Test
	void testGet() {
		System.out.println(demoRedisService.get());
		System.out.println(demoRedisService.get());
		System.out.println(demoRedisService.get());
		System.out.println(demoRedisService.get());
		System.out.println(demoRedisService.get());
	}

    @Test
	void testDelete() {
		System.out.println(demoRedisService.get());
		System.out.println(demoRedisService.get());
		System.out.println(demoRedisService.delete());
		System.out.println(demoRedisService.get());
		System.out.println(demoRedisService.get());
	}

    @Test
	void testUpdate() {
		System.out.println(demoRedisService.get());
		System.out.println(demoRedisService.get());
		System.out.println(demoRedisService.update());
		System.out.println(demoRedisService.get());
		System.out.println(demoRedisService.get());
	}

}
