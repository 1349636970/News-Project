package US.SummerChallenge.NewsProject.model.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/6/29 0:59
 **/
@Getter
@Setter
public class NewsDTO {
    private String newsTitle;
    private String newsSources;
    private String newsImage;
    private String newsSummary;
    private String newsTag;
    private String newsMedia;
    private String country;
    private Date time;
}
