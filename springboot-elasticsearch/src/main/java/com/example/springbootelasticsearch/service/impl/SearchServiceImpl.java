package com.example.springbootelasticsearch.service.impl;

import cn.hutool.core.util.StrUtil;
import com.example.springbootelasticsearch.dao.EsGoodsSpuRepository;
import com.example.springbootelasticsearch.entity.EsGoodsSpu;
import com.example.springbootelasticsearch.service.SearchService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
 
/**
 * @Description: 搜索
 * @Author zhangdongkang
 * @Date 2021/4/25
 */
@Service
public class SearchServiceImpl implements SearchService {

	@Resource
	private EsGoodsSpuRepository esGoodsSpuRepository;
 
	/**
	 * @Description: 搜索
	 * @Author zhangdongkang
	 * @Date 2021/4/25
	 */
	@Override
	public Page<EsGoodsSpu> search() {
		//分页
		Integer pageNumber = 1;
		Integer pageSize = 10;

		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
 
		//关键词匹配
		String keywords = "aaa";
		if (StrUtil.isNotBlank(keywords)) {
			queryBuilder.must(
					QueryBuilders.boolQuery()
							// spuName IK分词匹配
							.should(QueryBuilders.matchQuery("spuName", keywords).boost(2))
							// spuName pinyin分词匹配
							.should(QueryBuilders.matchQuery("spuName.pinyin", keywords).boost(2))
							// 副标题 IK分词匹配
							.should(QueryBuilders.matchQuery("subTitle", keywords).boost(1))
			);
 
		}
 
		//商家id
		Long merchantId = 1L;
		if (merchantId != null) {
			queryBuilder.must(QueryBuilders.termQuery("merchantId", merchantId));
		}
		
		NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder()
				.withQuery(queryBuilder)
				.withPageable(pageRequest)
				.withSort(SortBuilders.scoreSort());

		return esGoodsSpuRepository.search(nativeSearchQueryBuilder.build());
	}
}