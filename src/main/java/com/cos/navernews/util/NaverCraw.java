package com.cos.navernews.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cos.navernews.domain.News;

@Component
public class NaverCraw {

	int aidnum = 1;
	
	public List<News> collect5() {
		RestTemplate rt = new RestTemplate();
		List<News> newsList = new ArrayList<>();
		
		for (int i = 1; i < 6; i++) {
			String aid = String.format("%010d", aidnum);
			String url = "https://news.naver.com/main/read.naver?mode=LSD&mid=shm&sid1=102&oid=022&aid=" + aid;
			String html = rt.getForObject(url, String.class);
			
			try {
				Document doc = Jsoup.parse(html);
				
				Element companyElement    = doc.selectFirst(".press_logo img ");
				Element titleElement            = doc.selectFirst("#articleTitle");
				Element createdAtElement = doc.selectFirst(".sponsor .t11");
				
				String company = companyElement.attr("title");
				String title = titleElement.text();
				String createdAt = createdAtElement.text();
				
				News news = News.builder()
						.company(company)
						.title(title)
						.createdAt(createdAt)
						.build();
				
				newsList.add(news);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("찾을수 없어요: " + e.getMessage());
			}
			
			aidnum++;
		}
		return newsList;
	}
}
