package US.SummerChallenge.NewsProject.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Zhenyu Lin
 * @Date: 2021/6/29 0:44
 **/
@Entity
@Table(name = "news")
@Getter
@Setter
public class News {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "NewsTitle")
    private String newsTitle;
    @Column(name = "NewsSources")
    private String newsSources;
    @Column(name = "NewsTag")
    private String newsTag;
    @Column(name = "NewsImage")
    private String newsImage;
    @Column(name = "NewsSummary")
    private String newsSummary;
    @Column(name = "Time")
    private Date time;
}
