package US.SummerChallenge.NewsProject.Services.impl;

import US.SummerChallenge.NewsProject.Services.INewsDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/7/20 17:25
 **/
@Service
public class NewsDataLoaderImpl implements INewsDataLoader {
    @Autowired
    CBSNewsImpl cbsNews;
    @Autowired
    CCTVNewsImpl cctvNews;
    @Autowired
    KATHMANDUPOSTNewsImpl kathmandupostNews;
    @Autowired
    NYTIMENewsImpl nytimeNews;
    @Autowired
    PEOPLENewsImpl peopleNews;
    @Autowired
    REPUBLICANewsImpl republicaNews;
    @Autowired
    THEHIMALAYANTIMESNewsImpl thehimalayantimesNews;
    @Autowired
    USTODAYNewsImpl ustodayNews;
    @Autowired
    XINHUANewsImpl xinhuaNews;
    @Override
    @PostConstruct
    @Scheduled(cron = "0 0 6 * * ?")
    public void newsDataLoad() {
        try {
            cbsNews.dataFetch();
            cctvNews.dataFetch();
            kathmandupostNews.dataFetch();
            nytimeNews.dataFetch();
            peopleNews.dataFetch();
            republicaNews.dataFetch();
            thehimalayantimesNews.dataFetch();
            ustodayNews.dataFetch();
            xinhuaNews.dataFetch();
        } catch (IOException | SAXException | ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }

    }
}
