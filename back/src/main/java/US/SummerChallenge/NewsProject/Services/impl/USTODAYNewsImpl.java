package US.SummerChallenge.NewsProject.Services.impl;

import US.SummerChallenge.NewsProject.Repository.NewsRepository;
import US.SummerChallenge.NewsProject.Services.base.AbstractNewsDataService;
import US.SummerChallenge.NewsProject.model.entity.News;
import US.SummerChallenge.NewsProject.model.enums.NewsDataSources;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/7/20 15:38
 **/
@Service
public class USTODAYNewsImpl extends AbstractNewsDataService {

    private final String Domain = "https://www.usatoday.com/";
    @Override
    public void dataFetch() throws ParserConfigurationException, IOException, SAXException {
        String requestResult = fetchData(NewsDataSources.USTODAY.getLink());
        Document doc = Jsoup.parse(requestResult);
        Element headlineNews = doc.selectFirst("div.gnt_pr > a");
        Elements listOfNews = doc.select("div.gnt_pr > div.gnt_m > a");
        listOfNews.stream()
                .filter(
                        newsMapper -> !newsMapper.attr("rel").equals("nofollow")
                ).
                forEach(
                        newsMapper -> {
                            News news = new News();
                            news.setNewsMedia(NewsDataSources.USTODAY.name());
                            news.setNewsTitle(newsMapper.text());
                            news.setNewsSources(Domain + newsMapper.attr("href"));
                            news.setNewsImage(Objects.requireNonNull(newsMapper.selectFirst("img")).attr("data-gl-src"));
                            assert headlineNews != null;
                            news.setTime(stringToData(headlineNews.attr("href"), "\\d{4}/\\d{1,2}/\\d{1,2}", "yyyy/MM/dd"));
                            news.setCountry("US");
                            newsList.add(news);
                        }
                );
        News news = new News();
        news.setNewsMedia(NewsDataSources.USTODAY.name());
        assert headlineNews != null;
        news.setNewsTitle(headlineNews.text());
        news.setNewsSources(headlineNews.attr("href"));
        news.setNewsImage(Objects.requireNonNull(headlineNews.selectFirst("img")).attr("src"));
        news.setTime(stringToData(headlineNews.attr("href"), "\\d{4}/\\d{1,2}/\\d{1,2}", "yyyy/MM/dd"));

        newsList.add(news);
        newsRepository.saveAll(newsList);
        newsList.clear();
    }
}
