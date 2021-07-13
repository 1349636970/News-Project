package US.SummerChallenge.NewsProject.Services.impl;

import US.SummerChallenge.NewsProject.Repository.NewsRepository;
import US.SummerChallenge.NewsProject.Services.NewsDataService;
import US.SummerChallenge.NewsProject.model.entity.News;
import US.SummerChallenge.NewsProject.model.enums.NewsDataSources;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Override
    public void CCTVfetchData() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request =  HttpRequest.newBuilder()
                .uri(URI.create(NewsDataSources.CCTV.getLink(1)))
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
        List<News> newsList = new ArrayList<>();
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
                            newsList.add(news);

                        }
                );
        newsRepository.saveAll(newsList);

    }

    @Override
    public void CBSNewsfetchData() throws IOException, InterruptedException {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request =  HttpRequest.newBuilder()
//                .uri(URI.create(NewsDataSources.CBS.getLink()))
//                .build();
//        HttpResponse<String> httpResponse = client.send(request,HttpResponse.BodyHandlers.ofString());

    }

    @PostConstruct
    @Override
    public void XINHUAfetchData() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request =  HttpRequest.newBuilder()
                .uri(URI.create(NewsDataSources.XINHUA.getLink()))
                .build();
        HttpResponse<String> httpResponse = client.send(request,HttpResponse.BodyHandlers.ofString());
        Document doc = Jsoup.parse(httpResponse.body());
        Elements countriesNews = doc.select("div.leftBox");
        Elements textForNews = countriesNews.select("div.bigPic02 > div.tit > a");
        List<News> newsList = new ArrayList<>();
        textForNews.forEach(
                newsMapper -> {
                    News news = new News();
                    news.setNewsTitle(newsMapper.text());
                    news.setNewsSources(newsMapper.attr("href"));
                    news.setNewsMedia(NewsDataSources.XINHUA.name());
                    try {
                        news.setTime(stringToDate(newsMapper.attr("href")));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    newsList.add(news);
                }
        );
        newsRepository.saveAll(newsList);

    }

    private Date stringToDate(String href) throws ParseException {
        String pattern = "\\d{4}-\\d{1,2}/\\d{1,2}";
        Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(href);
		Date date;
		if (m.find()) {
		    DateFormat format = new SimpleDateFormat("yyyy-MM/dd", Locale.ENGLISH);
		    date = format.parse(m.group(0));
        } else {
		    date = new Date(0);
        }

		return date;
    }


}
