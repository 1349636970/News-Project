package US.SummerChallenge.NewsProject.Services.impl;

import US.SummerChallenge.NewsProject.Repository.NewsRepository;
import US.SummerChallenge.NewsProject.Services.IDatabaseCleanService;
import US.SummerChallenge.NewsProject.model.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/8/24 13:12
 **/
@Service

public class DatabaseCleanServiceImpl implements IDatabaseCleanService {
    @Autowired
    NewsRepository newsRepository;

    @Override
    @Scheduled(cron = "0 0 7 * * ?")
    public void dailyClean() {
        Specification<News> spec = (Specification<News>) (root, criteriaQuery, criteriaBuilder) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, -48);
            Date today = calendar.getTime();
            calendar.set(Calendar.HOUR_OF_DAY, -24);
            Date yesterdayDate = calendar.getTime();
            return criteriaBuilder.and(criteriaBuilder.lessThan(root.get("time"), today), criteriaBuilder.greaterThan(root.get("time"), yesterdayDate));
        };
        List<News> readyDeleteNews = newsRepository.findAll(spec);
        newsRepository.deleteAll(readyDeleteNews);
    }
}
