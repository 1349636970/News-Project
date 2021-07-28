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
import java.util.Date;
import java.util.Objects;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/7/20 16:44
 **/
@Service
public class KATHMANDUPOSTNewsImpl extends AbstractNewsDataService {

    @Override
    public void dataFetch() throws ParserConfigurationException, IOException, SAXException {
        String requestResult = fetchData(NewsDataSources.KATHMANDUPOST.getLink());
        Document doc = Jsoup.parse(requestResult);
        Elements listOfNews = doc.select("div.block--morenews > article");
        listOfNews.forEach(
                newsMapper -> {
                    News news = new News();
                    news.setNewsMedia(NewsDataSources.KATHMANDUPOST.name());
                    news.setNewsSources(NewsDataSources.KATHMANDUPOST.getLink()+ Objects.requireNonNull(newsMapper.selectFirst("div.image > figure > a")).attr("href"));
                    news.setNewsImage(Objects.requireNonNull(newsMapper.selectFirst("div.image > figure > a > img")).attr("data-src"));
                    news.setNewsTitle(Objects.requireNonNull(newsMapper.selectFirst("a > h3")).text());
                    news.setNewsSummary(Objects.requireNonNull(newsMapper.selectFirst("p")).text());
                    news.setTime(new Date(0));
                    news.setCountry("NP");
                    newsList.add(news);
                }
        );
        newsRepository.saveAll(newsList);
        newsList.clear();
    }
}
