package US.SummerChallenge.NewsProject.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
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

    @GeneratedValue
    @Id
    @Column(name = "id",length = 1024)
    private Long id;

    @Column(name = "NewsTitle")
    private String newsTitle;
    @Column(name = "NewsSources")
    private String newsSources;
    @Column(name = "NewsTag")
    private String newsTag;
    @Column(name = "NewsImage")
    private String newsImage;
    @Column(name = "NewsSummary",length = 4096)
    private String newsSummary;
    @Column(name = "Time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Column(name = "NewsMedia")
    private String newsMedia;
}
