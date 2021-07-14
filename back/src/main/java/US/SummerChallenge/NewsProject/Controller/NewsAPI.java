package US.SummerChallenge.NewsProject.Controller;

import US.SummerChallenge.NewsProject.Services.iNewsService;
import US.SummerChallenge.NewsProject.model.dto.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/6/29 0:31
 **/
@RestController
public class NewsAPI {
    @Autowired
    iNewsService iNewsService;


    @RequestMapping(value = "todayNews", method = RequestMethod.POST)
    public NewsDTO getTodayNews() {
        return iNewsService.getNews();
    }

    @RequestMapping(value = "queryNewsByDate", method = RequestMethod.GET)
    public NewsDTO getNews(
            @RequestParam(name = "date")
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate date
    ) {
        return iNewsService.getNews(date);
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public LocalDate test(
            @RequestParam(name = "date")
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate date
    ) {
        return date;
    }
}
