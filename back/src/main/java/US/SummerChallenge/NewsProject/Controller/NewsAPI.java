package US.SummerChallenge.NewsProject.Controller;

import US.SummerChallenge.NewsProject.Services.NewsService;
import US.SummerChallenge.NewsProject.model.dto.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/6/29 0:31
 **/
@RestController
public class NewsAPI {
    @Autowired
    NewsService newsService;

    @RequestMapping(value = "todayNews", method = RequestMethod.POST)
    public NewsDTO getTodayNews() {
        return newsService.getNews();
    }
}
