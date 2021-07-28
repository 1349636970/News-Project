package US.SummerChallenge.NewsProject.Services.impl;

import US.SummerChallenge.NewsProject.Repository.NewsRepository;
import US.SummerChallenge.NewsProject.Services.base.AbstractNewsDataService;
import US.SummerChallenge.NewsProject.model.entity.News;
import US.SummerChallenge.NewsProject.model.enums.NewsDataSources;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/7/19 23:29
 **/
@Service
public class CCTVNewsImpl extends AbstractNewsDataService {

    @Override
    public void dataFetch() throws JsonProcessingException {
        String requestResult = fetchData(NewsDataSources.CCTV.getLink());
        Pattern pattern = Pattern.compile("\\{.+\\}");
        Matcher m = pattern.matcher(requestResult);
        String JSONData = null;
        while (m.find()) {
            JSONData = m.group(0);
            break;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
        Map<String, Map<String, List<Map<String, String>>>> mapper = objectMapper.readValue(JSONData, Map.class);
        List<Map<String, String>> dataList = mapper.get("data").get("list");
        dataList
                .forEach(
                        newsMapper -> {
                            News news = new News();
                            news.setNewsImage(newsMapper.get("image"));
                            news.setNewsSources(newsMapper.get("url"));
                            news.setNewsTitle(newsMapper.get("title"));
                            news.setNewsTag(newsMapper.get("keywords"));
                            news.setNewsSummary(newsMapper.get("desc"));
                            news.setTime(new java.sql.Timestamp(Long.parseLong(newsMapper.get("focus_date"))));
                            news.setNewsMedia(NewsDataSources.CCTV.name());
                            news.setCountry("CN");
                            newsList.add(news);

                        }
                );
        newsRepository.saveAll(newsList);
        newsList.clear();

    }
}


