package com.example.springbootelasticsearch.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDate;
 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document(indexName = "goods_spu", shards = 3, replicas = 1)
public class EsGoodsSpu {
 
	@Id
	private Long spuId;
	/**
	 * SPU编号，分发的商品编号相同
	 */
	@Field(type = FieldType.Keyword)
	private String spuCode;
	/**
	 * 商家id
	 */
	private Long merchantId;
	/**
	 * 商家名称
	 */
	@Field(type = FieldType.Keyword, index = false)
	private String merchantName;
	/**
	 * 商品名称
	 */
	@MultiField(mainField = @Field(type = FieldType.Text, analyzer = "ik_max_word"),
			otherFields = @InnerField(suffix = "pinyin", type = FieldType.Text, analyzer = "pinyin", searchAnalyzer = "pinyin"))
	private String spuName;
	/**
	 * 副标题
	 */
	@Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
	private String subTitle;
	/**
	 * 定位信息
	 */
	@GeoPointField
	private String position;
	/**
	 * 创建时间
	 */
	@Field(type = FieldType.Date, format = DateFormat.custom, pattern = "uuuu-MM-dd HH:mm:ss")
	private LocalDate createTime;
 
}