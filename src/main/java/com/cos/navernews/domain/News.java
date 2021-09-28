package com.cos.navernews.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
@Document(collection = "naver_news") // 주석을 제대로 달아야 한다.
public class News {
	//private Bson _id; // ObjectId(fdjskldjfds3)
	@Id
	private String _id;
	
	private String company;    // 신문사명
	private String title;            // 뉴스 제목
	private String createdAt; // 뉴스발행일
}
