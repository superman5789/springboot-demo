package com.example.springbootelasticsearch.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
 
@Slf4j
@EnableElasticsearchRepositories(basePackages = "com.example.springbootelasticsearch")
@Configuration
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {
 
	@Value("${elasticsearch.host}")
	private String host;
	@Value("${elasticsearch.port}")
	private int port;
	// 使用的协议
	private String schema = "http";
	// 连接超时时间
	private int socketTimeOut = 30000;
	// 连接超时时间
	private int connectTimeOut = 1000;
	// 获取连接的超时时间
	private int connectionRequestTimeOut = 500;
	// 最大连接数
	private int maxConnectPerRoute = 100;
	// 最大路由连接数
	private int maxConnectNum = 100;
 
	@Override
	public RestHighLevelClient elasticsearchClient() {
		log.info("########## elasticSearchConfig host:{}", host);
		RestClientBuilder builder = RestClient.builder(new HttpHost(host, port, schema));
		// 异步httpclient连接延时配置
		builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
			@Override
			public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
				requestConfigBuilder.setConnectTimeout(connectTimeOut);
				requestConfigBuilder.setSocketTimeout(socketTimeOut);
				requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
				return requestConfigBuilder;
			}
		});
		// 异步httpclient连接数配置
		builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
			@Override
			public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
				httpClientBuilder.setMaxConnTotal(maxConnectNum);
				httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
				return httpClientBuilder;
			}
		});
		RestHighLevelClient client = new RestHighLevelClient(builder);
		return client;
	}
 
	@Bean
	public ElasticsearchRestTemplate elasticsearchRestTemplate() {
		return new ElasticsearchRestTemplate(elasticsearchClient());
	}
 
}