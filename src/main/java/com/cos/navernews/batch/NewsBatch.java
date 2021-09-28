package com.cos.navernews.batch;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cos.navernews.domain.News;
import com.cos.navernews.domain.NewsRepository;
import com.cos.navernews.util.NaverCraw;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class NewsBatch {

	private final NewsRepository newsRepository;
	private final NaverCraw naverCraw;
	
	//@Scheduled(cron = "* * * * * *", zone = "Asia/Seoul")
	@Scheduled(fixedDelay = 1000*60*1)
	public void newCrawAndSave() {
		List<News> newsList = naverCraw.collect5();
		newsRepository.saveAll(newsList);
	}
}
