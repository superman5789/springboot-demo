package com.example.springbootelasticsearch.service;

import com.example.springbootelasticsearch.entity.EsGoodsSpu;
import org.springframework.data.domain.Page;

/**
 * @Description: 搜索
 * @Author zhangdongkang
 * @Date 2021/4/25
 */
public interface SearchService{

	/**
	 * @Description: 搜索
	 * @Author zhangdongkang
	 * @Date 2021/4/25
	 */
	Page<EsGoodsSpu> search();

}
