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
import java.util.List;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/7/20 10:09
 **/
@Service
public class XINHUANewsImpl extends AbstractNewsDataService {

    @Override
    public void dataFetch() throws ParserConfigurationException, IOException, SAXException {
        String requestResult = fetchData(NewsDataSources.XINHUA.getLink());
        Document doc = Jsoup.parse(requestResult);
        Elements countriesNews = doc.select("div.leftBox");
        Elements textForNews = countriesNews.select("div.bigPic02 > div.tit > a");
        textForNews.forEach(
                newsMapper -> {
                    News news = new News();
                    news.setNewsTitle(newsMapper.text());
                    news.setNewsSources(newsMapper.attr("href"));
                    news.setNewsMedia(NewsDataSources.XINHUA.name());
                    news.setTime(stringToData(newsMapper.attr("href"), "\\d{4}-\\d{1,2}/\\d{1,2}", "yyyy-MM/dd"));
                    news.setCountry("CN");
                    newsList.add(news);
                }
        );
        newsRepository.saveAll(newsList);
        newsList.clear();
    }
}

