package com.example.springboot.redis;

import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.Random;

@CacheConfig(cacheNames = "redisDemo")
@Service
public class DemoRedisService {

	@Cacheable
	public Integer get() {
		return new Random().nextInt();
	}

	@CacheEvict(beforeInvocation = true, allEntries = true)
	public boolean delete() {
		return false;
	}

	@CachePut
	public Integer update() {
		return new Random().nextInt();
	}

//	@Caching(put = {
//			@CachePut(value = "user", key = "#user.id"),
//			@CachePut(value = "user", key = "#user.username"),
//			@CachePut(value = "user", key = "#user.email")
//	})
//	public Integer save() {
//		return new Random().nextInt();
//	}
}
