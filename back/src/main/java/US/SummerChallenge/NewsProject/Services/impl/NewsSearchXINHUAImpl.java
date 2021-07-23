package US.SummerChallenge.NewsProject.Services.impl;

import US.SummerChallenge.NewsProject.Services.base.AbstractNewsDataService;
import US.SummerChallenge.NewsProject.model.dto.NewsDTO;
import US.SummerChallenge.NewsProject.model.enums.NewsDataSources;
import US.SummerChallenge.NewsProject.model.enums.NewsSearchSources;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/7/21 8:03
 **/
@Service
public class NewsSearchXINHUAImpl extends AbstractNewsDataService {
    @Override
    public void dataFetch() throws ParserConfigurationException, IOException, SAXException {
    }

    public List<NewsDTO> dataFetch(String searchKeyWord) throws JsonProcessingException {
        String requestResult = fetchData(NewsSearchSources.XINHUA.getLink(searchKeyWord));
        ObjectMapper objectMapper = new ObjectMapper();
        List<NewsDTO> newsDTOS = new ArrayList<>();
        @SuppressWarnings("unchecked")
        Map<String, Map<String,List<Map<String,String>>>> mapper = objectMapper.readValue(requestResult, Map.class);
        List<Map<String, String>> dataList = mapper.get("content").get("results");
        dataList.forEach(
                data -> {
                    NewsDTO news = new NewsDTO();
                    news.setNewsMedia(NewsSearchSources.XINHUA.name());
                    news.setNewsTitle(data.get("title"));
                    news.setNewsSources(data.get("url"));
                    news.setTime(stringToData(data.get("pubtime"),"yyyy-MM-dd HH:mm:ss"));
                    news.setCountry("CN");
                    newsDTOS.add(news);
                }

        );
        return newsDTOS;
    }
}
