package com.cos.navernews.batch;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

public class NaverCrawTest {
	
	@Test
	public void test1() {
		int aidnum = 1;
		RestTemplate rt = new RestTemplate();
		List<NewsTest> newsList = new ArrayList<>();
		
		for (int i = 1; i < 6; i++) {
			String aid = String.format("%010d", aidnum);
			String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=102&oid=022&aid=" + aid;
			String html = rt.getForObject(url, String.class);
			
			Document doc = Jsoup.parse(html);
			
			Element companyElement    = doc.selectFirst(".press_logo img ");
			Element titleElement            = doc.selectFirst("#articleTitle");
			Element createdAtElement = doc.selectFirst(".sponsor .t11");
			
			String company = companyElement.attr("title");
			String title = titleElement.text();
			String createdAt = createdAtElement.text();
			
			NewsTest news = NewsTest.builder()
					.company(company)
					.title(title)
					.createdAt(createdAt)
					.build();
			
			newsList.add(news);
			
			aidnum++;
	}
			
			
		}
}
