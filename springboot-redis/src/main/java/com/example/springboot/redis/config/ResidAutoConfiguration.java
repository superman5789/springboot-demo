package com.example.springboot.redis.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

/**
 * @Description: redis配置
 * @Author zhangdongkang
 * @Date 2020/8/27
 */
@Slf4j
@EnableCaching
@Configuration
@RequiredArgsConstructor
@AutoConfigureBefore(RedisAutoConfiguration.class)
public class ResidAutoConfiguration extends CachingConfigurerSupport {

	private final RedisConnectionFactory factory;

	/**
	 * redis缓存前缀，系统重启清除带前缀的缓存，如果不清除，修改了对象属性可能会和缓存中的属性和不一致而报错
	 */
	public static final String REDIS_CACHE_PREFIX = "cache_";

	/**
	 * 申明缓存管理器，会创建一个切面（aspect）并触发Spring缓存注解的切点（pointcut）
	 * 根据类或者方法所使用的注解以及缓存的状态，这个切面会从缓存中获取数据，将数据添加到缓存之中或者从缓存中移除某个值
	 */
	@Override
	@Bean
	public CacheManager cacheManager() {
		//默认缓存过期时间30分钟
		long defaultExpire = 60 * 30;
		//设置CacheManager的值序列化方式为json序列化
		RedisSerializer<Object> jsonSerializer = new GenericJackson2JsonRedisSerializer();
		RedisSerializationContext.SerializationPair<Object> serializer = RedisSerializationContext.SerializationPair
				.fromSerializer(jsonSerializer);
		//默认配置（强烈建议配置上）。  比如动态创建出来的都会走此默认配置
		RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
				.serializeValuesWith(serializer)
				.entryTtl(Duration.ofSeconds(defaultExpire));

		// 初始化RedisCacheManager
		return RedisCacheManager.builder(factory)
				.cacheDefaults(defaultCacheConfig)
				.build();
	}

	/**
	 * 异常处理，当Redis缓存发生异常时，打印日志，并且程序正常走，如果不加则不会继续查询数据库
	 */
	@Override
	public CacheErrorHandler errorHandler() {
		return new CacheErrorHandler() {
			@Override
			public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
				log.error(exception.getMessage());
			}

			@Override
			public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
				log.error(exception.getMessage());
			}

			@Override
			public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
				log.error(exception.getMessage());
			}

			@Override
			public void handleCacheClearError(RuntimeException exception, Cache cache) {
				log.error(exception.getMessage());
			}
		};
	}

	@Bean
	@Primary
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		return redisTemplate;
	}

	@Bean
	public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForHash();
	}

	@Bean
	public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForValue();
	}

	@Bean
	public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForList();
	}

	@Bean
	public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForSet();
	}

	@Bean
	public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForZSet();
	}

}
