package com.example.springbootelasticsearch.dao;

import com.example.springbootelasticsearch.entity.EsGoodsSpu;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
 
public interface EsGoodsSpuRepository extends ElasticsearchRepository<EsGoodsSpu, Long> {
 
}