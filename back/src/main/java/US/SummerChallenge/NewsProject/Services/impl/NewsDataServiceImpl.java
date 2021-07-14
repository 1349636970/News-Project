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

    @PostConstruct
    public void executeAll() throws IOException, InterruptedException {
        CCTVfetchData();
        CBSNewsfetchData();
        XINHUAfetchData();
        PEOPLEfetchData();
        USTODAYfetchData();
        NYTIMEfetchData();
        THEHIMALAYANTIMESfetchData();
        KATHMANDUPOSTfetchData();
        REPUBLICAfetchData();
    }

    @Override
    public void CCTVfetchData() throws IOException, InterruptedException {
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
        String requestResult = fetchData(NewsDataSources.CBS.getLink());
        Document doc = Jsoup.parse(requestResult);
        Elements listOfNews = doc.select("section#component-topic-world > div.component__item-wrapper > article > a");
        List<News> newsList = new ArrayList<>();
        listOfNews.forEach(
                newsMapper -> {
                    News news = new News();
                    news.setNewsMedia(NewsDataSources.CBS.name());
                    news.setNewsSources(newsMapper.attr("href"));
                    news.setNewsImage(newsMapper.select("span > img").attr("src"));
                    news.setNewsTitle(newsMapper.select("div.item__title-wrapper > h4").text());
                    news.setNewsSummary(newsMapper.select("div.item__title-wrapper > p").text());
                    news.setTime(new Date(0));
                    newsList.add(news);
                }
        );
        newsRepository.saveAll(newsList);

    }


    @Override
    public void XINHUAfetchData() throws IOException, InterruptedException {
        String requestResult = fetchData(NewsDataSources.XINHUA.getLink());
        Document doc = Jsoup.parse(requestResult);
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
                        news.setTime(stringToDate(newsMapper.attr("href"), "\\d{4}-\\d{1,2}/\\d{1,2}", "yyyy-MM/dd"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    newsList.add(news);
                }
        );
        newsRepository.saveAll(newsList);

    }


    @Override
    public void PEOPLEfetchData() throws IOException, InterruptedException {
        String requestResult = fetchData(NewsDataSources.PEOPLE.getLink());
        Document doc = Jsoup.parse(requestResult);
        Elements list = doc.select("ul.foreign_list8 > li");
        List<News> newsList = new ArrayList<>();

        list.forEach(
                newsMapper -> {
                    News news = new News();
                    news.setNewsTitle(Objects.requireNonNull(newsMapper.selectFirst("a")).text());
                    news.setNewsSources("http://en.people.cn/" + newsMapper.select("a").attr("href"));
                    news.setNewsMedia(NewsDataSources.PEOPLE.name());
                    try {
                        news.setTime(stringToDate(newsMapper.select("span").text(), "\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}", "yyyy-MM-dd HH:mm"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    newsList.add(news);
                }
        );
        newsRepository.saveAll(newsList);
    }


    @Override
    public void USTODAYfetchData() throws IOException, InterruptedException {
        String requestResult = fetchData(NewsDataSources.USTODAY.getLink());
        Document doc = Jsoup.parse(requestResult);
        Element headlineNews = doc.selectFirst("div.gnt_pr > a");
        Elements listOfNews = doc.select("div.gnt_pr > div.gnt_m > a");
        List<News> newsList = new ArrayList<>();
        listOfNews.stream()
                .filter(
                        newsMapper -> !newsMapper.attr("rel").equals("nofollow")
                ).
                forEach(
                        newsMapper -> {
                            News news = new News();
                            news.setNewsMedia(NewsDataSources.USTODAY.name());
                            news.setNewsTitle(newsMapper.text());
                            news.setNewsSources("https://www.usatoday.com/"+newsMapper.attr("href"));
                            news.setNewsImage(Objects.requireNonNull(newsMapper.selectFirst("img")).attr("data-gl-src"));
                            try {
                                assert headlineNews != null;
                                news.setTime(stringToDate(headlineNews.attr("href"), "\\d{4}/\\d{1,2}/\\d{1,2}", "yyyy/MM/dd"));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            newsList.add(news);
                        }
                );
        News news = new News();
        news.setNewsMedia(NewsDataSources.USTODAY.name());
        assert headlineNews != null;
        news.setNewsTitle(headlineNews.text());
        news.setNewsSources(headlineNews.attr("href"));
        news.setNewsImage(Objects.requireNonNull(headlineNews.selectFirst("img")).attr("src"));
        try {
            news.setTime(stringToDate(headlineNews.attr("href"), "\\d{4}/\\d{1,2}/\\d{1,2}", "yyyy/MM/dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        newsList.add(news);
        newsRepository.saveAll(newsList);
    }


    @Override
    public void NYTIMEfetchData() throws IOException, InterruptedException {
        String requestResult = fetchData(NewsDataSources.NYTIME.getLink());
        Document doc = Jsoup.parse(requestResult);
        Elements ListOfnews = doc.select("div.css-13mho3u > ol > li.css-ye6x8s > div.css-1cp3ece");
        List<News> newsList = new ArrayList<>();
        ListOfnews.forEach(
                newsMapper -> {
                    News news = new News();
                    news.setNewsMedia(NewsDataSources.NYTIME.name());
                    news.setNewsSources("https://www.nytimes.com/"+Objects.requireNonNull(newsMapper.selectFirst("div.css-1l4spti > a")).attr("href"));
                    news.setNewsTitle(newsMapper.select("div.css-1l4spti > a > h2").text());
                    news.setNewsSummary(newsMapper.select("div.css-1l4spti > a > p").text());
                    news.setNewsImage(newsMapper.select("div.css-1l4spti > a > div.css-79elbk > figure > div.css-79elbk > img").attr("src"));
                    try {
                        news.setTime(
                                stringToDate(
                                        Objects.requireNonNull(newsMapper.selectFirst("div.css-1l4spti > a")).attr("href"),
                                "\\d{4}/\\d{1,2}/\\d{1,2}",
                                "yyyy/MM/dd")
                    );
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    newsList.add(news);
                }
        );
        newsRepository.saveAll(newsList);

    }


    @Override
    public void THEHIMALAYANTIMESfetchData() throws IOException, InterruptedException {
        String requestResult = fetchData(NewsDataSources.THEHIMALAYANTIMES.getLink());
        Document doc = Jsoup.parse(requestResult);
        Elements listOfNews = doc.select("div.ht-more-from-section-list > div.js > div.post_list > article");
        List<News> newsList = new ArrayList<>();
        listOfNews.forEach(
                newsMapper -> {
                    News news = new News();
                    news.setNewsMedia(NewsDataSources.THEHIMALAYANTIMES.name());
                    news.setNewsSources(newsMapper.select("div.col-md-3 > figure > a").attr("href"));
                    news.setNewsImage(Objects.requireNonNull(newsMapper.selectFirst("div.col-md-3 > figure > a > div > picture > source")).attr("data-srcset"));
                    news.setNewsTitle(newsMapper.select("div.col-md-9 > h3 > a").text());
                    news.setNewsSummary(newsMapper.select("div.col-md-9 > div > a").text());
                    news.setTime(new Date(0));
                    newsList.add(news);
                }
        );
        newsRepository.saveAll(newsList);
    }


    @Override
    public void KATHMANDUPOSTfetchData() throws IOException, InterruptedException {
        String requestResult = fetchData(NewsDataSources.KATHMANDUPOST.getLink());
        Document doc = Jsoup.parse(requestResult);
        Elements listOfNews = doc.select("div.block--morenews > article");
        List<News> newsList = new ArrayList<>();
        listOfNews.forEach(
                newsMapper -> {
                    News news = new News();
                    news.setNewsMedia(NewsDataSources.KATHMANDUPOST.name());
                    news.setNewsSources("https://kathmandupost.com/"+ Objects.requireNonNull(newsMapper.selectFirst("div.image > figure > a")).attr("href"));
                    news.setNewsImage(Objects.requireNonNull(newsMapper.selectFirst("div.image > figure > a > img")).attr("data-src"));
                    news.setNewsTitle(Objects.requireNonNull(newsMapper.selectFirst("a > h3")).text());
                    news.setNewsSummary(Objects.requireNonNull(newsMapper.selectFirst("p")).text());
                    news.setTime(new Date(0));
                    newsList.add(news);
                }
        );
        newsRepository.saveAll(newsList);
    }

    @Override
    public void REPUBLICAfetchData() throws IOException, InterruptedException {
        String requestResult = fetchData(NewsDataSources.REPUBLICA.getLink());
        Document doc = Jsoup.parse(requestResult);
        Elements listOfNews = doc.select("div.carousel-inner > div.item");
        List<News> newsList = new ArrayList<>();
        listOfNews.forEach(
                newsMapper -> {
                    News news = new News();
                    news.setNewsMedia(NewsDataSources.REPUBLICA.name());
                    news.setNewsSources("https://myrepublica.nagariknetwork.com/"+ Objects.requireNonNull(newsMapper.selectFirst("a")).attr("href"));
                    news.setNewsImage(Objects.requireNonNull(newsMapper.selectFirst("a > figure > img")).attr("src"));
                    news.setNewsTitle(Objects.requireNonNull(newsMapper.selectFirst("div.container > a > div > h2")).text());
                    news.setNewsSummary(Objects.requireNonNull(newsMapper.selectFirst("div.container > a > div > p")).text());
                    news.setTime(new Date(0));
                    newsList.add(news);
                }

        );
        newsRepository.saveAll(newsList);
    }


    private Date stringToDate(String href, String regexPattern, String dateFormat) throws ParseException {
        Pattern r = Pattern.compile(regexPattern);
        Matcher m = r.matcher(href);
        Date date;
        if (m.find()) {
            DateFormat format = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
            date = format.parse(m.group(0));
        } else {
            date = new Date(0);
        }

        return date;
    }



    private String fetchData(String link) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .header("accept","application/json")
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        return httpResponse.body();
    }
}
