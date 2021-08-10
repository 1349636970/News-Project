package US.SummerChallenge.NewsProject.Services.impl;

import US.SummerChallenge.NewsProject.Services.base.AbstractNewsDataService;
import US.SummerChallenge.NewsProject.model.dto.NewsDTO;
import US.SummerChallenge.NewsProject.model.enums.NewsSearchSources;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/8/10 0:59
 **/
@Service
public class NewsSearchTHEHIMALAYANTIMESImpl extends AbstractNewsDataService {
    @Override
    public void dataFetch() throws ParserConfigurationException, IOException, SAXException {

    }

    public List<NewsDTO> dataFetch(String searchKeyWord) {
        String requestResult = fetchData(NewsSearchSources.THEHIMALAYANTIMES.getLink(searchKeyWord));
        List<NewsDTO> newsDTOS = new ArrayList<>();
        Document doc = Jsoup.parse(requestResult);
        Elements listOfNews = doc.select("div.searchWidget > div.js > div.post_list > article");
        listOfNews.forEach(
                news -> {

                    NewsDTO newsDTO = new NewsDTO();
                    try{
                        newsDTO.setNewsImage(news.child(0).selectFirst("figure > a > div.layout-ratio > picture > img").attr("src"));
                    } catch (NullPointerException e) {
                        System.err.println(e.getMessage());
                        newsDTO.setNewsImage(null);
                    }

                    newsDTO.setTime(stringToData());
                    newsDTO.setNewsSources(Objects.requireNonNull(news.child(1).selectFirst("h3 > a")).attr("href"));
                    newsDTO.setNewsTitle((Objects.requireNonNull(news.child(1).selectFirst("h3 > a"))).attr("title"));
                    newsDTO.setNewsMedia(NewsSearchSources.THEHIMALAYANTIMES.name());
                    newsDTOS.add(newsDTO);
                }
        );
        return newsDTOS;
    }

}
