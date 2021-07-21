package US.SummerChallenge.NewsProject.Services;

import US.SummerChallenge.NewsProject.model.dto.NewsDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/6/29 1:01
 **/
public interface INewsService {
    List<NewsDTO> getTodayNews();
    List<List<NewsDTO>> getNewsBetween(String firstCountry, String secondCountry, String searchKeyWord);
}
