package US.SummerChallenge.NewsProject.Services;

import US.SummerChallenge.NewsProject.model.dto.NewsDTO;

import java.time.LocalDate;
import java.util.Date;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/6/29 1:01
 **/
public interface iNewsService {
    NewsDTO getNews();
    NewsDTO getNews(LocalDate date);
}