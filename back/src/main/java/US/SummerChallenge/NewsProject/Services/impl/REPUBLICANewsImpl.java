package US.SummerChallenge.NewsProject.Services.impl;

import US.SummerChallenge.NewsProject.Repository.NewsRepository;
import US.SummerChallenge.NewsProject.Services.base.AbstractNewsDataService;
import US.SummerChallenge.NewsProject.model.entity.News;
import US.SummerChallenge.NewsProject.model.enums.NewsDataSources;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Date: 2021/7/20 17:14
 **/
@Service
public class REPUBLICANewsImpl extends AbstractNewsDataService {

    @Override
    public void dataFetch() throws ParserConfigurationException, IOException, SAXException {
        String requestResult = fetchData(NewsDataSources.REPUBLICA.getLink());
        Document doc = Jsoup.parse(requestResult);
        Elements listOfNews = doc.select("div.carousel-inner > div.item");
        listOfNews.forEach(
                newsMapper -> {
                    News news = new News();
                    news.setNewsMedia(NewsDataSources.REPUBLICA.name());
                    news.setNewsSources(NewsDataSources.REPUBLICA.getLink()+ Objects.requireNonNull(newsMapper.selectFirst("a")).attr("href"));
                    news.setNewsImage(Objects.requireNonNull(newsMapper.selectFirst("a > figure > img")).attr("src"));
                    news.setNewsTitle(Objects.requireNonNull(newsMapper.selectFirst("div.container > a > div > h2")).text());
                    news.setNewsSummary(Objects.requireNonNull(newsMapper.selectFirst("div.container > a > div > p")).text());
                    news.setTime(new Date(0));
                    news.setCountry("NP");
                    newsList.add(news);
                }

        );
        newsRepository.saveAll(newsList);
        newsList.clear();
    }
}
