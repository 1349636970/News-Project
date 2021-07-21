package US.SummerChallenge.NewsProject.Services.base;

import US.SummerChallenge.NewsProject.Repository.NewsRepository;
import US.SummerChallenge.NewsProject.model.entity.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/7/20 10:36
 **/
public abstract class AbstractNewsDataService {
    @Autowired
    protected NewsRepository newsRepository;
    protected List<News> newsList = new ArrayList<>();

    public abstract void dataFetch() throws ParserConfigurationException, IOException, SAXException;

    protected Date stringToData(String string, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date result;
        try {
            result = sdf.parse(string);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            result = new Date(0);
        }
        return result;
    }
    protected Date stringToData(String string,String regex,String pattern) {
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(string);
        Date date;
        if (m.find()) {
            DateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
            try {
                date = format.parse(m.group(0));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                date = new Date(0);
            }
        } else {
            date = new Date(0);
        }

        return date;
    }
    protected String fetchData(String link) {
        String result;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .header("accept", "application/json")
                .build();
        try {
            result = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e)  {
            System.out.println(e.getMessage());
            result = null;
        }

        return result;
    }
}
