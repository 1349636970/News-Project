package US.SummerChallenge.NewsProject.Services.impl;

import US.SummerChallenge.NewsProject.Repository.NewsRepository;
import US.SummerChallenge.NewsProject.Services.INewsService;
import US.SummerChallenge.NewsProject.model.dto.NewsDTO;
import US.SummerChallenge.NewsProject.model.entity.News;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/6/29 1:05
 **/
@Service
public class NewsServiceImpl implements INewsService {
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    NewsSearchXINHUAImpl newsSearchXINHUA;
    @Autowired
    NewsSearchCBSImpl newsSearchCBS;
    @Override
    public List<NewsDTO> getTodayNews() {
        Specification<News> spec = (Specification<News>) (root, criteriaQuery, criteriaBuilder) -> {
            Date today = new Date();
            Calendar calendar=Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,-24);
            Date yesterdayDate=calendar.getTime();
            return criteriaBuilder.and(criteriaBuilder.lessThan(root.get("time"),today),criteriaBuilder.greaterThan(root.get("time"),yesterdayDate));
        };

        List<News> news = newsRepository.findAll(spec);
        return convertFromEntity(news);
    }

    @Override
    public List<List<NewsDTO>> getNewsBetween(String firstCountry, String secondCountry, String searchKeyWord) {
        List<List<NewsDTO>> result = new ArrayList<>();
        if (firstCountry.equals("CN") || secondCountry.equals("CN")) {
            try {
                result.add(newsSearchXINHUA.dataFetch(searchKeyWord));
            } catch (JsonProcessingException e) {
                System.out.println(e.getMessage());
            }
        } else if (firstCountry.equals("US") || secondCountry.equals("US")) {
            try {
                result.add(newsSearchCBS.dataFetch(searchKeyWord));
            } catch (JsonProcessingException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }



    private List<NewsDTO> convertFromEntity(List<News> newsList) {
        List<NewsDTO> result = new ArrayList<>();
        newsList.forEach(
                news -> {
                    NewsDTO newsDTO = new NewsDTO();
                    BeanUtils.copyProperties(news,newsDTO);
                    result.add(newsDTO);
                }
        );
        return result;
    }
}
