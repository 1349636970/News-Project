package US.SummerChallenge.NewsProject.Controller;

import US.SummerChallenge.NewsProject.Services.INewsService;
import US.SummerChallenge.NewsProject.model.dto.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/6/29 0:31
 **/
@RestController
public class NewsAPI {
    @Autowired
    INewsService INewsService;


    @RequestMapping(value = "todayNews", method = RequestMethod.GET)
    public List<NewsDTO> getTodayNews() {
        return INewsService.getTodayNews();
    }

    @RequestMapping(value = "queryByCountries", method = RequestMethod.GET)
    public List<List<NewsDTO>> getNews(
            @RequestParam(name = "firstCountry")
            String firstCountry,
            @RequestParam(name = "secondCountry")
            String secondCountry,
            @RequestParam(name = "seachKeyWord")
            String searchKeyWord
    ) {
        return INewsService.getNewsBetween(firstCountry,secondCountry,searchKeyWord);
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
