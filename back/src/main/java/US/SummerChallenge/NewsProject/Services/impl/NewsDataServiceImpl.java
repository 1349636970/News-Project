package US.SummerChallenge.NewsProject.Services.impl;

import US.SummerChallenge.NewsProject.Repository.NewsRepository;
import US.SummerChallenge.NewsProject.Services.NewsDataService;
import US.SummerChallenge.NewsProject.model.entity.News;
import US.SummerChallenge.NewsProject.model.enums.NewsDataSources;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/7/6 13:35
 **/
@Service
public class NewsDataServiceImpl implements NewsDataService {
    @Autowired
    NewsRepository newsRepository;
//    @PostConstruct
    @Override
    public void fetchData() throws IOException, InterruptedException {
        NewsDataSources.CCTV.setPageNumber(1);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request =  HttpRequest.newBuilder()
                .uri(URI.create(NewsDataSources.CCTV.getLink()))
                .build();
        HttpResponse<String> httpResponse = client.send(request,HttpResponse.BodyHandlers.ofString());
        Pattern pattern = Pattern.compile("\\{.+\\}");
        Matcher m = pattern.matcher(httpResponse.body());
        String JSONData = null;
        while(m.find()){
            JSONData = m.group(0);
            break;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
        Map<String,Map<String, List<Map<String,String>>>> mapper= objectMapper.readValue(JSONData,Map.class);
        List<Map<String,String>> dataList = mapper.get("data").get("list");
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
                            newsRepository.save(news);
                        }
                );

            System.out.print(3);

    }
}
